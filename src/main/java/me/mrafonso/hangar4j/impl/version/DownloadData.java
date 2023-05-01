package me.mrafonso.hangar4j.impl.version;

public record DownloadData(FileInfo fileInfo, String externalUrl, String downloadUrl) {

    public FileInfo getFileInfo() {
        return fileInfo;
    }

    public String getExternalUrl() {
        return externalUrl;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }
}
