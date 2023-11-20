package com.project.oop.tasksmanagement.commands;

import com.project.oop.tasksmanagement.commands.contracts.BaseCommand;
import com.project.oop.tasksmanagement.core.contracts.TaskManagementRepository;
import com.project.oop.tasksmanagement.models.contracts.Comment;
import com.project.oop.tasksmanagement.models.contracts.Member;
import com.project.oop.tasksmanagement.models.contracts.Task;
import com.project.oop.tasksmanagement.utils.ParsingHelpers;
import com.project.oop.tasksmanagement.utils.ValidationHelpers;

import java.util.List;

public class AddCommentCommand implements BaseCommand {

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;
    public static final String INVALID_INPUT_MESSAGE = "Invalid input. Expected a number for ID.";
    public final static String COMMENT_ADDED_SUCCESSFULLY = "%s added comment successfully!";
    public final static String TASK_DOES_NOT_EXIST = "The task does not exist!";
    private final TaskManagementRepository taskManagementRepository;

    public AddCommentCommand(TaskManagementRepository taskManagementRepository) {
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> commands) {
        ValidationHelpers.validateArgumentsCount(commands, EXPECTED_NUMBER_OF_ARGUMENTS);
        String author = commands.get(0);
        String content = commands.get(1);
        int taskId = ParsingHelpers.tryParseInt(commands.get(2), INVALID_INPUT_MESSAGE) - 1;
        return addComment(author, content, taskId);
    }

    private String addComment(String author, String content, int taskId) {
        Member member = taskManagementRepository.findMemberByName(author);

        ValidationHelpers.validateIntRange(taskId, 0, taskManagementRepository.getAllTasks().size() - 1, TASK_DOES_NOT_EXIST);


        Task task = taskManagementRepository.getAllTasks().get(taskId);

        Comment comment = taskManagementRepository.createComment(content, String.valueOf(member));

        member.addComment(comment, task);

        return String.format(COMMENT_ADDED_SUCCESSFULLY, author);
    }
}
