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

package com.alanayy.combat;

import com.alanayy.units.Unit;

import java.util.ArrayList;

public class Map {

    // unitGrid values
    public static final int UNIT_EMPTY = 0;
    public static final int UNIT_A1 = 1;
    public static final int UNIT_A2 = 2;
    public static final int UNIT_A3 = 3;
    public static final int UNIT_A4 = 4;
    public static final int UNIT_B1 = 5;
    public static final int UNIT_B2 = 6;
    public static final int UNIT_B3 = 7;
    public static final int UNIT_B4 = 8;

    // mapGrid values
    public static final int BASIC = 0;
    public static final int SPAWN_1 = 1;
    public static final int SPAWN_2 = 2;
    public static final int FOREST = 3;
    public static final int TILE = 4;
    public static final int TRENCH = 5;
    public static final int HOLE = 6;

    private final int[][] mapGrid = new int[8][6];
    private final int[][] unitGrid = new int[8][6];
    private ArrayList<Unit> team1;
    private ArrayList<Unit> team2;

    public Map(MapList map) {
        if (map.equals(MapList.ARENA_1)) {
            for (int i = 1; i < 5; i++) {
                mapGrid[0][i] = SPAWN_1;
                mapGrid[7][i] = SPAWN_2;
            }
            mapGrid[1][1] = FOREST;
            mapGrid[1][4] = FOREST;
            mapGrid[6][1] = FOREST;
            mapGrid[6][4] = FOREST;
        }
        int team1UnitId = 1;
        int team2UnitId = 5;
        for (int row = 0; row < mapGrid.length; row++)
            for (int col = 0; col < mapGrid[row].length; col++)
                if (mapGrid[row][col] == SPAWN_1) {
                    unitGrid[row][col] = team1UnitId++;
                } else if (mapGrid[row][col] == SPAWN_2) {
                    unitGrid[row][col] = team2UnitId++;
                }
    }

    public int[][] getMapGrid() {
        return mapGrid;
    }

    public int[][] getUnitGrid() {
        return unitGrid;
    }

    /**
     * Finds a unit in the Map mapGrid.
     *
     * @param unitId The Unit ID to find.
     * @return int[row, column]
     */
    public int[] getPosById(int unitId) {
        for (int row = 0; row < unitGrid.length; row++)
            for (int col = 0; col < unitGrid[col].length; col++)
                if (unitGrid[row][col] == unitId)
                    return new int[]{row, col};
        return null;
    }

    public Unit getUnitByPos(int row, int col) {
        switch (unitGrid[row][col]) {
            case UNIT_A1:
                return getUnitById(UNIT_A1);
            case UNIT_A2:
                return getUnitById(UNIT_A2);
            case UNIT_A3:
                return getUnitById(UNIT_A3);
            case UNIT_A4:
                return getUnitById(UNIT_A4);
            case UNIT_B1:
                return getUnitById(UNIT_B1);
            case UNIT_B2:
                return getUnitById(UNIT_B2);
            case UNIT_B3:
                return getUnitById(UNIT_B3);
            case UNIT_B4:
                return getUnitById(UNIT_B4);
            default:
                return null;
        }
    }

    public Unit getUnitById(int id) {
        switch (id) {
            case UNIT_A1:
                return team1.get(0);
            case UNIT_A2:
                return team1.get(1);
            case UNIT_A3:
                return team1.get(2);
            case UNIT_A4:
                return team1.get(3);
            case UNIT_B1:
                return team2.get(0);
            case UNIT_B2:
                return team2.get(1);
            case UNIT_B3:
                return team2.get(2);
            case UNIT_B4:
                return team2.get(3);
        }
        return null;
    }

    public void setTeam(int teamOneOrTwo, ArrayList<Unit> unitList) {
        // Only made for ARENA_1 map!
        // Must change this code if you wanna make more maps later.
        if (teamOneOrTwo == 1) {
            unitGrid[0][1] = UNIT_A1;
            unitGrid[0][2] = UNIT_A2;
            unitGrid[0][3] = UNIT_A3;
            unitGrid[0][4] = UNIT_A4;
            team1 = unitList;
        } else if (teamOneOrTwo == 2) {
            unitGrid[7][1] = UNIT_A1;
            unitGrid[7][2] = UNIT_A2;
            unitGrid[7][3] = UNIT_A3;
            unitGrid[7][4] = UNIT_A4;
            team2 = unitList;
        } else {
            System.out.println("Unknown team value! (Map.java : int teamOneOrTwo)");
            System.exit(1);
        }
    }

    public void moveToPos(Unit unit, int row, int col) {
        int id = unit.getId();
        int[] lastPos = getPosById(id);
        unitGrid[row][col] = id;
        unitGrid[lastPos[0]][lastPos[1]] = UNIT_EMPTY;
    }

    public enum MapList {
        ARENA_1
    }
}
