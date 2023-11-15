package com.project.oop.tasksmanagement.models.contracts;

import java.util.List;

public interface Team extends ActivityHistory{
    String getName();
    List<Developer> getDevelopers();
    List<Board> getBoards();
    void addDeveloper(Developer developer);

    void addBoard(Board board);


    /* OPTIONAL */
//    void removeMember();
//    void removeBoard();

}
