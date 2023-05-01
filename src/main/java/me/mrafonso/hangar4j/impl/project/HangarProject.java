package me.mrafonso.hangar4j.impl.project;

import lombok.Getter;

@Getter
public class HangarProject {
    private String createdAt;
    private String name;
    private Namespace namespace;
    private Stats stats;
    private String category;
    private String lastUpdated;
    private String visibility;
    private String avatarUrl;
    private String description;
    private UserActions userActions;
    private Settings settings;
}
