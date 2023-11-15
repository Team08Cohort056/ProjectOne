package com.project.oop.tasksmanagement.core;

import com.project.oop.tasksmanagement.core.contracts.TaskManagementRepository;
import com.project.oop.tasksmanagement.core.exceptions.InvalidUserInputException;
import com.project.oop.tasksmanagement.models.BoardImpl;
import com.project.oop.tasksmanagement.models.DeveloperImpl;
import com.project.oop.tasksmanagement.models.TeamImpl;
import com.project.oop.tasksmanagement.models.contracts.*;
import com.project.oop.tasksmanagement.models.enums.Priority;
import com.project.oop.tasksmanagement.models.enums.Severity;
import com.project.oop.tasksmanagement.models.enums.StorySize;
import com.project.oop.tasksmanagement.models.tasks.BugImpl;
import com.project.oop.tasksmanagement.models.tasks.FeedbackImpl;
import com.project.oop.tasksmanagement.models.tasks.StoryImpl;

import java.util.ArrayList;
import java.util.List;

public class TaskManagementRepositoryImpl implements TaskManagementRepository {

    int nextId;
    private final List<Team> teams;
    private final List<Developer> developers;
    public TaskManagementRepositoryImpl(){
        this.teams = new ArrayList<>();
        this.developers = new ArrayList<>();
        nextId = 0;
    }

    @Override
    public List<Team> getTeams() {
        return new ArrayList<>(teams);
    }

    @Override
    public List<Developer> getMembers() {
        return new ArrayList<>(developers);
    }

    @Override
    public boolean memberExists(String memberName) {
        boolean exists = false;

        for (Developer developer : getMembers()) {
            if (developer.getName().equalsIgnoreCase(memberName)) {
                exists = true;
                break;
            }
        }

        return exists;
    }

    @Override
    public Team createTeam(String teamName) {
        return new TeamImpl(teamName);
    }

    @Override
    public Developer createMember(String memberName) {
        return new DeveloperImpl(memberName);
    }

    @Override
    public Board createBoard(String boardName) {
        return new BoardImpl(boardName);
    }

    @Override
    public Bug createBug(String title, String description, Priority priority, Severity severity, Developer assignee) {
        return new BugImpl(++nextId, title, description, priority, severity, assignee);
    }


    @Override
    public Feedback createFeedback(String title, String description, int rating) {
        return new FeedbackImpl(++nextId, title, description, rating);
    }

    @Override
    public Story createStory(String title, String description, Priority priority, StorySize storySize) {
        return new StoryImpl(++nextId, title, description, priority, storySize);
    }

    @Override
    public Bug findBugById() {
        return null;
    }

    @Override
    public Feedback findFeedbackById() {
        return null;
    }

    @Override
    public Story findStoryById() {
        return null;
    }

    private <T extends Identifiable> T findElementById(List<T> elements, int id) {
        for (T element : elements) {
            if (element.getId() == id) {
                return element;
            }
        }
        throw new IllegalArgumentException(String.format("No record with ID %d", id));
    }

    @Override
    public Team findTeamByName(String teamName) {
        for (Team team : getTeams()) {
            if (team.getName().equals(teamName)) {
                return team;
            }
        }
        throw new IllegalArgumentException(String.format("No team with name %s", teamName));
    }

    @Override
    public Developer findMemberByName(String memberName) {
        for (Developer developer : getMembers()) {
            if (developer.getName().equals(memberName)) {
                return developer;
            }
        }

        throw new InvalidUserInputException(String.format("No record for this member name %s", memberName));
    }

    @Override
    public void addTeam(Team team) {
        //add validation
        teams.add(team);
    }

    @Override
    public void addMember(Developer developer) {
        developers.add(developer);
    }

}
