package me.mrafonso.hangar4j.impl.project;

import me.mrafonso.hangar4j.impl.Pagination;

import java.util.List;

public record HangarProjects(Pagination pagination, List<HangarProject> result) {

}
