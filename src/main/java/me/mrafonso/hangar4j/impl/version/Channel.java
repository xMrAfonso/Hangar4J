package me.mrafonso.hangar4j.impl.version;

import java.util.List;

public class Channel {
    String createdAt;
    String name;
    String color;
    List<String> flags;

    public String getCreatedAt() {
        return createdAt;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public List<String> getFlags() {
        return flags;
    }
}
