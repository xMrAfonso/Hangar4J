package me.mrafonso.hangar4j.impl.version;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class Stats {
    private int totalDownloads;
    private Map<String, Integer> platformDownloads;
}
