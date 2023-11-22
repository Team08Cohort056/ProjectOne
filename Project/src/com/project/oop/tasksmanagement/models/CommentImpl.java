package com.project.oop.tasksmanagement.models;

import com.project.oop.tasksmanagement.models.contracts.Comment;
import com.project.oop.tasksmanagement.utils.ValidationHelpers;

import static java.lang.String.format;

public class CommentImpl implements Comment {

    private static final int CONTENT_LEN_MIN = 3;
    private static final int CONTENT_LEN_MAX = 200;
    private static final String CONTENT_LEN_ERR = format(
            "Content must be between %d and %d characters long!",
            CONTENT_LEN_MIN,
            CONTENT_LEN_MAX);
    private String content;
    private final String author;

    public CommentImpl(String content, String author) {
        setContent(content);
        this.author = author;
    }

    @Override
    public String getAuthor() {
        return this.author;
    }

    @Override
    public String commentsAsString() {
        return String.format("%s\nMember: %s\n", content, author);
    }

    @Override
    public String getContent() {
        return this.content;
    }

    private void setContent(String content) {
        ValidationHelpers.validateStringLength(content,CONTENT_LEN_MIN,CONTENT_LEN_MAX,CONTENT_LEN_ERR);
        this.content = content;
    }

//    @Override
//    public String toString() {
//        return String.format("""
//                %s
//                User: %s
//                """,getContent(),getAuthor());
//    }
}