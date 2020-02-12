package com.alanayy.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TextCommands {

    public final List<Command> commandList = new ArrayList<>();

    public List<Command> getCommandList() {
        return commandList;
    }

    public String getHelpMsg() {
        StringBuilder helpMsg = new StringBuilder();
        helpMsg.append("Here is a list of available commands!")
                .append(System.lineSeparator());

        for (Command command : commandList) {
            String cmd = command.getCmd();
            String help = command.getHelp();
            helpMsg.append(cmd);

            // Change this for bigger / smaller spacing in the Help Message.
            int SPACING = 6;

            helpMsg.append(new String(new char[SPACING - cmd.length()]).replace("\0", " "))
                    .append("-  ")
                    .append(help)
                    .append(System.lineSeparator());
        }
        return helpMsg.toString();
    }

    public void addAllCommands(Command... commands) {
        commandList.addAll(Arrays.asList(commands));
    }

    public static class Command {

        private String cmd, help;
        // private String method;

        public Command(String cmd, String help) {
            this.cmd = cmd;
            this.help = help;
            // this.method = method;
            // commandList.add(new Command(command, helpText, methodToRun));
        }

        public String getCmd() {
            return cmd;
        }

        public String getHelp() {
            return help;
        }

        /*
        public String getMethod() {
            return method;
        }
         */
    }
}