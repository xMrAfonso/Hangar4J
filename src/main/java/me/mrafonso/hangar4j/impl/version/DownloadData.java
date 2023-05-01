package me.mrafonso.hangar4j.impl.version;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DownloadData {
    private FileInfo fileInfo;
    private String externalUrl;
    private String downloadUrl;
}
