package com.alanayy.equips.secondary;

import com.alanayy.combat.Combat;
import com.alanayy.units.Unit;

import static com.alanayy.combat.StatusEffect.affectDefRes;
import static com.alanayy.combat.StatusEffect.chillAtkETNA;

public class Seal {

    public Seal(SealName sealName, Unit unit, Combat combat) {
        int combatState = combat.getCombatState();

        switch (sealName) {
            case CLOSE_DEF_3:
                // If unit is attacked by foe using sword, axe, lance, or dragonstone,
                // unit receives Def/Res+6 during combat.
                // TODO First part of Close Def.
                if (unit.isAttacked()) {
                    affectDefRes(unit, 6);
                }
                break;
            case DISTANT_DEF_3:
                // If unit is attacked by foe using bow, daggers, magic, or staff,
                // unit receives Def/Res+6 during combat.
                // TODO First part of Distant Def.
                if (unit.isAttacked()) {
                    affectDefRes(unit, 6);
                }
                break;
            case HEAVY_BLADE_3:
                // If unit's Atk - foe's Atk ≥ 1, unit gains Special cooldown charge +1 attack.

                // (If using other similar skill, only highest value applied.)

                break;
            case ATK_3:
                // TODO Include descriptions here.
                if (combatState == Combat.PRE_BATTLE) {
                    unit.setAtk(unit.getAtk() + 3);
                }
                break;
            case ATK_1:
                // TODO Include descriptions here.
                if (combatState == Combat.PRE_BATTLE) {
                    unit.setAtk(unit.getAtk() + 1);
                }
                break;
            case HP_4:
                // TODO Include descriptions here.
                if (combatState == Combat.PRE_BATTLE) {
                    unit.setHp(unit.getHp() + 4);
                }
                break;
            case SPD_1:
                // TODO Include descriptions here.
                if (combatState == Combat.PRE_BATTLE) {
                    unit.setSpd(unit.getSpd() + 1);
                }
                break;
            case CHILL_ATK_1:
                // At the start of each turn, inflicts Atk-3 on foe on the
                // enemy team with the highest Atk through its next action.
                chillAtkETNA(unit, 3);
                break;
        }
    }

    public enum SealName {
        CLOSE_DEF_3, DISTANT_DEF_3, HEAVY_BLADE_3, ATK_3,
        ATK_1, HP_4, SPD_1, CHILL_ATK_1
    }
}
