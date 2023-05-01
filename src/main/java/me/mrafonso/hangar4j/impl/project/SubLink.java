package me.mrafonso.hangar4j.impl.project;

public record SubLink(int id, String name, String url) {

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
