package com.project.oop.tasksmanagement.core.contracts;

import java.util.List;

public interface BaseCommand {

    String execute(List<String> commands);
}
