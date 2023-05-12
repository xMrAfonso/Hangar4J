package me.mrafonso.hangar4j.impl.project;

/**
 * Represents a Hangar Project Stats.
 *
 * @param views             The total views of the project.
 * @param downloads         The total downloads of the project.
 * @param recentViews       The recent views of the project.
 * @param recentDownloads   The recent downloads of the project.
 * @param stars             The total stars of the project.
 * @param watchers          The total watchers of the project.
 */
public record Stats(int views, int downloads, int recentViews, int recentDownloads, int stars, int watchers) {

}
