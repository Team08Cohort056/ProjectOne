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
                return "";
            case ADDBUGREPRODUCESTEPS:
                return "";

            case ASSIGNTASK:
                return "";

            case UNASSIGNTASK:
                return "";

            case ADDCOMMENT:
                return "";

            case REMOVECOMMENT:
                return "";

            case CREATEMEMBER:
                return "";

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
