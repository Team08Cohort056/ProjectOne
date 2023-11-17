package com.project.oop.tasksmanagement.commands;

import com.project.oop.tasksmanagement.commands.contracts.BaseCommand;
import com.project.oop.tasksmanagement.core.contracts.TaskManagementRepository;
import com.project.oop.tasksmanagement.models.contracts.Bug;
import com.project.oop.tasksmanagement.models.contracts.Feedback;
import com.project.oop.tasksmanagement.models.contracts.Story;
import com.project.oop.tasksmanagement.models.contracts.Task;
import com.project.oop.tasksmanagement.models.enums.*;
import com.project.oop.tasksmanagement.utils.ParsingHelpers;
import com.project.oop.tasksmanagement.utils.ValidationHelpers;

import java.util.List;

public class ChangeTaskCommand implements BaseCommand {
    private static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;
    private static final String BUG_ERR_MESSAGE = "Bug tasks can have only Priority, Severity or Status changed.";

    private static final String STORY_ERR_MESSAGE = "Story tasks can have only Priority, Story size or Status changed.";

    private static final String FEEDBACK_ERR_MESSAGE = "Feedback tasks can have only Rating or Status changed.";

    private static final String CHANGE_COMPLETED_SUCCESSFULLY = "%s change completed successfully !";

    private static final String ERR_MESSAGE_IF_ID_NOT_INTEGER = "First argument is the ID and should be integer.";

    private static final String FEEDBACK_RATING_SHOULD_BE_INTEGER_ERR = "Feedback rating should be integer.";

    private final TaskManagementRepository taskManagementRepository;

    public ChangeTaskCommand(TaskManagementRepository taskManagementRepository){
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> commands) {
        ValidationHelpers.validateArgumentsCount(commands,EXPECTED_NUMBER_OF_ARGUMENTS);
        int id = ParsingHelpers.tryParseInt(commands.get(0), ERR_MESSAGE_IF_ID_NOT_INTEGER);
        String enumToBeChanged = commands.get(1);
        String newValue = commands.get(2);

        changeEnumValue(id,enumToBeChanged,newValue);
        return CHANGE_COMPLETED_SUCCESSFULLY.formatted(enumToBeChanged);
    }

    private void changeEnumValue(int id, String enumToBeChanged, String newValue){
        Task task = taskManagementRepository.findTaskById(id);
        switch (task.getTaskType()) {
            case BUG -> {
                Bug bug = (Bug) task;
                switch (enumToBeChanged.toLowerCase()) {
                    case "priority" -> bug.changeBugPriority(ParsingHelpers.tryParseEnum(newValue, Priority.class));
                    case "severity" -> bug.changeBugSeverity(ParsingHelpers.tryParseEnum(newValue, Severity.class));
                    case "status" -> bug.changeBugStatus(ParsingHelpers.tryParseEnum(newValue, BugStatus.class));
                    default -> throw new IllegalArgumentException(BUG_ERR_MESSAGE);
                }
            }
            case STORY -> {
                Story story = (Story) task;
                switch (enumToBeChanged.toLowerCase()) {
                    case "priority" -> story.changeStoryPriority(ParsingHelpers.tryParseEnum(newValue, Priority.class));
                    case "size" -> story.changeStorySize(ParsingHelpers.tryParseEnum(newValue, StorySize.class));
                    case "status" -> story.changeStoryStatus(ParsingHelpers.tryParseEnum(newValue, StoryStatus.class));
                    default -> throw new IllegalArgumentException(STORY_ERR_MESSAGE);
                }
            }
            case FEEDBACK -> {
                Feedback feedback = (Feedback) task;
                switch (enumToBeChanged.toLowerCase()) {
                    case "rating" ->
                            feedback.changeFeedbackRating(ParsingHelpers.tryParseInt(newValue, FEEDBACK_RATING_SHOULD_BE_INTEGER_ERR));
                    case "status" ->
                            feedback.changeFeedbackStatus(ParsingHelpers.tryParseEnum(newValue, FeedbackStatus.class));
                    default -> throw new IllegalArgumentException(FEEDBACK_ERR_MESSAGE);
                }
            }
        }
    }
}
