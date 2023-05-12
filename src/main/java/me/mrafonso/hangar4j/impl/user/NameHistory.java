package me.mrafonso.hangar4j.impl.user;

/**
 * Represents a Hangar User NameHistory.
 *
 * @param oldName The old name of the user.
 * @param newName The new name of the user.
 * @param date    The date of the name change.
 */
public record NameHistory(String oldName, String newName, String date) {

}
