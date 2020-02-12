package com.alanayy.equips.secondary.passives;

import com.alanayy.combat.Combat;
import com.alanayy.units.Unit;

public class PassiveB {

    public PassiveB(PassiveListB passive, Unit unit, Combat combat) {
        int hp = unit.getTempHp();
        int maxHp = unit.getHp();
        boolean attacking = unit.isAttacking();
        boolean attacked = unit.isAttacked();
        Unit enemy = unit.getEnemy();

        switch (passive) {
            case GUARD_3:
                if (hp >= maxHp * 0.8 && (attacking || attacked)) {
                    enemy.setTempSpecCharge(enemy.getTempSpecCharge() - 1);
                }
                break;
            case QUICK_RIPOSTE_1:
                if (hp >= maxHp * 0.9 && attacked) {
                    unit.setFollowUp(true);
                }
                break;
            case QUICK_RIPOSTE_3:
                if (hp >= maxHp * 0.7 && attacked) {
                    unit.setFollowUp(true);
                }
                break;
            case SPECIAL_SPIRAL_3:
                // TODO: Code Special Spiral
                break;
            case VANTAGE_3:
                if (hp <= maxHp * 0.75 && attacked) {
                    unit.setCounterPriority(true);
                }
                break;
            case WARY_FIGHTER_3:
                if (hp >= maxHp * 0.5) {
                    unit.setFollowUp(false);
                    unit.getEnemy().setFollowUp(false);
                }
                break;
            case MYSTIC_BOOST_3:
                if (combat.getCombatState() == Combat.AFTER_COMBAT) {
                    unit.setHp(unit.getTempHp() + 6);
                    if (unit.getTempHp() >= unit.getHp()) {
                        unit.setTempHp(unit.getHp());
                    }
                }
                // TODO: Code rest of Mystic Boost
                break;
            case SPECIAL_FIGHTER_3:
                if (hp >= maxHp * 0.5) {
                    unit.setTempSpecCharge(unit.getTempSpecCharge() + 1);
                    enemy.setTempSpecCharge(unit.getTempSpecCharge() - 1);
                }
                break;
        }
    }

    public enum PassiveListB {
        GUARD_3, QUICK_RIPOSTE_1, QUICK_RIPOSTE_3, SPECIAL_SPIRAL_3,
        VANTAGE_3, WARY_FIGHTER_3, MYSTIC_BOOST_3, SPECIAL_FIGHTER_3
    }
}
