package me.mrafonso.hangar4j.impl.version;

import java.util.Map;

public record Stats(int totalDownloads, Map<String, Integer> platformDownloads) {

}
