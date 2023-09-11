package me.mrafonso.hangar4j.impl.stargazers;

import me.mrafonso.hangar4j.impl.Pagination;
import me.mrafonso.hangar4j.impl.user.HangarUser;

import java.util.ArrayList;

public record HangarStargazers(Pagination pagination, ArrayList<HangarUser> result) {
}
