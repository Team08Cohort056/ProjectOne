package com.project.oop.tasksmanagement.utils;

import com.project.oop.tasksmanagement.models.contracts.Feedback;
import com.project.oop.tasksmanagement.models.contracts.Story;

import java.util.Comparator;


public class ListingHelpers {

    public static Comparator<Feedback> getfeedbackComparator() {
        return feedbackComparator;
    }

    private static final Comparator<Feedback> feedbackComparator = Comparator
            .comparing(Feedback::getTitle)
            .thenComparingInt(Feedback::getRating);
    public static Comparator<Story> comparatorByTitle = Comparator.comparing(Story::getTitle);
    public static Comparator<Story> comparatorByPriority = Comparator.comparing(Story::getPriority);
    public static Comparator<Story> comparatorBySize = Comparator.comparing(Story::getStorySize);
}
