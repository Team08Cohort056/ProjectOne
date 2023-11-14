package com.project.oop.tasksmanagement.models.contracts;

import com.project.oop.tasksmanagement.models.enums.Priority;
import com.project.oop.tasksmanagement.models.enums.StorySize;
import com.project.oop.tasksmanagement.models.enums.StoryStatus;

public interface Story extends Task {

    StorySize getStorySize();
    Priority getPriority();
    StoryStatus getStoryStatus();
}
