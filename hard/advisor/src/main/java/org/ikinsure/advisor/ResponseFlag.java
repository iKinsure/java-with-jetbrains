package org.ikinsure.advisor;

/**
 * enum represents response state
 */
public enum ResponseFlag {
    CORRECT, EXCEPTION, ERROR;

    /**
     * method validate response
     * @return response state
     */
    static ResponseFlag validate(String response) {
        if (response == null || response.isBlank()) {
            return ResponseFlag.EXCEPTION;
        } else if (response.contains("error")) {
            return ResponseFlag.ERROR;
        }
        return ResponseFlag.CORRECT;
    }
}
