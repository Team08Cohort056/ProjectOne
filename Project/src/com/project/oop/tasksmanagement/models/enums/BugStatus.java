package com.project.oop.tasksmanagement.models.enums;

public enum BugStatus {
        ACTIVE,DONE;
    @Override
    public String toString() {
        switch (this) {
            case ACTIVE:
                return "Active";
            case DONE:
                return "Done";
            default:
                throw new UnsupportedOperationException("Cannot convert Status type.");

        }
    }
}
