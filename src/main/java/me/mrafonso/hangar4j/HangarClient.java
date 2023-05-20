package me.mrafonso.hangar4j;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import me.mrafonso.hangar4j.util.RequestType;
import me.mrafonso.hangar4j.impl.project.HangarProject;
import me.mrafonso.hangar4j.impl.project.HangarProjects;
import me.mrafonso.hangar4j.impl.project.Namespace;
import me.mrafonso.hangar4j.impl.user.HangarUser;
import me.mrafonso.hangar4j.impl.version.HangarVersion;
import me.mrafonso.hangar4j.impl.version.HangarVersions;
import org.jetbrains.annotations.Nullable;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Date;
import java.util.concurrent.CompletableFuture;

/**
 * HangarClient is the main class of the library. It is used to make requests to the Hangar API.
 */
public class HangarClient {
    private static final HttpClient client = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .build();
    private static final Gson gson = new Gson();
    private static final String API_URL = "https://hangar.papermc.io/api/v1";
    private boolean autoUpdateJWT = true;
    private DecodedJWT jwt;
    private final String userAgent;
    private final String apiKey;

    /**
     * Creates a new HangarClient instance with the specified API key and user agent.
     * This constructor is used when you have an API key and want to use the authenticated API.
     * You will have your own separate api limits.
     *
     * @param apiKey        The API key to use for requests.
     * @param userAgent     The user agent to use for requests.
     * @param autoUpdateJWT Whether to automatically update the JWT token when it expires or not. (Default: true)
     */
    public HangarClient(String apiKey, String userAgent, boolean autoUpdateJWT) {
        this.apiKey = apiKey;
        this.userAgent = userAgent + " (Hangar4J)";
        this.autoUpdateJWT = autoUpdateJWT;
    }

    /**
     * Creates a new HangarClient instance with the specified API key and user agent.
     * This constructor is used when you have an API key and want to use the authenticated API.
     * You will have your own separate api limits.
     *
     * @param apiKey    The API key to use for requests.
     * @param userAgent The user agent to use for requests.
     */
    public HangarClient(String apiKey, String userAgent) {
        this.apiKey = apiKey;
        this.userAgent = userAgent + " (Hangar4J)";
    }

    /**
     * Creates a new HangarClient instance with the specified user agent.
     * This constructor is used when you don't have an API key and want to use the public API.
     * WARNING: You will share api limits with other users.
     *
     * @param userAgent The user agent to use for requests.
     */
    public HangarClient(String userAgent) {
        this.apiKey = null;
        this.userAgent = userAgent + " (Hangar4J)";
    }

    /**
     * Creates a new HangarClient instance with the specified user agent.
     * This constructor is used when you don't have an API key and want to use the public API.
     * WARNING: You will share api limits with other users.
     *
     * @param userAgent     The user agent to use for requests.
     * @param autoUpdateJWT Whether to automatically update the JWT token when it expires or not. (Default: true)
     */
    public HangarClient(String userAgent, boolean autoUpdateJWT) {
        this.apiKey = null;
        this.userAgent = userAgent + " (Hangar4J)";
        this.autoUpdateJWT = autoUpdateJWT;
    }


    /**
     * Checks if the currently stored JWT token has expired.
     *
     * @return boolean indicating whether the JWT token has expired or not.
     */
    public boolean isJWTExpired() {
        Date expiresAt = jwt.getExpiresAt();
        return expiresAt.before(new Date());
    }

    /**
     * Generates a new JWT token used for authentication.
     * This method is called automatically when making a request to the API if the JWT token has expired and this option is enabled. (Default: true)
     *
     * @return DecodedJWT object containing information about the JWT token. (CompletableFuture)
     */
    public @Nullable CompletableFuture<DecodedJWT> getNewJWT() {
        return sendAPIRequest("/authenticate?apiKey=" + apiKey, RequestType.POST)
                .thenApply(response -> {
                    JsonObject obj = gson.fromJson(response.body(), JsonObject.class);
                    return JWT.decode(obj.get("token").getAsString());
                });
    }

    /**
     * Manually sets the JWT token used for authentication.
     *
     * @param jwt The JWT token to use.
     */
    public void setJWT(DecodedJWT jwt) {
        this.jwt = jwt;
    }


    /**
     * Retrieve information about a specific project.
     *
     * @param author The author of the project.
     * @param slug   The slug of the project.
     *
     * @return HangarProject object containing information about the project. (CompletableFuture)
     */
    public @Nullable CompletableFuture<HangarProject> getProject(String author, String slug) {
        return sendAPIRequest("/projects/" + author + "/" + slug, RequestType.GET)
                .thenApply(response -> gson.fromJson(response.body(), HangarProject.class));
    }

    /**
     * Retrieve a list containing several projects.
     *
     * @param orderWithRelevance Whether to order the projects with relevance or not.
     * @param limit              The maximum amount of projects to return.
     * @param offset             The offset to start from.
     *
     * @return HangarProjects object containing a list of projects. (CompletableFuture)
     */
    public @Nullable CompletableFuture<HangarProjects> getProjects(boolean orderWithRelevance, int limit, int offset) {
        if (limit > 25) {
            throw new IllegalArgumentException("Unable to make requests with a limit higher than 25. Currently: " + limit);
        }
        return sendAPIRequest("/projects?orderWithRelevance=" + orderWithRelevance + "&limit=" + limit + "&offset=" + offset, RequestType.GET)
                    .thenApply(response -> gson.fromJson(response.body(), HangarProjects.class));
    }

    /**
     * Retrieve a list containing several projects.
     *
     * @param limit  The maximum amount of projects to return.
     * @param offset The offset to start from.
     *
     * @return HangarProjects object containing a list of projects. (CompletableFuture)
     */
    public @Nullable CompletableFuture<HangarProjects> getProjects(int limit, int offset) {
        return getProjects(false, limit, offset);
    }

