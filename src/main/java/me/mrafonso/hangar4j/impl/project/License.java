package me.mrafonso.hangar4j.impl.project;

public record License(String name, String url, String type) {

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getType() {
        return type;
    }
}
