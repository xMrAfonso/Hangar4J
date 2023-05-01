package me.mrafonso.hangar4j.impl.project;

public record Stats(int views, int downloads, int recentViews, int recentDownloads, int stars, int watchers) {

    public int getViews() {
        return views;
    }

    public int getDownloads() {
        return downloads;
    }

    public int getRecentViews() {
        return recentViews;
    }

    public int getRecentDownloads() {
        return recentDownloads;
    }

    public int getStars() {
        return stars;
    }

    public int getWatchers() {
        return watchers;
    }
}
