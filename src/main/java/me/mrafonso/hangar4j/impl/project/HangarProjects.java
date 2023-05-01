package me.mrafonso.hangar4j.impl.project;

import lombok.Getter;
import me.mrafonso.hangar4j.impl.Pagination;

import java.util.List;

@Getter
public class HangarProjects {
    private Pagination pagination;
    private List<HangarProject> result;
}
