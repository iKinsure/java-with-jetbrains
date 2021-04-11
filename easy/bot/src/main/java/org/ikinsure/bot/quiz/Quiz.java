package org.ikinsure.bot.quiz;

import java.util.List;

/**
 * Class represents single quiz with one correct answer
 */
public final class Quiz {

    private final String question;
    private final List<String> answers;
    private final int correctIndex;

    private Quiz(String question, List<String> answers, int correctIndex) {
        this.question = question;
        this.answers = answers;
        this.correctIndex = correctIndex;
    }

    public static Builder builder() {
        return new Builder();
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public int getCorrectIndex() {
        return correctIndex;
    }

    /**
     * Quiz builder with validation
     */
    public static final class Builder {

        private String question;
        private List<String> answers;
        private int correctIndex;

        public Builder setQuestion(String question) {
            this.question = question;
            return this;
        }

        public Builder setAnswers(List<String> answers) {
            this.answers = answers;
            return this;
        }

        public Builder setCorrectIndex(int correctIndex) {
            this.correctIndex = correctIndex;
            return this;
        }

        public Quiz build() {

            if (question == null || question.isEmpty()) {
                throw new IllegalStateException("question cannot be empty");
            }

            if (answers == null || answers.isEmpty()) {
                throw new IllegalStateException("answers cannot be empty");
            }

            if (correctIndex >= answers.size() || correctIndex < 0) {
                throw new IllegalStateException("invalid index");
            }

            return new Quiz(question, answers, correctIndex);
        }
    }

}
