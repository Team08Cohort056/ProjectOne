package com.project.oop.tasksmanagement.commands.enums;

public enum CommandTypeHelper {
    ADDBOARD,
    ADDTASK,
    ADDMEMBERTOTEAM,
    ADDBUGREPRODUCESTEPS,
    ASSIGNTASK,
    UNASSIGNTASK,
    ADDCOMMENT,
    REMOVECOMMENT,
    CREATEMEMBER,
    CREATETEAM,
    CHANGETASK,
    SHOWALL,
    SHOWACTIVITY,
    SHOWTEAMMEMBERS,
    SHOWTEAMBOARDS,
    LISTALL,
    LISTALLBUGS,
    LISTASSIGNEDTASKS,
    HELP;

    @Override
    public String toString() {
        switch (this) {
            case ADDBOARD:
                return """
                       
                        "Creates and adds a board to a team. Command takes 2 parameters:
                        [1]The name of the board you wish to create.
                        [2]The team that will contain the board.
                        """;
            case ADDTASK:
                return """
                        Creates and adds a task to a board. Command takes 6 parameters:
                        [1]Name of receiving team.
                        [2]Name of receiving board.
                        [3]Type of task you wish to create.
                        [4]Title of task.
                        [5]Description of task.
                        [6]severity, size or rating.
                        """;

            case ADDMEMBERTOTEAM:
                return """
                        Adds a member to a team. Command takes 2 parameters:
                        [1]Name of receiving team.
                        [2]Name of member.
                        """;
            case ADDBUGREPRODUCESTEPS:
                return """
                        Add steps to reproduce the bug. Command takes 2 parameters:
                        [1]ID of task.
                        [2]One string that describes the steps for reproducing the bug.
                        """;
            case ASSIGNTASK:
                return """
                        Assigns a task to a member. Command takes 2 parameters:
                        [1]Name of receiving member.
                        [2]ID of task.
                        You can see all tasks and their IDs with {SHOWALL TASKS}.
                        """;

            case UNASSIGNTASK:
                return """
                        UnAssigns a task from a member. Command takes 2 parameters:
                        [1]Name of member.
                        [2]ID of task to be removed.
                        You can see all tasks and their IDs with {SHOWALL TASKS}.
                        """;

            case ADDCOMMENT:
                return """
                        Adds a comment to a task with an author. Command takes 3 parameters:
                        [1]Name of author(member).
                        [2]Content of the comment.
                        [3]ID of task to receive a comment.
                        """;

            case REMOVECOMMENT:
                return """
                        Removes a comment from a task. Command takes 3 parameters:
                        [1]Name of author(member).
                        [2]ID of task to have comment removed.
                        [3]Index of comment.
                        """;

            case CREATEMEMBER:
                return """
                        Creates a member and adds them to the repository. Command takes 1 parameter:
                        [1]Name of member.
                        """;

            case CREATETEAM:
                return """
                        Creates a team and adds it to the repository. Command takes 1 parameter:
                        [1]Name of team.
                        """;

            case CHANGETASK:
                return """
                        Changes the attributes of an existing task. Command takes 3 parameters:
                        [1]ID of the task to be changed.
                        [2]Attribute to be changed.
                        [3]New value of attribute.
                        You can see all tasks and their IDs with {SHOWALL TASKS}.
                        """;

            case SHOWALL:
                return """
                        Shows all teams, members, boards or tasks. Command takes 1 parameter:
                        [1]Collection to be shown.
                        """;

            case SHOWACTIVITY:
                return """
                        Shows all activity of a team, member, board or task. Command takes 2 parameters:
                        [1]Type to query for activity.
                        [2]Name or ID of specific item.
                        """;

            case SHOWTEAMMEMBERS:
                return """
                        Shows all members in a team. Command takes 1 parameter:
                        [1]Team name.
                        """;

            case LISTALL:
                return "Helper not implemented yet."; //TODO

            case LISTALLBUGS:
                return """
                        Show all tasks that are already assigned.Command takes 2 or 4 parameters:
                        If one filter wanted:
                        [1]filter type - status or assignee
                        [2]status type or assignee name
                        If two filters wanted:
                        [1] "status"
                        [2] status type
                        [3] "assignee"
                        [4] assignee name
                        """;

            case LISTASSIGNEDTASKS:
                return """
                        Show all tasks that are already assigned.Command takes 2 or 4 parameters:
                        If one filter wanted:
                        [1]filter type - status or assignee
                        [2]status type or assignee name
                        If two filters wanted:
                        [1] "status"
                        [2] status type
                        [3] "assignee"
                        [4] assignee name
                        """;

            case HELP:
                return """
                        Shows the user instructions on using the application. Command takes no parameters or 1 parameter:
                        [1]Name of command to receive instructions on.
                        """;

            default:
                return "";
        }
    }
    //TODO: Finish all helper strings.
}
