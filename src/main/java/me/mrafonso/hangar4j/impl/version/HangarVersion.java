package me.mrafonso.hangar4j.impl.version;

import lombok.Getter;
import me.mrafonso.hangar4j.impl.Platform;

import java.util.List;
import java.util.Map;

@Getter
public class HangarVersion {
    private String createdAt;
    private String name;
    private String visibility;
    private String description;
    private Stats stats;
    private String author;
    private String reviewState;
    private Channel channel;
    private String pinnedStatus;
    private Map<String, DownloadData> downloads;
    private Map<Platform, List<String>> platformDependencies;
    private Map<Platform, String> platformDependenciesFormatted;
}
