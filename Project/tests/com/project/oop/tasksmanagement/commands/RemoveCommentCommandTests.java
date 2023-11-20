package com.project.oop.tasksmanagement.commands;

import com.project.oop.tasksmanagement.core.TaskManagementRepositoryImpl;
import com.project.oop.tasksmanagement.core.contracts.TaskManagementRepository;
import com.project.oop.tasksmanagement.models.CommentImplTests;
import com.project.oop.tasksmanagement.models.MemberImpl;
import com.project.oop.tasksmanagement.models.MemberImplTests;
import com.project.oop.tasksmanagement.models.StoryImplTests;
import com.project.oop.tasksmanagement.models.contracts.Comment;
import com.project.oop.tasksmanagement.models.contracts.Member;
import com.project.oop.tasksmanagement.models.contracts.Story;
import com.project.oop.tasksmanagement.utils.UtilsTests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class RemoveCommentCommandTests {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 3;

    private RemoveCommentCommand removeCommentCommand;
    private Member member;

    @BeforeEach
    public void before() {
        TaskManagementRepository taskManagementRepository = new TaskManagementRepositoryImpl();
        removeCommentCommand = new RemoveCommentCommand(taskManagementRepository);
        member = new MemberImpl(MemberImplTests.VALID_MEMBER_NAME);
    }

    @Test
    public void should_ThrowException_When_ArgumentCountDifferentThanExpected() {
        // Arrange
        List<String> params = UtilsTests.getList(EXPECTED_NUMBER_OF_ARGUMENTS - 1);

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> removeCommentCommand.execute(params));
    }

    @Test
    public void should_ThrowException_When_CommentIndexIsInvalid() {
        // Arrange
        Comment testComment = CommentImplTests.initializeTestComment();
        Story testStory = StoryImplTests.initializeTestStory();
        member.addComment(testComment, testStory);
        List<String> params = List.of(
                "INVALID_INDEX",
                "1",
                member.getName());

        // Act, Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> removeCommentCommand.execute(params));
    }

   // @Test
   // public void should_ThrowException_When_TaskDoesNotExist() {
        // Arrange
       // Comment testComment = CommentImplTests.initializeTestComment();
       // Story testStory = StoryImplTests.initializeTestStory();
      //  member.addComment(testComment, testStory);
      //  List<String> params = List.of(
              //  "1",
              //  "1",
             //   member.getName());

        // Act, Assert
      //  Assertions.assertThrows(IllegalArgumentException.class, () -> removeCommentCommand.execute(params));
  //  }

   // @Test
   // public void should_ThrowException_When_CommentDoesNotExist() {
        // Arrange
      //  Story testStory = StoryImplTests.initializeTestStory();
      //  member.addTask(testStory);
      //  List<String> params = List.of(
             //   "1",
            //    "1",
            //    member.getName());

        // Act, Assert
      //  Assertions.assertThrows(IllegalArgumentException.class, () -> removeCommentCommand.execute(params));
   // }


   // @Test
  //  public void should_RemoveCommentFromUser_When_ArgumentsAreValid() {
        // Arrange
       // Comment testComment = CommentImplTests.initializeTestComment();
      //  Story testStory = StoryImplTests.initializeTestStory();
      //  member.addTask(testStory);
      //  member.addComment(testComment, testStory);

        // Act
      // removeCommentCommand.execute(List.of(
             //   "1",
              //  "1",
              //  member.getName()
       // ));

        // Act, Assert
       // Assertions.assertEquals(0, member.getTasks().get(0).getComments().size());
   // }
}
