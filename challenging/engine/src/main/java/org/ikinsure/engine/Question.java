package org.ikinsure.engine;

import java.util.List;

public class Question {

    private final String title;
    private final String text;
    private final List<String> options;

    public Question(String title, String text, List<String> options) {
        this.title = title;
        this.text = text;
        this.options = options;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public List<String> getOptions() {
        return options;
    }
}
