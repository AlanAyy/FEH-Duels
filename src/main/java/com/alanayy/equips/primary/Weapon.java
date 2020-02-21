package com.alanayy.equips.primary;

import com.alanayy.combat.Combat;
import com.alanayy.units.Unit;

import static com.alanayy.combat.StatusEffect.*;

public class Weapon {

    private int might, range;

    public Weapon(WeaponName weaponName, Unit unit, Combat combat) {
        int combatState = combat.getCombatState();
        Unit enemy = unit.getEnemy();

        switch (weaponName) {
            case BINDING_BLADE_EFF:
                // Effective against dragon foes.
                if (enemy.getRace().equals("DRAGON")) {
                    unit.setEffective(true);
                }
                // If foe initiates combat, grants Def/Res +4 during combat.
                if (unit.isAttacked()) {
                    affectDefRes(unit, 4);
                    // If unit's HP >= 50% and foe initiates combat, unit makes a guaranteed follow-up attack.
                    if (unit.getTempHp() >= unit.getHp() * 0.5) {
                        unit.setFollowUp(true);
                    }
                }
                break;
            case LIGHTNING_BREATH_SPD:
                // Slows Special trigger (cooldown count+1).
                if (combatState == Combat.PRE_BATTLE) {
                    unit.setSpecCharge(unit.getSpecCharge() + 1);
                }
                // If attacked, unit can counterattack regardless of foe's range.
                if (unit.isAttacked()) {
                    unit.setCloseCounter(true);
                    unit.setFarCounter(true);
                }
                // If foe's Range = 2, damage calculated using the lower of foe's Def or Res.
                adaptiveDmgIfERangeIs2(unit);
                break;
            case CHERCHES_AXE_EFF:
                // Inflicts Spd-5.
                if (combatState == Combat.PRE_BATTLE) {
                    unit.setSpd(unit.getSpd() - 5);
                }
                // If unit initiates combat, unit attacks twice.
                if (unit.isAttacking()) {
                    unit.setAttackTwice(true);
                }
                // At start of turn, converts bonuses on foes in cardinal directions
                // with HP < unit's HP into penalties through their next actions.
                if (combatState == Combat.START_TURN) {
                    for (Unit cardinalFoe : unit.getCardinalFoes()) {
                        panicTNA(cardinalFoe);
                    }
                }
                break;
            case FORBLAZE_EFF:
                // TODO Make a findHighest[insertStat] function sometime later.
                // At start of turn, inflicts Res-7 on foe on the enemy team
                // with the highest Res through its next action.
                if (combatState == Combat.START_TURN) {
                    chillResETNA(unit, 7);
                }
                // If unit initiates combat, grants Atk+6 during combat.
                if (unit.isAttacking()) {
                    unit.setTempAtk(unit.getTempAtk() + 6);
                }
                break;
            case DIRE_THUNDER:
                // Spd-5.
                if (combatState == Combat.PRE_BATTLE) {
                    unit.setSpd(unit.getSpd() - 5);
                }
                // Attack twice when initiating combat.
                if (unit.isAttacking()) {
                    unit.setAttackTwice(true);
                }
                break;
            case SINMARA:
                // Grants Def+3.
                if (combatState == Combat.PRE_BATTLE) {
                    unit.setDef(unit.getDef() + 3);
                }
                // At start of turn, deals 20 damage to foes within 2 spaces.
                if (combatState == Combat.START_TURN) {
                    for (Unit foe : unit.getTwoSpaceFoes()) {
                        affectHp(foe, -20);
                    }
                }
                break;
            case LYFJABERG:
                // Grants Res+3.
                if (combatState == Combat.PRE_BATTLE) {
                    unit.setRes(unit.getRes() + 3);
                }
                // At start of combat, if unit's HP is greater than or equal to 50%,
                // grants Atk/Spd+4 during combat,
                if ((unit.isAttacked() || unit.isAttacking())
                        && unit.getTempHp() >= unit.getHp() * 0.5) {
                    affectAtkSpd(unit, 4);
                }
                // and if unit initiates combat,
                // foe cannot make a follow-up attack.
                if (unit.isAttacking()) {
                    enemy.setFollowUp(false);
                }
                // After combat, if unit attacked, inflict Def/Res-7 on target and
                // foes within 2 spaces of target through their next actions,
                if (combatState == Combat.AFTER_COMBAT && unit.isAttacking()) {
                    affectDefResTNA(enemy, -7);
                    for (Unit foe : enemy.getTeammates()) {
                        affectDefResTNA(foe, -7);
                    }
                }
                // and if bonus was granted to unit, deals 4 damage to unit.
                // TODO Make this bonus bullshit lol.
                break;
            case RAZING_BREATH:
                // Effective against dragon foes.
                if (enemy.getRace().equals("DRAGON")) {
                    unit.setEffective(true);
                }
                // Unit can counterattack regardless of foe's range.
                unit.setCloseCounter(true);
                unit.setFarCounter(true);
                // If foe's Range = 2, calculates damage using the lower of foe's Def or Res.
                adaptiveDmgIfERangeIs2(unit);
                break;
        }
    }

    /**
     * ------------------
     * |  Weapon Stats  |
     * ------------------
     */

    public int getMight() {
        return might;
    }

    public void setMight(int might) {
        this.might = might;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    /**
     * --------------------
     * |  Status Effects  |
     * --------------------
     */

    public enum WeaponName {
        BINDING_BLADE_EFF, LIGHTNING_BREATH_SPD, CHERCHES_AXE_EFF, FORBLAZE_EFF,
        DIRE_THUNDER, SINMARA, LYFJABERG, RAZING_BREATH
    }
}
