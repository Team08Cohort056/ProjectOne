package com.project.oop.tasksmanagement.commands;

import com.project.oop.tasksmanagement.commands.contracts.BaseCommand;
import com.project.oop.tasksmanagement.core.contracts.TaskManagementRepository;
import com.project.oop.tasksmanagement.models.BoardImpl;
import com.project.oop.tasksmanagement.models.contracts.Board;
import com.project.oop.tasksmanagement.models.contracts.Team;
import com.project.oop.tasksmanagement.utils.ValidationHelpers;

import java.util.List;

public class AddBoardCommand implements BaseCommand {
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 2;
    public static final String BOARD_ADDED_SUCCESSFULLY = "Board %s added successfully to team %s!";
    public static final String BOARD_ALREADY_EXIST = "Board with name %s already exists in team %s.";
    private final TaskManagementRepository taskManagementRepository;

    public AddBoardCommand(TaskManagementRepository taskManagementRepository){
        this.taskManagementRepository = taskManagementRepository;
    }

    @Override
    public String execute(List<String> parameters) {
        ValidationHelpers.validateArgumentsCount(parameters,EXPECTED_NUMBER_OF_ARGUMENTS);
        String boardName = parameters.get(0);
        String team = parameters.get(1);
        return addBoard(boardName,team);
    }
    private String addBoard(String boardName, String team){
        if (doBoardExistInTeam(boardName,team)){
            throw new IllegalArgumentException(BOARD_ALREADY_EXIST.formatted(boardName,team));
        }
        Board board = taskManagementRepository.createBoard(boardName);
        taskManagementRepository.findTeamByName(team).addBoard(board);

        return BOARD_ADDED_SUCCESSFULLY.formatted(boardName,team);
    }

    private boolean doBoardExistInTeam(String boardName,String team){
        List<Board> boards = taskManagementRepository.findTeamByName(team).getBoards();
        for (Board board: boards) {
            if (board.getName().equals(boardName)){
                return true;
            }
        }
        return false;
    }
}
