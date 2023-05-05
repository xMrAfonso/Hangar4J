package me.mrafonso.hangar4j.impl.version;

import java.util.List;

public record Channel(String createdAt, String name, String color, List<String> flags) {

}
