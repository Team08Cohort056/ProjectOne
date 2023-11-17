package com.project.oop.tasksmanagement.models.enums;

public enum StoryStatus {
NOT_DONE,IN_PROGRESS,DONE;
    public String toString() {
        switch (this) {
            case NOT_DONE:
                return "Not done";
            case IN_PROGRESS:
                return "In Progress";
            case DONE:
                return "Done";
            default:
                throw new UnsupportedOperationException("Story status can be Not done, In progress or Done");

        }
    }
}
