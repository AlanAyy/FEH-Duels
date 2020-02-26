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