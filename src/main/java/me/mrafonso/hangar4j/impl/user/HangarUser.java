package me.mrafonso.hangar4j.impl.user;

import lombok.Getter;

import java.util.List;

@Getter
public class HangarUser {
    private String createdAt;
    private String name;
    private String tagline;
    private List<Integer> roles;
    private int projectCount;
    private boolean locked;
    private List<NameHistory> nameHistory;
    private String avatarUrl;
    private boolean isOrganization;
}
