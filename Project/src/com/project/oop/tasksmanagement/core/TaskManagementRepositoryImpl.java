package com.project.oop.tasksmanagement.core;

import com.project.oop.tasksmanagement.core.contracts.TaskManagementRepository;
import com.project.oop.tasksmanagement.core.exceptions.InvalidUserInputException;
import com.project.oop.tasksmanagement.models.BoardImpl;
import com.project.oop.tasksmanagement.models.CommentImpl;
import com.project.oop.tasksmanagement.models.MemberImpl;
import com.project.oop.tasksmanagement.models.TeamImpl;
import com.project.oop.tasksmanagement.models.contracts.*;
import com.project.oop.tasksmanagement.models.enums.Severity;
import com.project.oop.tasksmanagement.models.enums.Status;
import com.project.oop.tasksmanagement.models.enums.StorySize;
import com.project.oop.tasksmanagement.models.tasks.BugImpl;
import com.project.oop.tasksmanagement.models.tasks.FeedbackImpl;
import com.project.oop.tasksmanagement.models.tasks.StoryImpl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TaskManagementRepositoryImpl implements TaskManagementRepository {

    private static final String NO_TEAMS_FOUND_HEADER = "No teams found.";
    private static final String NO_MEMBERS_FOUND_HEADER = "No members found.";
    private static final String NO_BOARDS_FOUND_HEADER = "No boards found.";
    private static final String NO_TASKS_FOUND_HEADER = "No tasks found.";
    private static final String NO_TASK_WITH_ID_ERR = "No %s with ID %d";
    private static final String NO_TEAM_WITH_NAME = "No team with name %s is found";
    private static final String NO_MEMBER_WITH_NAME_ERR = "No member with name %s is found";
    private static final String NO_BOARD_WITH_NAME_ERR = "No board with name %s is found";
    private static final String NO_STORIES_WITH_THAT_STATUS = "There are no stories with status %s";
    private static final String NO_STORIES_WITH_THAT_CRITERIA = "There are no stories with that criteria";
    private static final String NO_STORIES_WITH_THAT_ASSIGNEE = "There are no stories for that assignee %s";
    private int nextId;
    private final List<Team> teams = new ArrayList<>();
    private final List<Member> members = new ArrayList<>();
    private final List<Task> allTasks = new ArrayList<>();
    private final List<AssignabelTask> allAssignableTasks = new ArrayList<>();
    private final List<AssignabelTask> allAssignedTasks = new ArrayList<>();
    private final List<Bug> bugs = new ArrayList<>();
    private final List<Story> stories = new ArrayList<>();
    private final List<Feedback> feedbacks = new ArrayList<>();

    public TaskManagementRepositoryImpl() {
        nextId = 0;
    }

    @Override
    public int getId() {
        return nextId;
    }

    @Override
    public List<Team> getTeams() {
        return new ArrayList<>(teams);
    }

    @Override
    public List<Member> getMembers() {
        return new ArrayList<>(members);
    }

    @Override
    public List<Task> getAllTasks() {
        return new ArrayList<>(allTasks);
    }

    public List<AssignabelTask> getAllAssignableTasks() {
        return allAssignableTasks;
    }

    public List<AssignabelTask> getAllAssignedTasks() {
        return allAssignedTasks;
    }

    @Override
    public List<Bug> getBugs() {
        return new ArrayList<>(bugs);
    }

    @Override
    public List<Story> getStories() {
        return new ArrayList<>(stories);
    }

    @Override
    public List<Feedback> getFeedbacks() {
        return new ArrayList<>(feedbacks);
    }

    @Override
    public boolean memberExists(String memberName) {
        for (Member member : getMembers()) {
            if (member.getName().equalsIgnoreCase(memberName)) {
               return true;
            }
        }
        return false;
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
    public Comment createComment(String content, String author) {
        return new CommentImpl(content, author);
    }

    @Override
    public Bug createBug(String title, String description, Severity severity) {
        Bug bug = new BugImpl(++nextId, title, description, severity);
        bugs.add(bug);
        allAssignableTasks.add(bug);
        return bug;
    }

    @Override
    public Story createStory(String title, String description, StorySize storySize) {
        Story story = new StoryImpl(++nextId, title, description, storySize);
        stories.add(story);
        allAssignableTasks.add(story);
        return story;
    }

    @Override
    public Feedback createFeedback(String title, String description, int rating) {
        Feedback feedback = new FeedbackImpl(++nextId, title, description, rating);
        feedbacks.add(feedback);
        return feedback;
    }

    public Task findTaskById(int id) {
        return findElementById(allTasks, id);
    }

    public AssignabelTask findAssignableTaskById(int id) {
        return findElementById(allAssignableTasks, id);
    }

    public Bug findBugById(int id) {
        return findElementById(bugs, id);
    }

    public Story findStoryById(int id) {
        return findElementById(stories, id);
    }

    public Feedback findFeedbackById(int id) {
        return findElementById(feedbacks, id);
    }

    private <T extends Identifiable> T findElementById(List<T> elements, int id) {
        for (T element : elements) {
            if (element.getId() == id) {
                return element;
            }
        }
        throw new IllegalArgumentException(NO_TASK_WITH_ID_ERR.formatted(elements.getClass(), id));
    }

    @Override
    public Team findTeamByName(String teamName) {
        for (Team team : getTeams()) {
            if (team.getName().equals(teamName)) {
                return team;
            }
        }
        throw new IllegalArgumentException(String.format(NO_TEAM_WITH_NAME, teamName));
    }

    @Override
    public Member findMemberByName(String memberName) {
        for (Member member : getMembers()) {
            if (member.getName().equals(memberName)) {
                return member;
            }
        }

        throw new InvalidUserInputException(String.format(NO_MEMBER_WITH_NAME_ERR, memberName));
    }

    @Override
    public Board findBoardByName(String boardName) {
        for (Team team : teams) {
            for (int i = 0; i < team.getBoards().size(); i++) {
                if (boardName.equals(team.getBoards().get(i).getName())) {
                    return team.getBoards().get(i);
                }

            }
        }
        throw new IllegalArgumentException(String.format(NO_BOARD_WITH_NAME_ERR, boardName));

    }

    public void addAssignedTask(AssignabelTask task){
        allAssignedTasks.add(task);
    }

    public void removeAssignedTask(AssignabelTask task){
        allAssignedTasks.remove(task);
    }

    @Override
    public void addTeam(Team team) {
        teams.add(team);
    }

    @Override
    public void addMember(Member member) {
        members.add(member);
    }

    public void addTask(Task task) {
        allTasks.add(task);
    }

    @Override
    public String printTeams() {
        StringBuilder result = new StringBuilder();
        int counter = 0;
        if (teams.isEmpty()) {
            result.append(NO_TEAMS_FOUND_HEADER);
            return result.toString();
        }
        for (Team team : teams) {
            counter++;
            result.append(counter).append(".").append(team.getName()).append(System.lineSeparator());
        }
        result.deleteCharAt(result.length() - 2);
        return result.toString();
    }

    @Override
    public String printMembers() {
        StringBuilder result = new StringBuilder();
        int counter = 1;
        if (members.isEmpty()) {
            return result.append(NO_MEMBERS_FOUND_HEADER).toString();
        }
        for (Member member : members) {
            result.append(counter).append(".").append(member.getName()).append(System.lineSeparator());
            counter++;
        }
        return result.toString();
    }

    @Override
    public String printBoards() {
        StringBuilder result = new StringBuilder();
        int counter = 1;
        boolean hasBoard = false;
        if (teams.isEmpty()) {
            return result.append(NO_BOARDS_FOUND_HEADER).toString();
        }
        for (Team team : teams) {
            if (!team.getBoards().isEmpty()) {
                hasBoard = true;
                break;
            }
        }
        if (!hasBoard) {
            return result.append(NO_BOARDS_FOUND_HEADER).toString();
        }
        for (Team team : teams) {
            if (!team.getBoards().isEmpty()) {
                for (Board board : team.getBoards()) {
                    result.append(counter).append(".").append(board.getName()).append(System.lineSeparator());
                    counter++;
                }
            }
        }
        return result.toString();
    }
    @Override
    public String printTasks() {
        int counter = 1;
        StringBuilder result = new StringBuilder();
        if (allTasks.isEmpty()) {
            return result.append(NO_TASKS_FOUND_HEADER).toString();
        }
        for (Task task : allTasks) {
            result.append(counter).append(".").append(task.getTitle()).append(System.lineSeparator());
            counter++;
        }
        return result.toString();
    }


}
