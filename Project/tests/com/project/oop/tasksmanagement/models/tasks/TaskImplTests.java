package com.project.oop.tasksmanagement.models.tasks;

import com.project.oop.tasksmanagement.models.CommentImplTests;
import com.project.oop.tasksmanagement.models.contracts.Bug;
import com.project.oop.tasksmanagement.models.contracts.Comment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TaskImplTests {
    @Test
    public void removeComment_Should_RemoveCommentFromTheTask(){
       Bug bug = BugImplTests.initializeTestBug();
        Comment comment = CommentImplTests.initializeTestComment();
        bug.addComment(comment);
        bug.addComment(comment);
        bug.removeComment(comment);
        Assertions.assertEquals(1,bug.getComments().size());
    }
}
