package me.mrafonso.hangar4j.impl.user;

import java.util.List;

public record HangarUser(String createdAt, String name, String tagline, List<Integer> roles, int projectCount,
                         boolean locked, List<NameHistory> nameHistory, String avatarUrl, boolean isOrganization) {
}
