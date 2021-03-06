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

package com.alanayy.user;

import com.alanayy.combat.Battle;
import com.alanayy.combat.Map;
import com.alanayy.logs.ErrorHandler;
import com.alanayy.units.Unit;
import com.alanayy.user.TextCommands.Command;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TextInput {

    private static final TextCommands mainCommands = new TextCommands();
    private static final TextCommands unitCommands = new TextCommands();
    private static final TextCommands unitEditCommands = new TextCommands();
    private static final TextCommands teamCommands = new TextCommands();
    private static final TextCommands fightCommands = new TextCommands();
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Unit> unitList = new ArrayList<>();
    private static final List<Unit> team1 = new ArrayList<>();
    private static final List<Unit> team2 = new ArrayList<>();
    private static Battle battle;
    private static Unit tempUnit;

    static {
        System.out.println("Welcome to Fire Emblem - Duels! (Made by AlanAyy)");
        System.out.println("(It's a work in progress, but bear with me while I improve it!)");
        System.out.println("(Everything is a menu, except for Commands which are labeled with [CMD])");
        System.out.println("Type \"help\" for a list of commands you can use.");
        System.out.println("Type \"back\" to go back to your previous menu.\n");
        makeTextCommands();
        menuInput(mainCommands);
    }

    /**
     * -------------------
     * |  Unit Commands  |
     * -------------------
     */

    private static void listUnit() {
        for (Unit unit : unitList) {
            printUnitInfo(unit);
        }
    }

    private static void newUnit() {
        System.out.println("To make a new Unit, follow this format:\n" +
                "(H = Human / D = Dragon / B = Beast)\n" +
                "(A = Armored / I = Infantry / C = Cavalry / F = Flying)\n" +
                "{name} {color} {H/D/B} {A/I/C/F} {level} {hp} {atk} {spd} {def} {res}");
        String[] input = scanner.nextLine().split("\\s+");
        Unit newUnit = new Unit(formatCaps(input[0]), input[1].toUpperCase(), input[2].toUpperCase(),
                input[3].toUpperCase(), input[4], input[5], input[6], input[7], input[8], input[9]);
        unitList.add(newUnit);
        System.out.println("Unit " + input[0] + " has been added!");
        printUnitInfo(newUnit);
    }

    private static void editUnit() {
        if (unitList.isEmpty()) {
            System.out.println("You haven't made any Units yet!\n");
        } else {
            System.out.println("Here is the list of Units you can edit:");
            for (Unit unit : unitList) {
                System.out.print(unit.getName() + ", ");
            }
            System.out.println("\nWhich units would you like to edit?");
            // Input looper
            while (true) {
                boolean validInput = false;
                String input = scanner.nextLine();
                for (int i = 0; i < unitList.size(); i++) {
                    Unit unit = unitList.get(i);
                    String name = unit.getName();
                    if (input.equalsIgnoreCase(name)) {
                        validInput = true;
                        System.out.println("Editing \"" + name + "\"...");
                        menuInput(unitEditCommands);
                        tempUnit = unit;
                        unitList.remove(i);
                        unitList.add(i, tempUnit);
                        System.out.println("Unit \"" + tempUnit.getName() + "\" has been updated!");
                        tempUnit = null;
                        break;
                    }
                }
                if (input.equals("back")) {
                    System.out.println("Returning to previous menu...");
                    break;
                }
                if (!validInput) {
                    System.out.println(ErrorHandler.INVALID_INPUT);
                }
            }
        }
    }

    private static void delUnit() {
        boolean unitFound = false;
        int unitPosInList = 0;
        System.out.println("To delete a Unit, follow this format:\n" +
                "{name}");
        String input = scanner.nextLine();
        for (int i = 0; i < unitList.size(); i++) {
            String unitName = unitList.get(i).getName();
            if (unitName.equalsIgnoreCase(input)) {
                unitPosInList = i;
                unitFound = true;
            }
        }
        String capsInput = formatCaps(input);
        if (unitFound) {
            unitList.remove(unitPosInList);
            System.out.println("Unit \"" + capsInput + "\" was successfully removed!");
        } else {
            System.out.println("Unit \"" + capsInput + "\" could not be found. Unit was NOT removed!");
        }
    }

    /**
     * ------------------------
     * |  Unit>Edit Commands  |
     * ------------------------
     */

    private static void unitEditor(String unitValToChange) {
        System.out.println("What would you like to set your Unit's \"" + unitValToChange + "\" to?");
        String input = scanner.nextLine();

        switch (unitValToChange) {
            case "name":
                tempUnit.setName(input);
                break;
            case "color":
                tempUnit.setColor(input);
                break;
            case "race":
                tempUnit.setRace(input);
                break;
            case "type":
                tempUnit.setType(input);
                break;
            case "stats":
                // TODO Set stats and remove properly omg.
            case "rmv":
                System.out.println("Coming soon!");
                break;
            case "wep":
                // tempUnit.setWeapon(strToWeapon());
                // TODO Set weapons properly omg.
                break;
            case "asst":
                // tempUnit.setAssist(input);
                // TODO Set assists properly omg.
                break;
            case "spec":
                // tempUnit.setSpecial(input);
                // TODO Set specials properly omg.
                break;
            case "psvA":
                // tempUnit.setPassives(strToPassiveNameA());
                // TODO Set passives properly omg.
                break;
            case "psvB":
                // tempUnit.setPassives(strToPassiveNameB());
                // TODO Set passives properly omg.
                break;
            case "psvC":
                // tempUnit.setPassives(strToPassiveNameC());
                // TODO Set passives properly omg.
                break;
            case "seal":
                // tempUnit.setSeal(input);
                // TODO Set seals properly omg.
                break;
        }
    }

    /**
     * -------------------
     * |  Team Commands  |
     * -------------------
     */

    private static void setTeam() {
        System.out.println("To set a team, follow this format:\n" +
                "{name} {name} {name} {name} {team # (1 or 2)}");
        String[] input = scanner.nextLine().split("\\s+");
        int teamNum = Integer.parseInt(input[4]) - 1;

        int unitsAdded = 0;
        for (int i = 0; i < 4; i++)
            for (Unit unit : unitList)
                if (input[i].equalsIgnoreCase(unit.getName())) {
                    if (input[4].equals("1")) {
                        team1.add(unit);
                    } else if (input[4].equals("2")) {
                        team2.add(unit);
                    }
                    // TODO Make an error handler.
                    unitsAdded++;
                }
        // Fancy print thing + singular or plural.
        System.out.println(unitsAdded == 4
                ? "All units were successfully added to Team #" + teamNum + "!"
                : "Warning! " + (4 - unitsAdded) +
                (unitsAdded == 1
                        ? " unit was not added, because it does not exist."
                        : " units were not added, because they do not exist"));
    }

    private static void orderTeam() {
        // TODO Make the Order Team command.
    }

    private static void clearTeam() {
        team1.clear();
        team2.clear();
        System.out.println("All teams cleared!");
    }

    /**
     * --------------------
     * |  Fight Commands  |
     * --------------------
     */

    private static void startDemoBattle() {
        // TODO Make Demo Battle :v
    }

    private static void startBattle() {
        setTeamIds();
        isEverythingReady();
        battle = new Battle(team1, team2, new Map(Map.MapList.ARENA_1));
        battle.printUnitGrid();
        menuInput(fightCommands);
    }

    private static void infoFight() {
        boolean unitFound = false;
        System.out.println("To get info on a Unit, follow this format:\n" +
                "{name}");
        String[] input = scanner.nextLine().split("\\s+");
        for (Unit unit : team1)
            if (input[1].equalsIgnoreCase(unit.getName())) {
                printUnitInfo(unit);
                unitFound = true;
                break;
            }
        if (!unitFound)
            for (Unit unit : team2)
                if (input[1].equalsIgnoreCase(unit.getName())) {
                    printUnitInfo(unit);
                    unitFound = true;
                    break;
                }
        if (!unitFound)
            System.out.println("Unit \"" + formatCaps(input[1]) + "\" could not be found!");
    }

    private static void checkFight() {

    }

    private static void actFight() {

    }

    private static void endFight() {

    }

    /**
     * -------------------
     * |  Menu Commands  |
     * -------------------
     */

    private static void menuInput(TextCommands commands) {
        List<Command> commandList = commands.getCommandList();
        String input;

        while (true) {
            boolean validInput = false;
            // [CHECK] IF YOU GET ERRORS, LOOK HERE!!!!!!
            System.out.println(">>>");
            input = scanner.nextLine();

            if (input.equals("help") || input.equals("?")) { // Help checks
                helpMenu(commands);
                continue;
            } else if (input.equals("back") && !commands.equals(mainCommands)) { // Back checks
                break;
            } else if (input.equals("quit") || input.equals("exit")) { // Exit checks
                quitGame();
            } else if (commands.equals(fightCommands)) {
                if (battle.isTeamDead(1)) {
                    resolveWin(2);
                    break;
                } else if (battle.isTeamDead(2)) {
                    resolveWin(1);
                    break;
                }
            }
            for (Command command : commandList) {
                if (input.equals(command.getCmd())) {
                    validInput = true;
                    resolveInput(input, commands);
                }
            }
            if (!validInput) {
                System.out.println(ErrorHandler.INVALID_INPUT);
            }
            System.out.println(System.lineSeparator());
        }
        scanner.close();
        quitGame();
    }

    private static void resolveInput(String input, TextCommands commands) {
        List<Command> cmdList = commands.getCommandList();
        for (Command command : cmdList)
            if (command.getCmd().equals(input))
                switch (input) {
                    // --- Main Menu --- //
                    case "unit":
                        System.out.println("You have entered the Unit menu.");
                        menuInput(unitCommands);
                        break;
                    case "team":
                        menuInput(teamCommands);
                        break;
                    case "demo":
                        startDemoBattle();
                        // NO BREAK! Demo just makes the teams and feeds into "start".
                    case "start":
                        startBattle();
                        break;
                    // --- Unit Menu --- //
                    case "list":
                        listUnit();
                        break;
                    case "new":
                        newUnit();
                        break;
                    case "del":
                        delUnit();
                        break;
                    // --- Unit>Edit Menu --- //
                    // "list" is already taken care of by Unit Menu, so we'll exclude it.
                    case "name":
                    case "color":
                    case "race":
                    case "type":
                    case "stats":
                    case "wep":
                    case "asst":
                    case "spec":
                    case "psvA":
                    case "psvB":
                    case "psvC":
                    case "seal":
                    case "rmv":
                        unitEditor(input);
                        break;
                    // --- Team Menu --- //
                    case "set":
                        setTeam();
                        break;
                    case "order":
                        orderTeam();
                        break;
                    case "clear":
                        clearTeam();
                        break;
                    // --- Fight Menu --- //
                    case "info":
                        infoFight();
                        break;
                    case "check":
                        checkFight();
                        break;
                    case "act":
                        actFight();
                        break;
                    case "end":
                        endFight();
                        break;
                }
    }

    private static void helpMenu(TextCommands commands) {
        System.out.println(commands.getHelpMsg());
    }

    /**
     * ------------------
     * |  All Commands  |
     * ------------------
     */

    private static void makeTextCommands() {
        mainCommands.addAllCommands(
                new Command("unit", "Make changes to your Units."),
                new Command("team", "Change your Fire Emblem team."),
                new Command("demo", "Start a fight with basic Units."),
                new Command("start", "Enter the Arena and fight!"));
        unitCommands.addAllCommands(
                new Command("list", "{CMD} List available Units."),
                new Command("new", "{CMD} Make a new Unit."),
                new Command("del", "{CMD} Delete a Unit (by name)."));
        unitEditCommands.addAllCommands(
                new Command("list", "List everything about your Unit."),
                new Command("name", "Change your Unit's name."),
                new Command("color", "Change your Unit's color."),
                new Command("race", "Change your Unit's race."),
                new Command("type", "Change your Unit's movement type."),
                new Command("stats", "Change your Unit's stats."),
                new Command("wep", "Set your Unit's Weapon."),
                new Command("asst", "Set your Unit's Assist Skill."),
                new Command("spec", "Set your Unit's Special Skill."),
                new Command("psvA", "Set your Unit's (A) Passive."),
                new Command("psvB", "Set your Unit's (B) Passive."),
                new Command("psvC", "Set your Unit's (C) Passive."),
                new Command("seal", "Set your Unit's Seal."),
                new Command("rmv", "Remove your Unit's equipment (all but name, attributes and stats)."));
        teamCommands.addAllCommands(
                new Command("set", "Put your Units into a Team."),
                new Command("order", "Change the order of Units in a Team."),
                new Command("clear", "Remove all Units from all Teams."));
        fightCommands.addAllCommands(
                new Command("info", "Get info on a Unit."),
                new Command("check", "See what would happen before acting."),
                new Command("act", "Do something (Ex: attack or move a unit)."),
                new Command("end", "Ends the player's turn."));
    }

    /**
     * --------------------
     * |  Misc. Commands  |
     * --------------------
     */

    private static void printUnitInfo(Unit unit) {
        String space = " ";
        System.out.println(unit.getName() + space +
                unit.getColor() + space +
                unit.getRace() + space +
                unit.getType() + space +
                unit.getHp() + space +
                unit.getAtk() + space +
                unit.getSpd() + space +
                unit.getDef() + space +
                unit.getRes() + System.lineSeparator());
    }

    private static void resolveWin(int teamNum) {
        System.out.println("Team #" + teamNum + " wins!!!\n" +
                "Exiting to the Main Menu...");
        // Cleanup before next fight.
        battle = null;
        team1.clear();
        team2.clear();
    }

    private static void isEverythingReady() {
        // TODO Finish ready-check before starting battle.
        if (team1.size() > 4) {
            System.out.println("Team 1:" + ErrorHandler.TOO_MANY_UNITS);
        }
        if (team2.size() > 4) {
            System.out.println("Team 2:" + ErrorHandler.TOO_MANY_UNITS);
        }
    }

    private static void setTeamIds() {
        for (int i = 0; i < 4; i++) {
            team1.get(i).setId(i + 1);
        }
        for (int i = 0; i < 4; i++) {
            team2.get(i).setId(i + 5);
        }
    }

    private static void quitGame() {
        System.out.println("\nThanks for playing!" +
                "\n@Sodapop#3480 on Discord if you have any feedback you'd like to give.");
        System.exit(ErrorHandler.QUIT);
    }

    private static String formatCaps(String input) {
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }
}