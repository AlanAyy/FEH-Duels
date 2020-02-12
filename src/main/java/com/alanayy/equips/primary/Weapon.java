package com.alanayy.equips.primary;

import com.alanayy.combat.Combat;
import com.alanayy.units.Unit;

public class Weapon {

    private int range;

    public Weapon(WeaponName weaponName, Unit unit, Combat combat) {
        int combatState = combat.getCombatState();

        switch (weaponName) {
            case BINDING_BLADE_EFF:
                if (unit.getEnemy().getRace().equals("DRAGON")) {
                    // TODO Make effective.
                }
                if (unit.isAttacked()) {
                    unit.setTempDef(unit.getTempDef() + 4);
                    unit.setTempRes(unit.getTempRes() + 4);
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
                if (unit.getTwoSpaceFoes().contains(unit.getEnemy())
                        && !unit.getAdjacentFoes().contains(unit.getEnemy())) {
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
                break;
            case DIRE_THUNDER:
                break;
            case SINMARA:
                break;
            case LYFJABERG:
                break;
            case RAZING_BREATH:
                break;
        }
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public void resolvePanic(Unit unit) {
        int difference;

        difference = unit.getTempAtk() - unit.getAtk();
        if (difference > 0)
            unit.setAtk(unit.getAtk() - difference);

        difference = unit.getTempSpd() - unit.getSpd();
        if (difference > 0)
            unit.setAtk(unit.getSpd() - difference);

        difference = unit.getTempDef() - unit.getDef();
        if (difference > 0)
            unit.setAtk(unit.getDef() - difference);

        difference = unit.getTempRes() - unit.getRes();
        if (difference > 0)
            unit.setAtk(unit.getRes() - difference);
    }

    public enum WeaponName {
        BINDING_BLADE_EFF, LIGHTNING_BREATH_SPD, CHERCHES_AXE_EFF, FORBLAZE_EFF,
        DIRE_THUNDER, SINMARA, LYFJABERG, RAZING_BREATH
    }
}
