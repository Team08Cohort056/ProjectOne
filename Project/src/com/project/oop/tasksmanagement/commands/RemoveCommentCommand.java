package com.project.oop.tasksmanagement.commands;

import com.project.oop.tasksmanagement.commands.contracts.BaseCommand;
import com.project.oop.tasksmanagement.core.contracts.TaskManagementRepository;
import com.project.oop.tasksmanagement.models.contracts.Comment;
import com.project.oop.tasksmanagement.models.contracts.Developer;
import com.project.oop.tasksmanagement.models.contracts.Task;
import com.project.oop.tasksmanagement.utils.ParsingHelpers;
import com.project.oop.tasksmanagement.utils.ValidationHelpers;

import java.util.List;

public class RemoveCommentCommand implements BaseCommand {


    private final static String COMMENT_INDEX_OUT_OF_BOUNDS = "There is no comment on this index.";
    public final static String INVALID_COMMENT_INDEX = "Invalid comment index. Expected a number.";
    public final static String COMMENT_REMOVED_SUCCESSFULLY = "%s removed comment successfully!";
    private final static String INVALID_TASK_ID = "Invalid task id. Expected a number.";
    private final static String TASK_ID_OUT_OF_BOUNDS = "There is no such task";

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;

    private final TaskManagementRepository taskManagementRepository;

    public RemoveCommentCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }


    @Override
    public String execute(List<String> commands) {
        ValidationHelpers.validateArgumentsCount(commands, EXPECTED_NUMBER_OF_ARGUMENTS);
        int taskId = ParsingHelpers.tryParseInt(commands.get(0), INVALID_TASK_ID) - 1;
        int commentIndex = ParsingHelpers.tryParseInt(commands.get(1), INVALID_COMMENT_INDEX) - 1;
        String author = commands.get(2);

        return removeComment(author, taskId, commentIndex);
    }

    private String removeComment(String author,int taskId, int commentIndex) {
        Developer developer = taskManagementRepository.findMemberByName(author);

        ValidationHelpers.validateIntRange(taskId, 0, taskManagementRepository.getAllTasks().size() - 1, TASK_ID_OUT_OF_BOUNDS);
        ValidationHelpers.validateIntRange(commentIndex, 0,
                taskManagementRepository.getAllTasks().get(taskId).getComments().size() - 1, COMMENT_INDEX_OUT_OF_BOUNDS);

        Task task = taskManagementRepository.getAllTasks().get(taskId);
        Comment comment = taskManagementRepository.getAllTasks().get(taskId).getComments().get(commentIndex);

        developer.removeComment(comment, task);

        return String.format(COMMENT_REMOVED_SUCCESSFULLY, author);
    }
}