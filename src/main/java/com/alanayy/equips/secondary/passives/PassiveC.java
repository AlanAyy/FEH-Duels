package com.alanayy.equips.secondary.passives;

import com.alanayy.combat.Combat;
import com.alanayy.units.Unit;

import java.util.ArrayList;

public class PassiveC {

    public PassiveC(PassiveListC passive, Unit unit, Combat combat) {
        int phase = combat.getPhase();
        boolean attacking = unit.isAttacking();
        boolean attacked = unit.isAttacked();
        ArrayList<Unit> adjacentAllies = unit.getAdjacentAllies();
        ArrayList<Unit> twoSpaceAllies = unit.getTwoSpaceAllies();

        switch (passive) {
            case ODD_ATK_WAVE_3:
                if (phase % 2 == 1) {
                    unit.setTempAtk(unit.getTempAtk() + 6);
                    for (Unit ally : adjacentAllies) {
                        ally.setTempAtk(ally.getTempAtk() + 6);
                    }
                }
                break;
            case ODD_RES_WAVE_3:
                if (phase % 2 == 1) {
                    unit.setTempRes(unit.getTempRes() + 6);
                    for (Unit ally : adjacentAllies) {
                        ally.setTempRes(ally.getTempRes() + 6);
                    }
                }
                break;
            case ODD_DEF_WAVE_3:
                if (phase % 2 == 1) {
                    unit.setTempDef(unit.getTempDef() + 6);
                    for (Unit ally : adjacentAllies) {
                        ally.setTempDef(ally.getTempDef() + 6);
                    }
                }
                break;
            case EVEN_ATK_WAVE_3:
                if (phase % 2 == 0) {
                    unit.setTempAtk(unit.getTempAtk() + 6);
                    for (Unit ally : adjacentAllies) {
                        ally.setTempAtk(ally.getTempAtk() + 6);
                    }
                }
                break;
            case GOAD_CAVALRY:
                if (attacking || attacked) {
                    for (Unit ally : twoSpaceAllies) {
                        ally.setTempAtk(ally.getTempAtk() + 4);
                        ally.setTempSpd(ally.getTempSpd() + 4);
                    }
                }
                break;
            case SURTRS_MENACE:
                if (!unit.getTwoSpaceFoes().isEmpty()) {
                    unit.setTempAtk(unit.getTempAtk() + 4);
                    unit.setTempSpd(unit.getTempSpd() + 4);
                    unit.setTempDef(unit.getTempDef() + 4);
                    unit.setTempRes(unit.getTempRes() + 4);
                    for (Unit foe : unit.getTwoSpaceFoes()) {
                        foe.setTempAtk(foe.getTempAtk() - 4);
                        foe.setTempSpd(foe.getTempSpd() - 4);
                        foe.setTempDef(foe.getTempDef() - 4);
                        foe.setTempRes(foe.getTempDef() - 4);
                    }
                }
                break;
            case SPARKLING_BOOST:
                Unit weakestAlly = null;
                int weakestAllyHp = 1000;
                for (Unit ally : unit.getTeammates()) {
                    if (ally.getTempHp() <= weakestAllyHp) {
                        weakestAllyHp = ally.getTempHp();
                        weakestAlly = ally;
                    }
                }
                if (weakestAlly != null) {
                    weakestAlly.setTempHp(weakestAlly.getTempHp() + 10);
                    if (weakestAlly.getTempHp() >= weakestAlly.getHp()) {
                        weakestAlly.setTempHp(weakestAlly.getHp());
                    }
                }
                break;
            case SOLITARY_DREAM:
                if (unit.getAdjacentAllies().isEmpty()) {
                    unit.setTempAtk(unit.getTempAtk() + 4);
                    unit.setTempSpd(unit.getTempSpd() + 4);
                    unit.setTempDef(unit.getTempDef() + 4);
                    unit.setTempRes(unit.getTempRes() + 4);
                }
                unit.setTempMovement(unit.getTempMovement() + 1);
                break;
        }
    }

    public enum PassiveListC {
        ODD_ATK_WAVE_3, ODD_RES_WAVE_3, ODD_DEF_WAVE_3, EVEN_ATK_WAVE_3,
        GOAD_CAVALRY, SURTRS_MENACE, SPARKLING_BOOST, SOLITARY_DREAM
    }
}
