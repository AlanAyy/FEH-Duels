package com.alanayy.combat;

import com.alanayy.units.Unit;

public class Combat {

    public static final int PRE_BATTLE = 0;
    public static final int START_TURN = 1;
    public static final int BEFORE_COMBAT = 2;
    public static final int AFTER_COMBAT = 3;
    public static final int END_TURN = 4;
    private int turn = 1;
    private int phase = 1;
    private int combatState;

    /**
     * --------------------
     * |  Turns + Phases  |
     * --------------------
     */

    public int getTurn() {
        return turn;
    }

    public int getPhase() {
        return phase;
    }

    public void nextTurn() {
        turn++;
    }

    public void nextPhase() {
        phase++;
    }


    /**
     * ------------------------
     * |  Fight Calculations  |
     * ------------------------
     */

    public void fight(Unit atk, Unit def) {
        // TODO Some fight mechanic.
    }

    private int damageCalc(Unit atk, Unit def) {
        // TODO Calculate damage.
        return 0;
        // REMOVE THIS 0! It's only here so the Build is updated lol.
    }

    public int getCombatState() {
        return combatState;
    }
}
