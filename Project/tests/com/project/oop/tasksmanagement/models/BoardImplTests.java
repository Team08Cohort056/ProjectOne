package com.project.oop.tasksmanagement.models;

import com.project.oop.tasksmanagement.models.contracts.Board;
import com.project.oop.tasksmanagement.models.tasks.FeedbackImplTests;
import com.project.oop.tasksmanagement.utils.BaseConstants;
import com.project.oop.tasksmanagement.utils.EventLog;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BoardImplTests {
    Board board;
    @BeforeEach
    public void before() {
        board = initializeTestBoard();
    }

    @Test
    public void BoardImpl_Should_ImplementBoardInterface(){
        Assertions.assertTrue(board instanceof Board);
    }

    @Test
    public void constructor_Should_Throw_Exception_When_NameLengthOutOfBounds(){
        Assertions.assertThrows(IllegalArgumentException.class, () ->
                new BoardImpl(BaseConstants.INVALID_NAME));
    }

    @Test
    public void constructor_Should_CreateNewBoard_When_ParameterIsCorrect(){
        Assertions.assertEquals(BaseConstants.VALID_NAME, board.getName());
    }
    @Test
    public void getBoardTasks_Should_ReturnCopyOfTheCollection() {
        board.getBoardTasks().add(FeedbackImplTests.initializeTestFeedback());
        Assertions.assertEquals(0,board.getBoardTasks().size());
    }
    @Test
    public void getActivityHistory_Should_ReturnCopyOfTheCollection() {
        board.getActivityHistory().add(new EventLog("test"));
        Assertions.assertEquals(1,board.getActivityHistory().size());
    }

    @Test
    public void addBoardTask_Should_AddTaskToCollection(){
        board.addBoardTask(FeedbackImplTests.initializeTestFeedback());
        Assertions.assertEquals(1,board.getBoardTasks().size());
    }

//    public void constructor_Should_Throw_Exception_When_BoardWithSameNameAlreadyExistInThisTeam(){
//        Assertions.assertThrows()
//    }

    public static BoardImpl initializeTestBoard(){
        return  new BoardImpl(BaseConstants.VALID_NAME);
    }
}
