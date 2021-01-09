package org.ikinsure.jsondatabase.server;

import com.google.gson.annotations.Expose;

/**
 * Class represents server response
 */
public class Response {

    public static final Response EMPTY = builder()
            .setResponse("ERROR")
            .setReason("No such operation")
            .build();
    public static final Response OK = builder()
            .setResponse("OK")
            .build();
    public static final Response NO_KEY = builder()
            .setResponse("ERROR")
            .setReason("No such key")
            .build();

    @Expose
    private String response;
    @Expose
    private String reason;
    @Expose
    private String value;

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {

        private String response;
        private String reason;
        private String value;

        public Builder setResponse(String response) {
            this.response = response;
            return this;
        }

        public Builder setReason(String reason) {
            this.reason = reason;
            return this;
        }

        public Builder setValue(String value) {
            this.value = value;
            return this;
        }

        public Response build() {
            Response result = new Response();
            result.response = this.response;
            result.reason = this.reason;
            result.value = this.value;
            return result;
        }
    }
}
