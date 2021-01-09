package org.ikinsure.jsondatabase.server;

public class Response {

    private String response;
    private String reason;
    private String value;

    public void setResponse(String response) {
        this.response = response;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static Response empty() {
        Response response = new Response();
        response.setResponse("ERROR");
        response.setReason("No such operation");
        return response;
    }
}
