package com.alanayy.combat;

import com.alanayy.units.Unit;

import java.util.Arrays;
import java.util.List;

public class Battle {

    private static final int TEAM1_WINS = 1;
    private static final int TEAM2_WINS = 2;
    private Map map;
    private List<Unit> team1, team2;

    public Battle(List<Unit> team1, List<Unit> team2, Map map) {
        this.team1 = team1;
        this.team2 = team2;
        this.map = map;
        runPreBattleChecks();
    }

    public void printMapGrid() {
        System.out.println(Arrays.deepToString(map.getMapGrid()));
    }

    public void printUnitGrid() {
        System.out.println(Arrays.deepToString(map.getUnitGrid()));
    }

    public void runPreBattleChecks() {
        for (Unit unit : team1)
            resetTempStats(unit);
        for (Unit unit : team2)
            resetTempStats(unit);
    }

    public void runRoutineChecks() {
        resolveNearbyUnits();
    }

    /**
     * -----------------------
     * |  Pre-Battle Checks  |
     * -----------------------
     */

    public void resetTempStats(Unit unit) {
        unit.setTempHp(unit.getHp());
        unit.setTempAtk(unit.getAtk());
        unit.setTempSpd(unit.getSpd());
        unit.setTempDef(unit.getDef());
        unit.setTempRes(unit.getRes());
    }

    /**
     * --------------------
     * |  Routine Checks  |
     * --------------------
     */

    public void resolveNearbyUnits() {
        // TODO this :v
    }

    public void hideDeadUnits() {
        for (int i = 0; i < 4; i++) {
            if (team1.get(i).getTempHp() <= 0) {
                int[] pos = map.getPosById(i + 1);
                // TODO Hide dead units.
            }
        }
    }

    public boolean isTeamDead(int teamNum) {
        boolean teamDead = true;
        if (teamNum == 1) {
            for (int[] col : map.getUnitGrid())
                for (int val : col)
                    if (val == Map.UNIT_A1 || val == Map.UNIT_A2 || val == Map.UNIT_A3 || val == Map.UNIT_A4) {
                        teamDead = false;
                        break;
                    }
        } else if (teamNum == 2) {
            for (int[] col : map.getUnitGrid())
                for (int val : col)
                    if (val == Map.UNIT_B1 || val == Map.UNIT_B2 || val == Map.UNIT_B3 || val == Map.UNIT_B4) {
                        teamDead = false;
                        break;
                    }
        } else {
            System.out.println("Wrong team #!!!");
        }
        return teamDead;
    }

    /**
     * ---------------------
     * |  One-time Checks  |
     * ---------------------
     */

    public boolean canSpecial(Unit unit) {
        return unit.getTempSpecCharge() == 0;
    }
}