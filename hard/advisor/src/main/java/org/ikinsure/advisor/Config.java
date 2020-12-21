package org.ikinsure.advisor;

import java.util.Arrays;
import java.util.List;

/**
 * settings which can be passed from args
 */
public enum Config {

    ACCESS("https://accounts.spotify.com"), // connect server path
    REDIRECT_URI("http://localhost:8080/"), // local server address
    CLIENT_ID("6e70a3870fbe4d4bae4982467b032a2f"), // user id
    SECRET("90f7f9f1909548aa8f3f6c5ce74e3e8c"), // test secret code of spotify app
    AUTH_CODE(""), // permission code
    RESOURCE("https://api.spotify.com"), // API server path
    ACCESS_TOKEN(""), // token works as a permission for resources
    PAGE("5"); // entries per single page

    private String actual;

    Config(String defaults) {
        this.actual = defaults;
    }

    public String get() {
        return actual;
    }

    public void set(String actual) {
        this.actual = actual;
    }

    @Override
    public String toString() {
        return actual;
    }

    /**
     * match all input params to config
     * @param args input program arguments
     */
    public static void matchArguments(String[] args) {
        List<String> list = List.of(args);
        for (Config config : Config.values()) {
            int index = list.indexOf("-" + config.name().toLowerCase());
            if (index != -1) {
                config.actual = list.get(index + 1);
            }
        }
    }
}
