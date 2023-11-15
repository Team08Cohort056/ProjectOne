package com.project.oop.tasksmanagement.commands.contracts;

import java.util.List;

public interface BaseCommand {

    String execute(List<String> commands);
}
