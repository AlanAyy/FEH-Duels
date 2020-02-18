package com.alanayy.equips.primary;

import com.alanayy.combat.Combat;
import com.alanayy.units.Unit;

public class Weapon {

    private int might, range;

    public Weapon(WeaponName weaponName, Unit unit, Combat combat) {
        int combatState = combat.getCombatState();
        Unit enemy = unit.getEnemy();

        switch (weaponName) {
            case BINDING_BLADE_EFF:
                // Effective against dragon foes.
                if (enemy.getRace().equals("DRAGON")) {
                    // TODO Make effective.
                }
                // If foe initiates combat, grants Def/Res +4 during combat.
                if (unit.isAttacked()) {
                    unit.setTempDef(unit.getTempDef() + 4);
                    unit.setTempRes(unit.getTempRes() + 4);
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
                if (unit.getTwoSpaceFoes().contains(enemy)
                        && !unit.getAdjacentFoes().contains(enemy)) {
                    unit.setAdaptiveDmg(true);
                }
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
                        resolvePanic(cardinalFoe);
                    }
                }
                break;
            case FORBLAZE_EFF:
                // TODO Make a findHighest[insertStat] function sometime later.
                // At start of turn, inflicts Res-7 on foe on the enemy team
                // with the highest Res through its next action.
                if (combatState == Combat.START_TURN) {
                    int highestRes = 0;
                    Unit highestResUnit = unit;
                    for (Unit foe : unit.getEnemyTeam()) {
                        if (foe.getTempRes() > highestRes) {
                            highestRes = foe.getTempRes();
                            highestResUnit = foe;
                        }
                    }
                    highestResUnit.setTempRes(highestResUnit.getTempRes() - 7);
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
                        foe.setTempHp(foe.getTempHp() - 20);
                        if (foe.getTempHp() <= 0) {
                            foe.setTempHp(1);
                        }
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
                    unit.setTempAtk(unit.getTempAtk() + 4);
                    unit.setTempSpd(unit.getTempSpd() + 4);
                }
                // and if unit initiates combat,
                // foe cannot make a follow-up attack.
                if (unit.isAttacking()) {
                    enemy.setFollowUp(false);
                }
                // After combat, if unit attacked, inflict Def/Res-7 on target and
                // foes within 2 spaces of target through their next actions,
                if (combatState == Combat.AFTER_COMBAT && unit.isAttacking()) {
                    enemy.setTempDef(enemy.getTempDef() - 7);
                    enemy.setTempRes(enemy.getTempRes() - 7);
                    for (Unit foe : enemy.getTwoSpaceAllies()) {
                        foe.setTempDef(foe.getTempDef() - 7);
                        foe.setTempRes(foe.getTempRes() - 7);
                    }
                }
                // and if bonus was granted to unit, deals 4 damage to unit.
                // TODO Make this bonus bullshit lol.
                break;
            case RAZING_BREATH:
                // Effective against dragon foes.
                if (enemy.getRace().equals("DRAGON")) {
                    // TODO Make effective.
                }
                // Unit can counterattack regardless of foe's range.
                unit.setCloseCounter(true);
                unit.setFarCounter(true);
                // If foe's Range = 2, calculates damage using the lower of foe's Def or Res.
                if (unit.getTwoSpaceFoes().contains(enemy)
                        && !unit.getAdjacentFoes().contains(enemy)) {
                    unit.setAdaptiveDmg(true);
                }
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

    public void resolvePanic(Unit unit) {
        int difference;

        difference = unit.getTempAtk() - unit.getAtk();
        if (difference > 0)
            unit.setTempAtk(unit.getTempAtk() - difference * 2);

        difference = unit.getTempSpd() - unit.getSpd();
        if (difference > 0)
            unit.setTempSpd(unit.getSpd() - difference * 2);

        difference = unit.getTempDef() - unit.getDef();
        if (difference > 0)
            unit.setTempDef(unit.getTempDef() - difference * 2);

        difference = unit.getTempRes() - unit.getRes();
        if (difference > 0)
            unit.setTempRes(unit.getTempRes() - difference * 2);
    }

    public enum WeaponName {
        BINDING_BLADE_EFF, LIGHTNING_BREATH_SPD, CHERCHES_AXE_EFF, FORBLAZE_EFF,
        DIRE_THUNDER, SINMARA, LYFJABERG, RAZING_BREATH
    }
}
