package com.project.oop.tasksmanagement.core;

import com.project.oop.tasksmanagement.core.contracts.TaskManagementRepository;
import com.project.oop.tasksmanagement.core.exceptions.InvalidUserInputException;
import com.project.oop.tasksmanagement.models.BoardImpl;
import com.project.oop.tasksmanagement.models.CommentImpl;
import com.project.oop.tasksmanagement.models.DeveloperImpl;
import com.project.oop.tasksmanagement.models.TeamImpl;
import com.project.oop.tasksmanagement.models.contracts.*;
import com.project.oop.tasksmanagement.models.enums.Severity;
import com.project.oop.tasksmanagement.models.enums.StorySize;
import com.project.oop.tasksmanagement.models.tasks.BugImpl;
import com.project.oop.tasksmanagement.models.tasks.FeedbackImpl;
import com.project.oop.tasksmanagement.models.tasks.StoryImpl;

import java.util.ArrayList;
import java.util.List;

public class TaskManagementRepositoryImpl implements TaskManagementRepository {

    public static final String NO_TEAMS_FOUND_HEADER = "No teams found.";
    public static final String NO_DEVELOPERS_FOUND_HEADER = "No developers found.";
    public static final String NO_BOARDS_FOUND_HEADER = "No boards found.";
    int nextId;
    private final List<Team> teams = new ArrayList<>();
    private final List<Developer> developers = new ArrayList<>();
    private final List<Task> allTasks = new ArrayList<>();
    private final List<Bug> bugs = new ArrayList<>();
    private final List<Story> stories = new ArrayList<>();
    private final List<Feedback> feedbacks = new ArrayList<>();

    public TaskManagementRepositoryImpl() {
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
    public List<Task> getAllTasks() {return new ArrayList<>(allTasks);
    }

    @Override
    public List<Bug> getBugs() {return new ArrayList<>(bugs);}

    @Override
    public List<Story> getStories() {return new ArrayList<>(stories);}

    @Override
    public List<Feedback> getFeedbacks() {return new ArrayList<>(feedbacks);}

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
    public Comment createComment(String content, String author) {
        return new CommentImpl(content, author);
    }

    @Override
    public Board findBoardByName(String boardName) {
        for (Team team : teams) {
            for (int i = 0; i < team.getBoards().size(); i++) {
                if (boardName.equals(team.getBoards().get(i).getName())){
                    return team.getBoards().get(i);
                }

            }
        }
        throw new IllegalArgumentException(String.format("No board with name %s", boardName));

    }

    @Override
    public Bug createBug(String title, String description, Severity severity) {
        Bug bug = new BugImpl(++nextId, title, description, severity);
        bugs.add(bug);
        return bug;
    }


    @Override
    public Story createStory(String title, String description, StorySize storySize) {
       Story story = new StoryImpl(++nextId, title, description, storySize);
       stories.add(story);
       return story;
    }

    @Override
    public Feedback createFeedback(String title, String description, int rating) {
        Feedback feedback = new FeedbackImpl(++nextId, title, description, rating);
        feedbacks.add(feedback);
        return feedback;
    }

    public Task findTaskById(int id){
        for (Task task: allTasks) {
            if (task.getId() == id){
                return task;
            }
        }
        throw new IllegalArgumentException(String.format("No task with ID %d", id));
    }
    public  <T extends Identifiable> T findElementById(List<T> elements, int id) {
        for (T element : elements) {
            if (element.getId() == id) {
                return element;
            }
        }
        throw new IllegalArgumentException(String.format("No task with ID %d", id));
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
        teams.add(team);
    }

    @Override
    public void addMember(Developer developer) {
        developers.add(developer);
    }

    public void addTask(Task task){
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
    public String printDevelopers() {
        StringBuilder result = new StringBuilder();
        int counter = 1;
        if (developers.isEmpty()) {
            return result.append(NO_DEVELOPERS_FOUND_HEADER).toString();
        }
        for (Developer developer : developers) {
            result.append(counter).append(".").append(developer.getName()).append(System.lineSeparator());
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


}
