package com.project.oop.tasksmanagement.core.contracts;

import com.project.oop.tasksmanagement.models.contracts.*;
import com.project.oop.tasksmanagement.models.enums.*;

import java.util.List;

public interface TaskManagementRepository {

    List<Team> getTeams();

    List<Member> getMembers();

    List<Task> getAllTasks();

    List<Bug> getBugs();

    List<Story> getStories();

    List<Feedback> getFeedbacks();

    Team createTeam(String teamName);

    Member createMember(String memberName);

    Board createBoard(String boardName);

    Comment createComment(String content, String author);

    Bug createBug(String title, String description, Severity severity);

    Feedback createFeedback(String title, String description, int rating);

    Story createStory(String title, String description, StorySize storySize);

    boolean memberExists(String memberName);

    Task findTaskById(int id);

    AssignabelTask findAssignableTaskById(int id);

    Member findMemberByName(String memberName);

    Board findBoardByName(String boardName);

    Team findTeamByName(String team);

    void addTeam(Team team);

    void addMember(Member member);

    void addTask(Task task);

    String printTeams();
    String printMembers();
    String printBoards();
    String printTasks();
    int getId();
}
