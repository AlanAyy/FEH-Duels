/*
 * Copyright 2020 Alan "AlanAyy" Alcocer-Iturriza
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alanayy.equips.secondary.passives;

import com.alanayy.combat.Combat;
import com.alanayy.units.Unit;

import java.util.ArrayList;

public class PassiveC {

    public PassiveC(PassiveNameC passive, Unit unit, Combat combat) {
        int turn = combat.getTurn();
        boolean attacking = unit.isAttacking();
        boolean attacked = unit.isAttacked();
        ArrayList<Unit> adjacentAllies = unit.getAdjacentAllies();
        ArrayList<Unit> twoSpaceAllies = unit.getTwoSpaceAllies();

        switch (passive) {
            case ODD_ATK_WAVE_3:
                // At start of odd-numbered turns, grants Atk+6 to unit and adjacent allies for 1 turn.
                if (turn % 2 == 1) {
                    unit.setTempAtk(unit.getTempAtk() + 6);
                    for (Unit ally : adjacentAllies) {
                        ally.setTempAtk(ally.getTempAtk() + 6);
                    }
                }
                // (Bonus granted to unit even if no allies are adjacent.)
                break;
            case ODD_RES_WAVE_3:
                // At start of odd-numbers turns, grants Res+6 to unit and adjacent allies for 1 turn.
                if (turn % 2 == 1) {
                    unit.setTempRes(unit.getTempRes() + 6);
                    for (Unit ally : adjacentAllies) {
                        ally.setTempRes(ally.getTempRes() + 6);
                    }
                }
                // (Bonus granted to unit even if no allies are adjacent.)
                break;
            case ODD_DEF_WAVE_3:
                // At start of odd-numbered turns, grants Def+6 to unit and adjacent allies for 1 turn.
                if (turn % 2 == 1) {
                    unit.setTempDef(unit.getTempDef() + 6);
                    for (Unit ally : adjacentAllies) {
                        ally.setTempDef(ally.getTempDef() + 6);
                    }
                }
                // (Bonus granted to unit even if no allies are adjacent.)
                break;
            case EVEN_ATK_WAVE_3:
                // At start of even-numbered turns, grants Atk+6 to unit and adjacent allies for 1 turn.
                if (turn % 2 == 0) {
                    unit.setTempAtk(unit.getTempAtk() + 6);
                    for (Unit ally : adjacentAllies) {
                        ally.setTempAtk(ally.getTempAtk() + 6);
                    }
                }
                // (Bonus granted to unit even if no allies are adjacent.)
                break;
            case GOAD_CAVALRY:
                // Grants cavalry allies within 2 spaces Spd/Atk+4 during combat.
                if (attacking || attacked) {
                    for (Unit ally : twoSpaceAllies) {
                        ally.setTempAtk(ally.getTempAtk() + 4);
                        ally.setTempSpd(ally.getTempSpd() + 4);
                    }
                }
                break;
            case SURTRS_MENACE:
                // At start of turn, if unit is within 2 spaces of a foe,
                // grants Atk/Spd/Def/Res+4 for 1 turn and
                // inflicts Atk/Spd/Def/Res-4 on foes within 2 spaces through their next actions.
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
                // At start of turn, restores 10 HP to ally that has been dealt the most damage.
                Unit weakestAlly = null;
                int weakestAllyHp = 1000;
                for (Unit ally : unit.getAllyTeam()) {
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
                // (Excludes unit.)
                break;
            case SOLITARY_DREAM:
                // At start of turn, if unit is adjacent to only dragon allies or
                // if unit is not adjacent to any ally,
                // grants Atk/Spd/Def/Res+4 to unit and
                // unit can move 1 extra space.
                // TODO Include first part of Solitary Dream.
                if (unit.getAdjacentAllies().isEmpty()) {
                    unit.setTempAtk(unit.getTempAtk() + 4);
                    unit.setTempSpd(unit.getTempSpd() + 4);
                    unit.setTempDef(unit.getTempDef() + 4);
                    unit.setTempRes(unit.getTempRes() + 4);
                }
                unit.setTempMovement(unit.getTempMovement() + 1);
                // (That turn only. Does not stack.)
                break;
        }
    }

    public enum PassiveNameC {
        ODD_ATK_WAVE_3, ODD_RES_WAVE_3, ODD_DEF_WAVE_3, EVEN_ATK_WAVE_3,
        GOAD_CAVALRY, SURTRS_MENACE, SPARKLING_BOOST, SOLITARY_DREAM
    }
}
