package org.ikinsure.advisor;

import java.util.List;

public enum Config {

    ACCESS("https://accounts.spotify.com"), // server path
    REDIRECT_URI("http://localhost:8080/"), // local server address
    CLIENT_ID("6e70a3870fbe4d4bae4982467b032a2f"),
    SECRET("90f7f9f1909548aa8f3f6c5ce74e3e8c"), // test secret code of spotify app
    AUTH_CODE(""); // permission code

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

    static void matchArguments(String[] args) {
        List<String> list = List.of(args);
        for (Config config : Config.values()) {
            int index = list.indexOf("-" + config.name().toLowerCase());
            if (index != -1) {
                config.actual = list.get(index + 1);
            }
        }
    }
}
