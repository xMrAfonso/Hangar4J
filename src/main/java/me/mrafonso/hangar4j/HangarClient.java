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

public class HangarClient {
    private final String userAgent;
    private static final HttpClient client = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .build();
    private static final Gson gson = new Gson();
    private static final String API_URL = "https://hangar.papermc.io/api/v1";
    public DecodedJWT jwt;
    private String apiKey;
    public HangarClient(String apiKey, String userAgent) {
        this.apiKey = apiKey;
        this.userAgent = userAgent;
    }

    private boolean isJWTExpired() {
        Date expiresAt = jwt.getExpiresAt();
        jwt.getToken();
        return expiresAt.before(new Date());
    }

    private CompletableFuture<HttpResponse<String>> sendAPIRequest(String path, RequestType type, boolean useJWT) {
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(API_URL + path))
                .header("User-Agent", userAgent);

        if (type == RequestType.POST) {
            requestBuilder.POST(HttpRequest.BodyPublishers.noBody());
        } else if (jwt == null || isJWTExpired()) {
            updateJWT();
        }
        if (useJWT) {
            requestBuilder.header("Authorization", jwt.getToken());
        }

        HttpRequest request = requestBuilder.build();
        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
    }

    public void updateJWT() {
        sendAPIRequest("/authenticate?apiKey=" + apiKey, RequestType.POST, false)
                .thenApply(response -> {
                    JsonObject obj = gson.fromJson(response.body(), JsonObject.class);
                    return obj.get("token").getAsString();
                })
                .thenAccept(obj -> jwt = JWT.decode(obj))
                .join();
    }

    public HangarProject getProject(String author, String slug) {
        return sendAPIRequest("/projects/" + author + "/" + slug, RequestType.GET, true)
                .thenApply(response -> gson.fromJson(response.body(), HangarProject.class))
                .join();
    }

    public HangarProjects getProjects(boolean orderWithRelevance, int limit, int offset) {
        if (limit > 25) {
            throw new IllegalArgumentException("Unable to make requests with a limit higher than 25. Currently: " + limit);
        }
        return sendAPIRequest("/projects?orderWithRelevance=" + orderWithRelevance + "&limit=" + limit + "&offset=" + offset, RequestType.GET, true)
                    .thenApply(response -> gson.fromJson(response.body(), HangarProjects.class))
                    .join();
    }

    public HangarProjects getProjects(int limit, int offset) {
        return getProjects(false, limit, offset);
    }

    public HangarUser getUser(String user) {
        return sendAPIRequest("/users/" + user, RequestType.GET, true)
                .thenApply(response -> gson.fromJson(response.body(), HangarUser.class))
                .join();
    }

    public HangarVersion getVersion(String author, String slug, String version) {
        return sendAPIRequest("/projects/" + author + "/" + slug + "/versions/" + version, RequestType.GET, true)
                .thenApply(response -> gson.fromJson(response.body(), HangarVersion.class))
                .join();
    }

    public HangarVersion getVersion(HangarProject hangarProject, String version) {
        Namespace namespace = hangarProject.getNamespace();
        return getVersion(namespace.getOwner(), namespace.getSlug(), version);
    }

    public HangarVersions getVersions(String author, String slug) {
        return sendAPIRequest("projects/" + author + "/" + slug + "/versions/", RequestType.GET, true)
                .thenApply(response -> gson.fromJson(response.body(), HangarVersions.class))
                .join();
    }

    public HangarVersions getVersions(HangarProject hangarProject) {
        Namespace namespace = hangarProject.getNamespace();
        return getVersions(namespace.getOwner(), namespace.getSlug());
    }

    public int getTotalProjects() {
        return getProjects(1, 0).getPagination().getCount();
    }
}
