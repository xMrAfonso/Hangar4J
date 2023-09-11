package me.mrafonso.hangar4j.impl.version;

import me.mrafonso.hangar4j.impl.Platform;

import java.util.List;
import java.util.Map;

/**
 * Represents a Hangar Project Version.
 *
 * @param createdAt                     The creation date of the version.
 * @param name                          The name of the version.
 * @param visibility                    The visibility of the version.
 * @param description                   The description of the version.
 * @param stats                         The Stats object.
 * @param author                        The author of the version.
 * @param reviewState                   The review state of the version.
 * @param channel                       The Channel object.
 * @param pinnedStatus                  The pinned status of the version.
 * @param downloads                     The map of DownloadData objects.
 * @param platformDependencies          The map of platform dependencies.
 * @param platformDependenciesFormatted The map of formatted platform dependencies.
 */
public record HangarVersion(String createdAt, String name, String visibility, String description,
                            Stats stats, String author, String reviewState, Channel channel, String pinnedStatus,
                            Map<String, DownloadData> downloads, Map<Platform, List<PluginDependency>> pluginDependencies,
                            Map<Platform, List<String>> platformDependencies,
                            Map<Platform, String> platformDependenciesFormatted) {


}
