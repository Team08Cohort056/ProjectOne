package com.project.oop.tasksmanagement.models.contracts;

import com.project.oop.tasksmanagement.models.enums.Priority;
import com.project.oop.tasksmanagement.models.enums.Status;
import com.project.oop.tasksmanagement.models.enums.StorySize;
import com.project.oop.tasksmanagement.models.enums.StoryStatus;

public interface Story extends AssignabelTask {

    StorySize getStorySize();

    void changeStorySize(StorySize storySize);

    void changeStoryStatus(Status status);
}
