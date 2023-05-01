package me.mrafonso.hangar4j.impl.project;

public record HangarProject(String createdAt, String name, Namespace namespace, Stats stats, String category, String lastUpdated,
                            String visibility, String avatarUrl, String description, UserActions userActions, Settings settings) {

    public String getCreatedAt() {
        return createdAt;
    }

    public String getName() {
        return name;
    }

    public Namespace getNamespace() {
        return namespace;
    }

    public Stats getStats() {
        return stats;
    }

    public String getCategory() {
        return category;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public String getVisibility() {
        return visibility;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getDescription() {
        return description;
    }

    public UserActions getUserActions() {
        return userActions;
    }

    public Settings getSettings() {
        return settings;
    }
}
