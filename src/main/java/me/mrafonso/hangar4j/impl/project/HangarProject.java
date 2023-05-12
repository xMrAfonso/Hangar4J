package me.mrafonso.hangar4j.impl.project;

/**
 * Represents a Hangar Project and its information.
 *
 * @param createdAt     When the project was created.
 * @param name          The name of the project.
 * @param namespace     The Namespace object of the project.
 * @param stats         The Stats object of the project.
 * @param category      The category of the project.
 * @param lastUpdated   When the project was last updated.
 * @param visibility    The visibility of the project.
 * @param avatarUrl     The avatar url of the project.
 * @param description   The description of the project.
 * @param userActions   The UserActions object of the project.
 * @param settings      The Settings object of the project.
 */
public record HangarProject(String createdAt, String name, Namespace namespace, Stats stats, String category, String lastUpdated,
                            String visibility, String avatarUrl, String description, UserActions userActions, Settings settings) {

}
