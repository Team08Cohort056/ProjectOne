package com.project.oop.tasksmanagement.models.contracts;

import java.util.List;

public interface Team extends ActivityHistory{
    String getName();
    List<Member> getMembers();
    List<Board> getBoards();
    void addMember(Member member);

    void addBoard(Board board);
     Board findTeamBoardByName(String boardName);
    String printTeamMembers();
    String printTeamBoards();


    /* OPTIONAL */
//    void removeMember();
//    void removeBoard();

}
