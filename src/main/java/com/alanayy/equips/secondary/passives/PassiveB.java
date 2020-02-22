package com.alanayy.equips.secondary.passives;

import com.alanayy.combat.Combat;
import com.alanayy.units.Unit;

public class PassiveB {

    public PassiveB(PassiveNameB passive, Unit unit, Combat combat) {
        int hp = unit.getTempHp();
        int maxHp = unit.getHp();
        int combatState = combat.getCombatState();
        boolean attacking = unit.isAttacking();
        boolean attacked = unit.isAttacked();
        Unit enemy = unit.getTargetEnemy();

        switch (passive) {
            case GUARD_3:
                // If unit's HP is ≥ 80% at start of combat,
                // enemy is inflicted with Special cooldown charge-1.
                if (hp >= maxHp * 0.8 && (attacking || attacked)) {
                    enemy.setTempSpecCharge(enemy.getTempSpecCharge() - 1);
                }
                //(If using similar skill, only highest value applied.)
                break;
            case QUICK_RIPOSTE_1:
                // Unit automatically makes a follow-up attack if attacked at HP ≥ 90%.
                if (hp >= maxHp * 0.9 && attacked) {
                    unit.setFollowUp(true);
                }
                break;
            case QUICK_RIPOSTE_3:
                // Unit automatically makes a follow-up attack if attacked at HP ≥ 70%.
                if (hp >= maxHp * 0.7 && attacked) {
                    unit.setFollowUp(true);
                }
                break;
            case SPECIAL_SPIRAL_3:
                // If Special triggers before or during combat,
                // grants Special cooldown count-2 after combat.
                if (unit.getTempSpecCharge() == 0
                        && combatState == Combat.BEFORE_COMBAT) {
                    unit.setSpecCharge(unit.getSpecCharge() - 2);
                }
                break;
            case VANTAGE_3:
                // Unit counterattacks first when attacked at HP ≤ 75%
                if (hp <= maxHp * 0.75 && attacked) {
                    unit.setCounterPriority(true);
                }
                break;
            case WARY_FIGHTER_3:
                // Prevents follow up attack in combat from unit and foes if unit's HP ≥ 50%
                if (hp >= maxHp * 0.5) {
                    unit.setFollowUp(false);
                    unit.getTargetEnemy().setFollowUp(false);
                }
                break;
            case MYSTIC_BOOST_3:
                // Disables foe's skills that "calculate damage
                // using the lower of foe's Def or Res" and "calculate
                // damage from staff like other weapons."
                // TODO Code rest of Mystic Boost.
                // Restores 6 HP after combat.
                if (combat.getCombatState() == Combat.AFTER_COMBAT) {
                    unit.setHp(unit.getTempHp() + 6);
                    if (unit.getTempHp() >= unit.getHp()) {
                        unit.setTempHp(unit.getHp());
                    }
                }
                break;
            case SPECIAL_FIGHTER_3:
                // At start of combat, if unit's HP ≥ 50%,
                // grants Special cooldown charge +1 to unit and
                // inflicts Special cooldown charge -1 on foe per attack.
                if (hp >= maxHp * 0.5) {
                    unit.setTempSpecCharge(unit.getTempSpecCharge() + 1);
                    enemy.setTempSpecCharge(unit.getTempSpecCharge() - 1);
                }
                // (Only highest value applied. Does not stack.)
                break;
        }
    }

    public enum PassiveNameB {
        GUARD_3, QUICK_RIPOSTE_1, QUICK_RIPOSTE_3, SPECIAL_SPIRAL_3,
        VANTAGE_3, WARY_FIGHTER_3, MYSTIC_BOOST_3, SPECIAL_FIGHTER_3
    }
}
