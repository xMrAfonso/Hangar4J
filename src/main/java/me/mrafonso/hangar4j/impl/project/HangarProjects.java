package me.mrafonso.hangar4j.impl.project;

import me.mrafonso.hangar4j.impl.Pagination;

import java.util.List;

/**
 * Represents one or more Hangar Projects.
 *
 * @param pagination    The pagination info.
 * @param result        The list of HangarProject objects.
 */
public record HangarProjects(Pagination pagination, List<HangarProject> result) {

}
