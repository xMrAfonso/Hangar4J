package me.mrafonso.hangar4j.impl.project;

import me.mrafonso.hangar4j.impl.Pagination;

import java.util.List;

public class HangarProjects {
    Pagination pagination;
    List<HangarProject> result;

    public Pagination getPagination() {
        return pagination;
    }

    public List<HangarProject> getResult() {
        return result;
    }
}
