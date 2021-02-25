package org.ikinsure.engine;

public class Response {

    private final boolean success;
    private final String feedback;

    public Response(boolean success, String feedback) {
        this.success = success;
        this.feedback = feedback;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getFeedback() {
        return feedback;
    }
}
