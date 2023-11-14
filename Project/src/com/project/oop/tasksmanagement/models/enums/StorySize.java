package com.project.oop.tasksmanagement.models.enums;

public enum StorySize {
    LARGE, MEDIUM, SMALL;

    public String toString() {
        switch (this) {
            case LARGE:
                return "Large";
            case MEDIUM:
                return "Medium";
            case SMALL:
                return "Small";
            default:
                throw new UnsupportedOperationException("Can't convert Status size.");

        }
    }
}
