package com.alanayy.equips.secondary.passives;

import com.alanayy.combat.Combat;
import com.alanayy.units.Unit;

public class PassiveA {

    public PassiveA(PassiveNameA passive, Unit unit, Combat combat) {
        int hp = unit.getTempHp();
        int maxHp = unit.getHp();
        boolean attacking = unit.isAttacking();
        boolean attacked = unit.isAttacked();

        switch (passive) {
            case DISTANT_COUNTER:
                unit.setFarCounter(true);
            case STEADY_STANCE_4:
                if (attacked) {
                    unit.setTempDef(unit.getTempDef() + 6);
                }
                break;
            case FURY_3:
                unit.setAtk(unit.getAtk() + 3);
                if (combat.getCombatState() == Combat.AFTER_COMBAT) {
                    unit.setTempHp(unit.getTempHp() - 6);
                    if (unit.getTempHp() <= 0) {
                        unit.setTempHp(1);
                    }
                }
                break;
            case DEATHBLOW_4:
                if (attacking) {
                    unit.setTempAtk(unit.getTempAtk() + 8);
                }
                break;
            case SWIFT_SPARROW_2:
                if (attacking) {
                    unit.setTempAtk(unit.getTempAtk() + 4);
                    unit.setTempSpd(unit.getTempSpd() + 4);
                }
                break;
            case BRAZEN_ATKSPD_3:
                if (hp <= maxHp * 0.8) {
                    unit.setTempAtk(unit.getTempAtk() + 7);
                    unit.setTempSpd(unit.getTempSpd() + 7);
                }
                break;
        }
    }

    public enum PassiveNameA {
        DISTANT_COUNTER, STEADY_STANCE_4, FURY_3, DEATHBLOW_4,
        SWIFT_SPARROW_2, BRAZEN_ATKSPD_3
    }
}
