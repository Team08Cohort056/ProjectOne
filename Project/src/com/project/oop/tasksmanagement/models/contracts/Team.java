package com.project.oop.tasksmanagement.models.contracts;

import java.util.List;

public interface Team extends ActivityHistory{
    String getName();
    List<Developer> getDevelopers();
    List<Board> getBoards();
    void addDeveloper(Developer developer);

    void addBoard(Board board);
    String printTeamDevelopers();
    String printTeamBoards();


    /* OPTIONAL */
//    void removeMember();
//    void removeBoard();

}
