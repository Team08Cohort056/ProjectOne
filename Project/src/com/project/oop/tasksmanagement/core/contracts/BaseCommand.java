package com.project.oop.tasksmanagement.core.contracts;

import java.util.List;

public interface BaseCommand {

    void execute(List<String> commands);
}
