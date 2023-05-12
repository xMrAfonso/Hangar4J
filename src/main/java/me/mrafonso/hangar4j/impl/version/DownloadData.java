package me.mrafonso.hangar4j.impl.version;

/**
 * Represents a Hangar Project DownloadData.
 *
 * @param fileInfo    The file info.
 * @param externalUrl The external url.
 * @param downloadUrl The download url.
 */
public record DownloadData(FileInfo fileInfo, String externalUrl, String downloadUrl) {

}
