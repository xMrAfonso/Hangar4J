package me.mrafonso.hangar4j.impl.version;

import me.mrafonso.hangar4j.impl.Pagination;

import java.util.List;

/**
 * Represents a Hangar Project HangarVersions.
 *
 * @param pagination    The pagination info.
 * @param result        The list of versions.
 */
public record HangarVersions(Pagination pagination, List<HangarVersion> result) {

}
