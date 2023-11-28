package com.project.oop.tasksmanagement.models.tasks;

import com.project.oop.tasksmanagement.models.contracts.Bug;
import com.project.oop.tasksmanagement.models.enums.Priority;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssignableTaskImplTest {
    Bug bug;
    @BeforeEach
    public void before(){
        bug = BugImplTests.initializeTestBug();
    }

    @Test
    public void changePriority_Should_ChangePriority_When_CorrectArgument() {
        bug.changePriority(Priority.HIGH);
        Assertions.assertEquals(Priority.HIGH,bug.getPriority());
    }
    @Test
    public void changePriority_Should_ThrowException_When_IncorrectArgument() {
       Assertions.assertThrows(IllegalArgumentException.class,
               ()->bug.changePriority(Priority.valueOf("incorrect")));
    }

    @Test
    void assignTaskTo_Should_AssignTaskToDeveloper() {
        bug.assignTaskTo("dev");
        Assertions.assertEquals("dev",bug.getAssignee());
    }

    @Test
    void unAssignTask_Should_PutAssignStatusToNotAssined() {
        bug.assignTaskTo("dev");
        bug.unAssignTask();
        Assertions.assertEquals("Not assigned",bug.getAssignee());
    }
}