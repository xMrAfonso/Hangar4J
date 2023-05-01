package me.mrafonso.hangar4j.impl.version;

import me.mrafonso.hangar4j.impl.Pagination;

import java.util.List;

public class HangarVersions {
    Pagination pagination;
    List<HangarVersion> result;

    public Pagination getPagination() {
        return pagination;
    }

    public List<HangarVersion> getResult() {
        return result;
    }
}
