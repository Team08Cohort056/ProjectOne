package com.project.oop.tasksmanagement.core;

import com.project.oop.tasksmanagement.commands.contracts.BaseCommand;
import com.project.oop.tasksmanagement.core.contracts.CommandFactory;
import com.project.oop.tasksmanagement.core.contracts.TaskManagementRepository;

public class CommandFactoryImpl implements CommandFactory {
    @Override
    public BaseCommand createCommandFromCommandName(String commandTypeAsString, TaskManagementRepository taskManagementRepository) {
        return null;
    }
}
