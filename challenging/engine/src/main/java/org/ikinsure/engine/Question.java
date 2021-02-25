package org.ikinsure.engine;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Question {

    private int id;
    private String title;
    private String text;
    private List<String> options;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private int answer;

    public Question() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }
}
