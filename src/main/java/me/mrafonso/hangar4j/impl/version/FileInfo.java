package me.mrafonso.hangar4j.impl.version;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileInfo {
    private String name;
    private int sizeBytes;
    private String sha256Hash;
}
