package me.mrafonso.hangar4j.impl.project;

public record HangarProject(String createdAt, String name, Namespace namespace, Stats stats, String category, String lastUpdated,
                            String visibility, String avatarUrl, String description, UserActions userActions, Settings settings) {

}
