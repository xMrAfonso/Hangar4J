package me.mrafonso.hangar4j.impl.project;

public record Namespace(String owner, String slug) {

    public String getOwner() {
        return owner;
    }

    public String getSlug() {
        return slug;
    }
}
