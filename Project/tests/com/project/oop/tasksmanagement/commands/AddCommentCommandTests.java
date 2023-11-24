package com.project.oop.tasksmanagement.commands;

import com.project.oop.tasksmanagement.commands.addcreatecommands.AddCommentCommand;
import com.project.oop.tasksmanagement.commands.contracts.BaseCommand;
import com.project.oop.tasksmanagement.core.TaskManagementRepositoryImpl;
import com.project.oop.tasksmanagement.core.contracts.TaskManagementRepository;
import com.project.oop.tasksmanagement.models.*;
import com.project.oop.tasksmanagement.models.contracts.Board;
import com.project.oop.tasksmanagement.models.contracts.Member;
import com.project.oop.tasksmanagement.utils.UtilsTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.List;

public class AddCommentCommandTests {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;
    private static final String INVALID_ID = "ID should be a positive number starting from one";


    private TaskManagementRepository taskManagementRepository;
    private BaseCommand addCommentCommand;
    private Member member;
    private Board board;

    @BeforeEach
    public void before() {
        taskManagementRepository = new TaskManagementRepositoryImpl();
        addCommentCommand = new AddCommentCommand(taskManagementRepository);
        member = new MemberImpl(MemberImplTests.VALID_MEMBER_NAME);
        board = new BoardImpl(MemberImplTests.VALID_BOARD_NAME);

    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = UtilsTests.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        //  Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> addCommentCommand.execute(params));
    }

    @Test
    public void should_ThrowException_When_TaskIdIsNotNumber() {
        // Arrange
        List<String> params = List.of(
                CommentImplTests.VALID_CONTENT,
                board.getName(),
                member.getName(),
                INVALID_ID);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> addCommentCommand.execute(params));
    }

    @Test
    public void should_ThrowException_When_TaskDoesNotExist() {
        // Arrange
        List<String> params = List.of(
                CommentImplTests.VALID_CONTENT,
                board.getName(),
                member.getName(),
                "1");

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> addCommentCommand.execute(params));
    }

    //
   // @Test
   // public void should_CreateComment_When_InputIsValid() {
        // Arrange
      //  member.addTask(StoryImplTests.initializeTestStory());

       // List<String> params = List.of(
              //  CommentImplTests.VALID_CONTENT,
              //  board.getName(),
             //   member.getName(),
             //   "1");

        //  Act
       // addCommentCommand.execute(params);

        // Assert
       // Comment comment = taskManagementRepository.getAllTasks().get(0).getComments().get(0);
       // Assertions.assertEquals(CommentImplTests.VALID_CONTENT, comment.getContent());
       // Assertions.assertEquals(member.getName(), comment.getAuthor());
    }

