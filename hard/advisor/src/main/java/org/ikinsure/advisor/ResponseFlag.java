package org.ikinsure.advisor;

public enum ResponseFlag {
    CORRECT, EXCEPTION, ERROR;
    static ResponseFlag validate(String response) {
        if (response == null || response.isBlank()) {
            return ResponseFlag.EXCEPTION;
        } else if (response.contains("error")) {
            return ResponseFlag.ERROR;
        }
        return ResponseFlag.CORRECT;
    }
}
