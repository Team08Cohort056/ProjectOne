package com.project.oop.tasksmanagement.core.contracts;

import com.project.oop.tasksmanagement.commands.contracts.BaseCommand;

public interface CommandFactory {

    BaseCommand createCommandFromCommandName(String commandTypeAsString,
                                             TaskManagementRepository taskManagementRepository);

}

