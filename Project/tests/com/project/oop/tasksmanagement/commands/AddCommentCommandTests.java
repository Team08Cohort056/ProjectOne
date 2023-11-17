package com.project.oop.tasksmanagement.commands;

// import com.project.oop.tasksmanagement.commands.contracts.BaseCommand;
// import com.project.oop.tasksmanagement.core.TaskManagementRepositoryImpl;
// import com.project.oop.tasksmanagement.core.contracts.TaskManagementRepository;
// import com.project.oop.tasksmanagement.models.BoardImpl;
// import com.project.oop.tasksmanagement.models.DeveloperImpl;
// import com.project.oop.tasksmanagement.models.DeveloperImplTests;
// import com.project.oop.tasksmanagement.models.StoryImplTests;
// import com.project.oop.tasksmanagement.models.contracts.Board;
// import com.project.oop.tasksmanagement.models.contracts.Comment;
// import com.project.oop.tasksmanagement.models.contracts.Developer;
// import com.project.oop.tasksmanagement.utils.UtilsTests;
// import org.junit.jupiter.api.Assertions;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;

// import java.util.List;

public class AddCommentCommandTests {
    // TODO
   // public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;
   // private static final String INVALID_ID = "ID should be a positive number starting from one";

   // private TaskManagementRepository taskManagementRepository;
   // private BaseCommand addCommentCommand;
   // private Developer developer;
   // private Board board;

   // @BeforeEach
   // public void before() {
      //  taskManagementRepository = new TaskManagementRepositoryImpl();
       // addCommentCommand = new AddCommentCommand(taskManagementRepository);
       // developer = new DeveloperImpl(DeveloperImplTests.VALID_DEVELOPER_NAME);
       // board = new BoardImpl(DeveloperImplTests.VALID_BOARD_NAME);

 //   }

  //  @Test
  //  public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
      //  List<String> params = UtilsTests.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        // Act, Assert
      //  Assertions.assertThrows(IllegalArgumentException.class, () -> addCommentCommand.execute(params));
    }

   // @Test
   // public void should_ThrowException_When_TaskIdIsNotNumber() {
        // Arrange
      //  List<String> params = List.of(
              //  CommentImplTests.VALID_CONTENT,
             //   board.getName(),
             //   developer.getName(),
            //    INVALID_ID);

        // Act, Assert
    //    Assertions.assertThrows(IllegalArgumentException.class, () -> addCommentCommand.execute(params));
    // }

   // @Test
   // public void should_ThrowException_When_TaskDoesNotExist() {
        // Arrange
      //  List<String> params = List.of(
               // CommentImplTests.VALID_CONTENT,
             //   board.getName(),
           //     developer.getName(),
         //       "1");

        // Act, Assert
      //  Assertions.assertThrows(IllegalArgumentException.class, () -> addCommentCommand.execute(params));
    // }

   // @Test
   // public void should_CreateComment_When_InputIsValid() {
        // Arrange
      //  developer.addTask(StoryImplTests.initializeTestStory());

      //  List<String> params = List.of(
               // CommentImplTests.VALID_CONTENT,
           //     board.getName(),
         //       developer.getName(),
         //       "1");

        // Act
       // addCommentCommand.execute(params);

        //Assert
      //  Comment comment = taskManagementRepository.getAllTasks().get(0).getComments().get(0);
       // Assertions.assertEquals(CommentImplTests.VALID_CONTENT, comment.getContent());
      //  Assertions.assertEquals(developer.getName(), comment.getAuthor());
    //}
//}
