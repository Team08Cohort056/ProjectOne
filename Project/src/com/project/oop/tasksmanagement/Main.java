package com.project.oop.tasksmanagement;

import com.project.oop.tasksmanagement.core.TaskManagementEngineImpl;

public class Main {
    public static void main(String[] args) {

        TaskManagementEngineImpl managementEngine = new TaskManagementEngineImpl();
        managementEngine.start();
    }

}