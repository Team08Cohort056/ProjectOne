package com.project.oop.tasksmanagement.utils;

import com.project.oop.tasksmanagement.models.contracts.Feedback;
import com.project.oop.tasksmanagement.models.contracts.Task;


import java.util.Comparator;

public class ListingHelpers {


    public static Comparator<Feedback> getfeedbackComparator() {
        return feedbackComparator;
    }
    private static final Comparator<Feedback> feedbackComparator = Comparator
            .comparing(Feedback::getTitle)
            .thenComparingInt(Feedback::getRating);
}
