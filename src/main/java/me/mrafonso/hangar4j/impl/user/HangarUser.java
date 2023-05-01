package me.mrafonso.hangar4j.impl.user;

import java.util.List;

public class HangarUser {
    String createdAt;
    String name;
    String tagline;
    List<Integer> roles;
    int projectCount;
    boolean locked;
    List<NameHistory> nameHistory;
    String avatarUrl;
    boolean isOrganization;

    public String getCreatedAt() {
        return createdAt;
    }

    public String getName() {
        return name;
    }

    public String getTagline() {
        return tagline;
    }

    public List<Integer> getRoles() {
        return roles;
    }

    public int getProjectCount() {
        return projectCount;
    }

    public boolean isLocked() {
        return locked;
    }

    public List<NameHistory> getNameHistory() {
        return nameHistory;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public boolean isOrganization() {
        return isOrganization;
    }
}
