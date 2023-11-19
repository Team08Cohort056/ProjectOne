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
    LISTASSIGNEDTASKS,
    HELP;

    @Override
    public String toString() {
        switch (this) {
            case ADDBOARD:
                return "Creates and adds a board to a team. Command takes 2 parameters - " +
                        "The name of the board you wish to create and the team that will contain the board.";
            case ADDTASK:
                return "";

            case ADDMEMBERTOTEAM:
                return "Welcomes a new member of the team. To register a new member enter name and then team name!";
            case ADDBUGREPRODUCESTEPS:
                return "";

            case ASSIGNTASK:
                return "";

            case UNASSIGNTASK:
                return "";

            case ADDCOMMENT:
                return "Tell us what you think! If you want to leave a comment tell us your name, enter a comment" +
                        "and don't forget the ID of the task for which is the comment";

            case REMOVECOMMENT:
                return "If you want to remove a comment enter your name, the task ID and the index of the comment";

            case CREATEMEMBER:
                return "To be registered you have to enter your name!";

            case CREATETEAM:
                return "";

            case CHANGETASK:
                return "";

            case SHOWALL:
                return "";

            case SHOWACTIVITY:
                return "";

            case SHOWTEAMMEMBERS:
                return "";

            case LISTALL:
                return "";

            case LISTASSIGNEDTASKS:
                return "";

            case HELP:
                return "";

            default:
                return "";
        }
    }
    //TODO: Finish all helper strings.
}
