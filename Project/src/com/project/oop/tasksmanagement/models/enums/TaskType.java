package com.project.oop.tasksmanagement.models.enums;

public enum TaskType {
    BUG,STORY,FEEDBACK;
    @Override
    public String toString() {
        switch (this) {
            case BUG:
                return "Bug";
            case STORY:
                return "Story";
            case FEEDBACK:
                return "Feedback";
            default:
                throw new UnsupportedOperationException("Can't convert type.");
        }
    }

}
