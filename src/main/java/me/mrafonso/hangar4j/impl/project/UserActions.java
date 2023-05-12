package me.mrafonso.hangar4j.impl.project;

/**
 * Represents a Hangar Project UserActions.
 *
 * @param starred   Is the project starred.
 * @param watching  Is the project watched.
 * @param flagged   Is the project flagged.
 */
public record UserActions(boolean starred, boolean watching, boolean flagged) {

}
