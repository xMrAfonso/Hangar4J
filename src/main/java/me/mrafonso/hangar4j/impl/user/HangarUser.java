package me.mrafonso.hangar4j.impl.user;

import java.util.List;

/**
 * Represents a Hangar User.
 *
 * @param createdAt         The creation date of the user.
 * @param name              The name of the user.
 * @param tagline           The tagline of the user.
 * @param roles             The list of roles.
 * @param projectCount      The number of projects.
 * @param locked            Whether the user is locked.
 * @param nameHistory       The list of NameHistory objects.
 * @param avatarUrl         The avatar url.
 * @param isOrganization    Whether the user is an organization.
 */
public record HangarUser(String createdAt, String name, String tagline, List<Integer> roles, int projectCount,
                         boolean locked, List<NameHistory> nameHistory, String avatarUrl, boolean isOrganization) {
}
