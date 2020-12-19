package org.ikinsure.advisor.model;

import java.net.URI;

public class User {

    private final String name;
    private final String openAccount;
    private final int followers;
    private final String apiAccount;
    private final String id;
    private final String image;

    User(String name, String openAccount, int followers, String apiAccount, String id, String image) {
        this.name = name;
        this.openAccount = openAccount;
        this.followers = followers;
        this.apiAccount = apiAccount;
        this.id = id;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getOpenAccount() {
        return openAccount;
    }

    public int getFollowers() {
        return followers;
    }

    public String getApiAccount() {
        return apiAccount;
    }

    public String getId() {
        return id;
    }

    public String getImage() {
        return image;
    }
}
