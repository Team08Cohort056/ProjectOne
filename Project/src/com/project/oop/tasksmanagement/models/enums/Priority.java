package com.project.oop.tasksmanagement.models.enums;

public enum Priority {
    HIGH, MEDIUM, LOW;

    public String toString() {
        switch (this) {
            case HIGH:
                return "High";
            case MEDIUM:
                return "Medium";
            case LOW:
                return "Low";
            default:
                throw new UnsupportedOperationException("Can't convert Priority.");

        }
    }
}
