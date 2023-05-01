package me.mrafonso.hangar4j.impl.version;

public record FileInfo(String name, int sizeBytes, String sha256Hash) {

    public String getName() {
        return name;
    }

    public int getSizeBytes() {
        return sizeBytes;
    }

    public String getSha256Hash() {
        return sha256Hash;
    }
}
