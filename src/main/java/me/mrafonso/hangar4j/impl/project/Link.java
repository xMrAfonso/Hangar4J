package me.mrafonso.hangar4j.impl.project;

import java.util.List;

/**
 * Represents a Hangar Project Link.
 *
 * @param id    The id of the link.
 * @param type  The type of the link.
 * @param title The title of the link.
 * @param links The list of SubLink objects.
 */
public record Link(int id, String type, String title, List<SubLink> links) {

}
