package com.project.oop.tasksmanagement.core.contracts;

import com.project.oop.tasksmanagement.models.contracts.*;
import com.project.oop.tasksmanagement.models.enums.*;

import java.util.List;

public interface TaskManagementRepository {

    List<Team> getTeams();

    List<Developer> getMembers();

    List<Task> getAllTasks();

    List<Bug> getBugs();

    List<Story> getStories();

    List<Feedback> getFeedbacks();

    Team createTeam(String teamName);

    Developer createMember(String memberName);

    Board createBoard(String boardName);

    Comment createComment(String content, String author);

    Bug createBug(String title, String description, Severity severity);

    Feedback createFeedback(String title, String description, int rating);

    Story createStory(String title, String description, StorySize storySize);

    boolean memberExists(String memberName);

    Developer findMemberByName(String memberName);

    Board findBoardByName(String boardName);

    Task findTaskById(int id);

    <T extends Identifiable> T findElementById(List<T> elements, int id);

    Team findTeamByName(String team);

    void addTeam(Team team);

    void addMember(Developer developer);

    void addTask(Task task);

    String printTeams();
    String printDevelopers();
    String printBoards();
    String printTasks();
}
