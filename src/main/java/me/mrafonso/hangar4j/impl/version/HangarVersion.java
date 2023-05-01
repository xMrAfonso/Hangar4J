package me.mrafonso.hangar4j.impl.version;

import me.mrafonso.hangar4j.impl.Platform;

import java.util.List;
import java.util.Map;

public record HangarVersion(String createdAt, String name, String visibility, String description,
                            Stats stats, String author, String reviewState, Channel channel, String pinnedStatus,
                            Map<String, DownloadData> downloads, Map<Platform, List<String>> platformDependencies,
                            Map<Platform, String> platformDependenciesFormatted) {

    public String getCreatedAt() {
        return createdAt;
    }

    public String getName() {
        return name;
    }

    public String getVisibility() {
        return visibility;
    }

    public String getDescription() {
        return description;
    }

    public Stats getStats() {
        return stats;
    }

    public String getAuthor() {
        return author;
    }

    public String getReviewState() {
        return reviewState;
    }

    public Channel getChannel() {
        return channel;
    }

    public String getPinnedStatus() {
        return pinnedStatus;
    }

    public Map<String, DownloadData> getDownloads() {
        return downloads;
    }

    public Map<Platform, List<String>> getPlatformDependencies() {
        return platformDependencies;
    }

    public Map<Platform, String> getPlatformDependenciesFormatted() {
        return platformDependenciesFormatted;
    }
}