    /**
     * Search for a project by name.
     *
     * @param name   The name of the project.
     *
     * @return HangarProject object containing a list of projects. (CompletableFuture)
     */
    public @Nullable CompletableFuture<HangarProject> searchProject(String name) {
        return sendAPIRequest("/projects?orderWithRelevance=true&limit=1&offset=0&q=" + name, RequestType.GET)
                .thenApply(response -> gson.fromJson(response.body(), HangarProjects.class))
                .thenApply(projects -> projects.result().get(0));
    }

    /**
     * Retrieve a list containing projects of a specific user.
     *
     * @param user The user to retrieve the projects of.
     * @param limit The maximum amount of projects to return.
     * @return HangarProjects object containing a list of projects. (CompletableFuture)
     */
    public @Nullable CompletableFuture<HangarProjects> getUserProjects(String user, int limit) {
        return sendAPIRequest("/projects?orderWithRelevance=false&limit=" + limit + "&offset=0&owner=" + user, RequestType.GET)
                .thenApply(response -> gson.fromJson(response.body(), HangarProjects.class));
    }

    /**
     * Retrieve a list containing projects of a specific user.
     *
     * @param user  The user to retrieve the projects of.
     * @param limit The maximum amount of projects to return.
     *
     * @return HangarProjects object containing a list of projects. (CompletableFuture)
     */
    public @Nullable CompletableFuture<HangarProjects> getUserProjects(HangarUser user, int limit) {
        return getUserProjects(user.name(), limit);
    }

    /**
     * Retrieve a list containing projects of a specific user.
     *
     * @param user The user to retrieve the projects of.
     *
     * @return HangarProjects object containing a list of projects. (CompletableFuture)
     */
    public @Nullable CompletableFuture<HangarProjects> getUserProjects(String user) {
        return getUserProjects(user, 25);
    }

    /**
     * Retrieve a list containing projects of a specific user.
     *
     * @param user The user to retrieve the projects of.
     *
     * @return HangarProjects object containing a list of projects. (CompletableFuture)
     */
    public @Nullable CompletableFuture<HangarProjects> getUserProjects(HangarUser user) {
        return getUserProjects(user.name(), 25);
    }

    /**
     * Retrieve information about a specific user.
     *
     * @param user The username of the user.
     *
     * @return HangarUser object containing information about the user. (CompletableFuture)
     */
    public @Nullable CompletableFuture<HangarUser> getUser(String user) {
        return sendAPIRequest("/users/" + user, RequestType.GET)
                .thenApply(response -> gson.fromJson(response.body(), HangarUser.class));
    }

    /**
     * Retrieve information about a specific version of a specific project.
     *
     * @param author  The author of the project.
     * @param slug    The slug of the project.
     * @param version The version of the project.
     *
     * @return HangarVersion object containing information about the version. (CompletableFuture)
     */
    public @Nullable CompletableFuture<HangarVersion> getVersion(String author, String slug, String version) {
        return sendAPIRequest("/projects/" + author + "/" + slug + "/versions/" + version, RequestType.GET)
                .thenApply(response -> gson.fromJson(response.body(), HangarVersion.class));
    }

    /**
     * Retrieve information about a specific version of a specific project.
     *
     * @param hangarProject The HangarProject object.
     * @param version       The version of the project.
     *
     * @return HangarVersion object containing information about the version. (CompletableFuture)
     */
    public @Nullable CompletableFuture<HangarVersion> getVersion(HangarProject hangarProject, String version) {
        Namespace namespace = hangarProject.namespace();
        return getVersion(namespace.owner(), namespace.slug(), version);
    }

    /**
     * Retrieve a list containing several versions of a specific project.
     *
     * @param author The author of the project.
     * @param slug   The slug of the project.
     *
     * @return HangarVersions object containing a list of versions. (CompletableFuture)
     */
    public @Nullable CompletableFuture<HangarVersions> getVersions(String author, String slug) {
        return sendAPIRequest("projects/" + author + "/" + slug + "/versions/", RequestType.GET)
                .thenApply(response -> gson.fromJson(response.body(), HangarVersions.class));
    }

    /**
     * Retrieve a list containing several versions of a specific project.
     *
     * @param hangarProject The HangarProject object.
     *
     * @return HangarVersions object containing a list of versions. (CompletableFuture)
     */
    public @Nullable CompletableFuture<HangarVersions> getVersions(HangarProject hangarProject) {
        Namespace namespace = hangarProject.namespace();
        return getVersions(namespace.owner(), namespace.slug());
    }

    /**
     * Retrieve the total amount of projects present in Hangar.
     *
     * @return The total amount of projects as an integer. (CompletableFuture)
     */
    public @Nullable CompletableFuture<Integer> getTotalProjectCount() {
        return getProjects(1, 0).thenApply(hangarProjects -> hangarProjects.pagination().count());
    }

    /*
     * Sends a request to the Hangar API and does some basic error handling.
     */
    private CompletableFuture<HttpResponse<String>> sendAPIRequest(String path, RequestType type) {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(API_URL + path))
                .header("User-Agent", userAgent);

        if (type == RequestType.POST) {
            requestBuilder.POST(HttpRequest.BodyPublishers.noBody());
        } else if (apiKey != null) {
            if (autoUpdateJWT && (jwt == null || isJWTExpired())) {
                DecodedJWT tmpJWT = getNewJWT().join();
                synchronized(this) {
                    setJWT(tmpJWT);
                }
            }
            requestBuilder.header("Authorization", jwt.getToken());
        }

        HttpRequest request = requestBuilder.build();
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
    }
}
