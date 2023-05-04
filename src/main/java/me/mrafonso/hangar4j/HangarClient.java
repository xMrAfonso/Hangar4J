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
    private DecodedJWT jwt;
    private final String userAgent;
    private final String apiKey;

    /**
     * Creates a new HangarClient instance with the specified API key and user agent.
     * This constructor is used when you have an API key and want to use the authenticated API.
     * You will have your own separate api limits.
     *
     * @param apiKey The API key to use for requests.
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


    /*
     * Checks if the JWT token has expired.
     */
    private boolean isJWTExpired() {
        Date expiresAt = jwt.getExpiresAt();
        jwt.getToken();
        return expiresAt.before(new Date());
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
            if (jwt == null || isJWTExpired()) {
                updateJWT();
            }
            requestBuilder.header("Authorization", jwt.getToken());
        }

        HttpRequest request = requestBuilder.build();
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
    }


    /**
     * Updates the JWT token used for authentication.
     * This method is called automatically when making a request to the API if the JWT token has expired.
     */
    public void updateJWT() {
        sendAPIRequest("/authenticate?apiKey=" + apiKey, RequestType.POST)
                .thenApply(response -> {
                    JsonObject obj = gson.fromJson(response.body(), JsonObject.class);
                    return obj.get("token").getAsString();
                })
                .thenAccept(obj -> jwt = JWT.decode(obj))
                .join();
    }

    /**
     * Retrieve information about a specific project.
     *
     * @param author The author of the project.
     * @param slug The slug of the project.
     * @return HangarProject object containing information about the project.
     */
    public HangarProject getProject(String author, String slug) {
        return sendAPIRequest("/projects/" + author + "/" + slug, RequestType.GET)
                .thenApply(response -> gson.fromJson(response.body(), HangarProject.class))
                .join();
    }

    /**
     * Retrieve a list containing several projects.
     *
     * @param orderWithRelevance Whether to order the projects with relevance or not.
     * @param limit The maximum amount of projects to return.
     * @param offset The offset to start from.
     * @return HangarProjects object containing a list of projects.
     */
    public HangarProjects getProjects(boolean orderWithRelevance, int limit, int offset) {
        if (limit > 25) {
            throw new IllegalArgumentException("Unable to make requests with a limit higher than 25. Currently: " + limit);
        }
        return sendAPIRequest("/projects?orderWithRelevance=" + orderWithRelevance + "&limit=" + limit + "&offset=" + offset, RequestType.GET)
                    .thenApply(response -> gson.fromJson(response.body(), HangarProjects.class))
                    .join();
    }

    /**
     * Retrieve a list containing several projects.
     *
     * @param limit The maximum amount of projects to return.
     * @param offset The offset to start from.
     * @return HangarProjects object containing a list of projects.
     */
    public HangarProjects getProjects(int limit, int offset) {
        return getProjects(false, limit, offset);
    }

    /**
     * Retrieve information about a specific user.
     *
     * @param user The username of the user.
     * @return HangarUser object containing information about the user.
     */
    public HangarUser getUser(String user) {
        return sendAPIRequest("/users/" + user, RequestType.GET)
                .thenApply(response -> gson.fromJson(response.body(), HangarUser.class))
                .join();
    }

    /**
     * Retrieve information about a specific version of a specific project.
     *
     * @param author The author of the project.
     * @param slug The slug of the project.
     * @param version The version of the project.
     * @return HangarVersion object containing information about the version.
     */
    public HangarVersion getVersion(String author, String slug, String version) {
        return sendAPIRequest("/projects/" + author + "/" + slug + "/versions/" + version, RequestType.GET)
                .thenApply(response -> gson.fromJson(response.body(), HangarVersion.class))
                .join();
    }

    /**
     * Retrieve information about a specific version of a specific project.
     *
     * @param hangarProject The HangarProject object.
     * @param version The version of the project.
     * @return HangarVersion object containing information about the version.
     */
    public HangarVersion getVersion(HangarProject hangarProject, String version) {
        Namespace namespace = hangarProject.getNamespace();
        return getVersion(namespace.getOwner(), namespace.getSlug(), version);
    }

    /**
     * Retrieve a list containing several versions of a specific project.
     *
     * @param author The author of the project.
     * @param slug The slug of the project.
     * @return HangarVersions object containing a list of versions.
     */
    public HangarVersions getVersions(String author, String slug) {
        return sendAPIRequest("projects/" + author + "/" + slug + "/versions/", RequestType.GET)
                .thenApply(response -> gson.fromJson(response.body(), HangarVersions.class))
                .join();
    }

    /**
     * Retrieve a list containing several versions of a specific project.
     *
     * @param hangarProject The HangarProject object.
     * @return HangarVersions object containing a list of versions.
     */
    public HangarVersions getVersions(HangarProject hangarProject) {
        Namespace namespace = hangarProject.getNamespace();
        return getVersions(namespace.getOwner(), namespace.getSlug());
    }

    /**
     * Retrieve the total amount of projects present in Hangar.
     *
     * @return The total amount of projects as an integer.
     */
    public int getTotalProjects() {
        return getProjects(1, 0).getPagination().getCount();
    }
}
