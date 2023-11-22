package com.project.oop.tasksmanagement.commands.addcreatecommands;

import com.project.oop.tasksmanagement.commands.contracts.BaseCommand;
import com.project.oop.tasksmanagement.core.contracts.TaskManagementRepository;
import com.project.oop.tasksmanagement.models.contracts.Bug;
import com.project.oop.tasksmanagement.utils.ParsingHelpers;
import com.project.oop.tasksmanagement.utils.ValidationHelpers;

import java.util.List;

public class AddBugReproduceStepsCommand implements BaseCommand {
    private static final int NUMBER_OF_PARAMETERS = 2;
    public static final String ID_SHOULD_BE_INTEGER_ERR = "ID should be integer.";
    public static final String STEPS_TO_REPRODUCE_BUG_ADDED_SUCCESSFULLY = "Steps to reproduce Bug with ID %d added successfully.";
    private final TaskManagementRepository taskManagementRepository;

    public AddBugReproduceStepsCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> commands) {
        ValidationHelpers.validateArgumentsCount(commands,NUMBER_OF_PARAMETERS);
        int id = ParsingHelpers.tryParseInt(commands.get(0), ID_SHOULD_BE_INTEGER_ERR);
        String steps = commands.get(1);
        return addStepsToReproduceBug(id,steps);
    }

    private String addStepsToReproduceBug(int id, String steps){
        Bug bug = taskManagementRepository.findBugById(id);
        bug.addStepToReproduce(steps);
        return STEPS_TO_REPRODUCE_BUG_ADDED_SUCCESSFULLY.formatted(id);
    }
}
