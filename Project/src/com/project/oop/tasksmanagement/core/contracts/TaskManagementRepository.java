package com.project.oop.tasksmanagement.core.contracts;

import com.project.oop.tasksmanagement.models.contracts.*;
import com.project.oop.tasksmanagement.models.enums.*;

import java.util.List;

public interface TaskManagementRepository {
    List<Team> getTeams();
    List<Developer> getMembers();
    List<Task> getTasks();
    List<Bug> getBugs();
    List<Story> getStories();
    List<Feedback> getFeedbacks();
    Team createTeam(String teamName);
    boolean memberExists(String memberName);
    public Developer findMemberByName(String memberName);
    Developer createMember(String memberName);
    Board createBoard(String boardName);
    Comment createComment(String content, String author);
    Board findBoardByName(String boardName);

    Bug createBug(String title, String description, Severity severity);

    Feedback createFeedback(String title, String description, int rating);

    Story createStory(String title, String description, StorySize storySize);

    Bug findBugById();

    Feedback findFeedbackById();

    Story findStoryById();
    Team findTeamByName(String team);


    void addTeam(Team team);

    void addMember(Developer developer);

    String printTeams();
    String printDevelopers();
    String printBoards();
}
