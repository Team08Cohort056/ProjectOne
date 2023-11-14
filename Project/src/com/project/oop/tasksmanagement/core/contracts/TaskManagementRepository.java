package com.project.oop.tasksmanagement.core.contracts;

import com.project.oop.tasksmanagement.models.contracts.*;
import com.project.oop.tasksmanagement.models.enums.*;

import java.util.List;

public interface TaskManagementRepository {
    List<Team> getTeams();
    List<Member> getMembers();
    Team createTeam(String teamName);
    Member createMember(String memberName);
    Board createBoard(String boardName);

    Bug createBug(String title, String description, Priority priority, Severity severity, Member assignee);

    Feedback createFeedback(String title, String description, int rating);

    Story createStory(String title, String description, Priority priority, StorySize storySize);

    Bug findBugById();

    Feedback findFeedbackById();

    Story findStoryById();

    void addTeam(Team team);

    void addMember(Member member);

}
