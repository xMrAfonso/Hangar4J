package me.mrafonso.hangar4j.impl.version;

import java.util.Map;

/**
 * Represents a Hangar Project Stats.
 *
 * @param totalDownloads    The total downloads.
 * @param platformDownloads The map of platform downloads.
 */
public record Stats(int totalDownloads, Map<String, Integer> platformDownloads) {

}
