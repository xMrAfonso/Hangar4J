package me.mrafonso.hangar4j.impl.version;

import me.mrafonso.hangar4j.impl.Platform;

import java.util.List;
import java.util.Map;

public record HangarVersion(String createdAt, String name, String visibility, String description,
                            Stats stats, String author, String reviewState, Channel channel, String pinnedStatus,
                            Map<String, DownloadData> downloads, Map<Platform, List<String>> platformDependencies,
                            Map<Platform, String> platformDependenciesFormatted) {


}
