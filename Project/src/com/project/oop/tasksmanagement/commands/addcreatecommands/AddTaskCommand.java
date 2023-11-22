package com.project.oop.tasksmanagement.commands.addcreatecommands;

import com.project.oop.tasksmanagement.commands.contracts.BaseCommand;
import com.project.oop.tasksmanagement.core.contracts.TaskManagementRepository;
import com.project.oop.tasksmanagement.models.contracts.Task;
import com.project.oop.tasksmanagement.models.enums.Severity;
import com.project.oop.tasksmanagement.models.enums.StorySize;
import com.project.oop.tasksmanagement.models.enums.TaskType;
import com.project.oop.tasksmanagement.utils.ParsingHelpers;
import com.project.oop.tasksmanagement.utils.ValidationHelpers;

import java.util.List;

public class AddTaskCommand implements BaseCommand {
    private static final int NUMBER_OF_PARAMETERS = 6;
    private static final String CANNOT_CREATE_THIS_TYPE_OF_TASK = "Cannot create this type of task.";
    private static final String TASK_ADDED_SUCCESSFULLY = "%s with ID %d added successfully to Team %s Board %s!";
    private final TaskManagementRepository taskManagementRepository;
    public AddTaskCommand(TaskManagementRepository taskManagementRepository){
        this.taskManagementRepository = taskManagementRepository;
    }
    @Override
    public String execute(List<String> commands) {
        ValidationHelpers.validateArgumentsCount(commands, NUMBER_OF_PARAMETERS);
        String teamName = commands.get(0);
        String boardName = commands.get(1);
        TaskType taskType = ParsingHelpers.tryParseEnum(commands.get(2),TaskType.class);
        String title = commands.get(3);
        String description = commands.get(4);
        String additionalParam = commands.get(5);

        return addTask(teamName,boardName,taskType,title,description,additionalParam);
    }

    private String addTask(String teamName, String boardName, TaskType taskType, String title, String description,String additionalParam){
       Task task = createTask(taskType,title,description,additionalParam);
       taskManagementRepository.findTeamByName(teamName).findTeamBoardByName(boardName).addBoardTask(task);
       taskManagementRepository.addTask(task);
        return TASK_ADDED_SUCCESSFULLY.formatted(taskType.toString(),task.getId(),teamName,boardName);
    }

    private Task createTask(TaskType taskType, String title, String description,String additionalParam){
        return switch (taskType) {
            case BUG ->
                    taskManagementRepository.createBug(title, description, ParsingHelpers.tryParseEnum(additionalParam, Severity.class));
            case STORY ->
                    taskManagementRepository.createStory(title, description, ParsingHelpers.tryParseEnum(additionalParam, StorySize.class));
            case FEEDBACK ->
                    taskManagementRepository.createFeedback(title, description, Integer.parseInt(additionalParam));
            default -> throw new IllegalArgumentException(CANNOT_CREATE_THIS_TYPE_OF_TASK);
        };
    }
}
