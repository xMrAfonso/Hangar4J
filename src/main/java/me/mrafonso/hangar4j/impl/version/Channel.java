package me.mrafonso.hangar4j.impl.version;

import java.util.List;

/**
 * Represents a Hangar Project Channel.
 *
 * @param createdAt The creation date of the channel.
 * @param name      The name of the channel.
 * @param color     The color of the channel.
 * @param flags     The list of flags.
 */
public record Channel(String createdAt, String name, String color, List<String> flags) {

}
