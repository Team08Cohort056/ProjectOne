package com.project.oop.tasksmanagement.core.exceptions;

public class InvalidUserInputException extends RuntimeException {
    public InvalidUserInputException() {
    }

    public InvalidUserInputException(String message) {
        super(message);
    }
}
