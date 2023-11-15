package com.project.oop.tasksmanagement.core;

import com.project.oop.tasksmanagement.core.contracts.TaskManagementRepository;
import com.project.oop.tasksmanagement.core.exceptions.InvalidUserInputException;
import com.project.oop.tasksmanagement.models.BoardImpl;
import com.project.oop.tasksmanagement.models.MemberImpl;
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
    private List<Team> teams;
    private List<Member> members;
    public TaskManagementRepositoryImpl(){ nextId = 0; }

    @Override
    public List<Team> getTeams() {
        return new ArrayList<>(teams);
    }

    @Override
    public List<Member> getMembers() {
        return new ArrayList<>(members);
    }

    @Override
    public boolean memberExists(String memberName) {
        boolean exists = false;

        for (Member member : getMembers()) {
            if (member.getName().equalsIgnoreCase(memberName)) {
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
    public Member createMember(String memberName) {
        return new MemberImpl(memberName);
    }

    @Override
    public Board createBoard(String boardName) {
        return new BoardImpl(boardName);
    }

    @Override
    public Bug createBug(String title, String description, Priority priority, Severity severity, Member assignee) {
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
    public Member findMemberByName(String memberName) {
        for (Member member : getMembers()) {
            if (member.getName().equals(memberName)) {
                return member;
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
    public void addMember(Member member) {
        members.add(member);
    }

}
