package me.mrafonso.hangar4j.impl.version;

import lombok.Getter;
import me.mrafonso.hangar4j.impl.Pagination;

import java.util.List;

@Getter
public class HangarVersions {
    private Pagination pagination;
    private List<HangarVersion> result;
}
