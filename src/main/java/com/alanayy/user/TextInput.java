package com.alanayy.user;

import com.alanayy.combat.Battle;
import com.alanayy.combat.Map;
import com.alanayy.logs.ExitStatus;
import com.alanayy.units.Unit;
import com.alanayy.user.TextCommands.Command;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TextInput {

    private static final TextCommands mainCommands = new TextCommands();
    private static final TextCommands unitCommands = new TextCommands();
    private static final TextCommands teamCommands = new TextCommands();
    private static final TextCommands fightCommands = new TextCommands();
    private static final Scanner scanner = new Scanner(System.in);
    private static final List<Unit> unitList = new ArrayList<>();
    private static final List<Unit> team1 = new ArrayList<>();
    private static final List<Unit> team2 = new ArrayList<>();
    private static Battle battle;

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

    /**
     * --------------------
     * |  Fight Commands  |
     * --------------------
     */

    private static void startDemoBattle() {
        // TODO Make demo battle.
    }

    private static void startBattle() {
        // TODO Make the arena changeable.
        setTeamIds();
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

            if (input.equals("help") || input.equals("?")) {
                helpMenu(commands);
                continue;
            } else if (input.equals("back") && !commands.equals(mainCommands)) {
                break;
            } else if (input.equals("quit") || input.equals("exit")) {
                quitGame();
            } else if (commands.equals(fightCommands)) {
                if (battle.isTeamDead(1)) {
                    printWinMsg(2);
                    break;
                } else if (battle.isTeamDead(2)) {
                    printWinMsg(1);
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
                System.out.println("Invalid command! Please try again.");
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
                    // --- Team Menu --- //
                    case "set":
                        setTeam();
                        break;
                    case "order":
                        orderTeam();
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
                new Command("start", "Enter the Arena and fight!"));
        unitCommands.addAllCommands(
                new Command("new", "{CMD} Make a new Unit."),
                new Command("del", "{CMD} Delete a Unit (by name)."));
        teamCommands.addAllCommands(
                new Command("set", "Put your Units into a Team."),
                new Command("order", "Change the order of Units in a Team."));
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
                unit.getRes());
    }

    private static void printWinMsg(int teamNum) {
        System.out.println("Team #" + teamNum + " wins!!!\n" +
                "Exiting to the Main Menu...");
        battle = null;
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
        System.exit(ExitStatus.QUIT);
    }

    private static String formatCaps(String input) {
        return input.substring(0, 1).toUpperCase() + input.substring(1);
    }
}