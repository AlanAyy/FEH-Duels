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
