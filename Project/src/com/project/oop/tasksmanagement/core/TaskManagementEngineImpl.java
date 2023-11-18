package com.project.oop.tasksmanagement.core;

import com.project.oop.tasksmanagement.commands.contracts.BaseCommand;
import com.project.oop.tasksmanagement.core.contracts.CommandFactory;
import com.project.oop.tasksmanagement.core.contracts.TaskManagementEngine;
import com.project.oop.tasksmanagement.core.contracts.TaskManagementRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TaskManagementEngineImpl implements TaskManagementEngine {
    private static final String TERMINATION_COMMAND = "Exit";
    private static final String EMPTY_COMMAND = "Command cannot be empty.";
    private static final String MAIN_SPLIT_SYMBOL = " ";
    private static final String COMMENT_OPEN_SYMBOL = "[[";
    private static final String COMMENT_CLOSE_SYMBOL = "]]";
    private static final String REPORT_SEPARATOR = "--------------------";

    private final CommandFactory commandFactory;
    private final TaskManagementRepository taskManagementRepository;

    public TaskManagementEngineImpl() {
        commandFactory = new CommandFactoryImpl();
        taskManagementRepository = new TaskManagementRepositoryImpl();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                String inputLine = scanner.nextLine();
                if (inputLine.isBlank()) {
                    print(EMPTY_COMMAND);
                    continue;
                }
                if (inputLine.equalsIgnoreCase(TERMINATION_COMMAND)) {
                    break;
                }
                processCommand(inputLine);
            } catch (Exception ex) {
                if (ex.getMessage() != null && !ex.getMessage().isEmpty()) {
                    print(ex.getMessage());
                } else {
                    print(ex.toString());
                }
            }
        }
    }

    private void processCommand(String inputLine) {
        String commandName = extractCommandName(inputLine);
        BaseCommand baseCommand = commandFactory.createCommandFromCommandName(commandName, taskManagementRepository);
        List<String> parameters = extractCommandParameters(inputLine);
        String commandResult = baseCommand.execute(parameters);
        print(commandResult);
    }

    private String extractCommandName(String inputLine) {
        return inputLine.split(" ")[0];
    }

    private List<String> extractCommandParameters(String inputLine) {
        if (inputLine.contains(COMMENT_OPEN_SYMBOL)) {
            return extractSentenceParameters(inputLine);
        }
        String[] commandParts = inputLine.split(" ");
        ArrayList<String> parameters = new ArrayList<>();
        for (int i = 1; i < commandParts.length; i++) {
            parameters.add(commandParts[i]);
        }
        return parameters;
    }
    public List<String> extractSentenceParameters(String fullCommand) {
        int indexOfFirstSeparator = fullCommand.indexOf(MAIN_SPLIT_SYMBOL);
        List<String> parameters = new ArrayList<>();
        while (fullCommand.contains("[[") || fullCommand.contains("]]")) {
            int indexOfOpenSentence = fullCommand.indexOf(COMMENT_OPEN_SYMBOL);
            int indexOfCloseSentence = fullCommand.indexOf(COMMENT_CLOSE_SYMBOL);
            if (indexOfOpenSentence >= 0) {
                parameters.add(fullCommand.substring(indexOfOpenSentence + COMMENT_OPEN_SYMBOL.length(), indexOfCloseSentence));
                fullCommand = fullCommand.replace(fullCommand.substring(indexOfOpenSentence,indexOfCloseSentence+1), "");
            }
        }
        List<String> result = new ArrayList<>(Arrays.asList(fullCommand.substring(indexOfFirstSeparator + 1)
                .split(MAIN_SPLIT_SYMBOL)));
        for (String parameter : parameters) {
            result.add(result.indexOf("]"), parameter);
            result.remove("]");
        }
        return result;
    }

    private void print(String result) {
        System.out.println(result);
        System.out.println(REPORT_SEPARATOR);
    }
}
