package me.mrafonso.hangar4j.impl.version;

/**
 * Represents a Hangar Project FileInfo.
 *
 * @param name          The name of the file.
 * @param sizeBytes     The size of the file in bytes.
 * @param sha256Hash    The sha256 hash of the file.
 */
public record FileInfo(String name, int sizeBytes, String sha256Hash) {

}
