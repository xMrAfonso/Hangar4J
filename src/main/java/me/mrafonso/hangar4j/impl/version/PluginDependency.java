package me.mrafonso.hangar4j.impl.version;

import me.mrafonso.hangar4j.impl.Platform;
import me.mrafonso.hangar4j.impl.project.Namespace;

public record PluginDependency(String name, boolean required, Namespace namespace, String externalUrl, Platform platform) {
}
