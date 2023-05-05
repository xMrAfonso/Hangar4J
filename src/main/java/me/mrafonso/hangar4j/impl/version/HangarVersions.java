package me.mrafonso.hangar4j.impl.version;

import me.mrafonso.hangar4j.impl.Pagination;

import java.util.List;

public record HangarVersions(Pagination pagination, List<HangarVersion> result) {

}
