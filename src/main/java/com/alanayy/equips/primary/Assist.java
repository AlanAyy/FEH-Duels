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

package com.alanayy.equips.primary;

import com.alanayy.combat.Combat;
import com.alanayy.units.Unit;

import static com.alanayy.combat.StatusEffect.affectAtkDefEOT;
import static com.alanayy.combat.StatusEffect.affectDefResEOT;

public class Assist {

    public Assist(AssistName assistName, Unit unit, Combat combat) {
        Unit ally = unit.getTargetAlly();

        switch (assistName) {
            case RALLY_ATKDEF:
                // Grants Atk/Def+3 to an adjacent ally until the end of the turn.
                affectAtkDefEOT(ally, 3);
                break;
            case RALLY_DEFRES:
                // Grants Def/Res+3 to an adjacent ally until the end of the turn.
                affectDefResEOT(ally, 3);
                break;
            case REPOSITION:
                // Moves adjacent ally to opposite side of unit.
                // TODO Reposition.
                break;
            case SWAP:
                // Swap places with an adjacent ally.
                // TODO Swap.
                break;
        }
    }

    public enum AssistName {
        RALLY_ATKDEF, RALLY_DEFRES, REPOSITION,
        SWAP
    }
}
