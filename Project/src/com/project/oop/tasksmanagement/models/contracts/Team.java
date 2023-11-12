package com.project.oop.tasksmanagement.models.contracts;

import java.lang.reflect.Member;
import java.util.List;

public interface Team extends ActivityHistory{
    String getName();
    List<Member> getMembers();
    List<Board> getBoards();
    void addMember(Member member);

    void addBoard(Board board);


    /* OPTIONAL */
//    void removeMember();
//    void removeBoard();

}
