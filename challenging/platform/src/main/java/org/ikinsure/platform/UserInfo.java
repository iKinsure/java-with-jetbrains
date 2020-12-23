package org.ikinsure.platform;

public class UserInfo {

    private final int id;
    private final String name;
    private final String phone;
    private final boolean enabled;

    public UserInfo(int id, String name, String phone, boolean enabled) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.enabled = enabled;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public boolean isEnabled() {
        return enabled;
    }
}
