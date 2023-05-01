package me.mrafonso.hangar4j.impl.version;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Channel {
    private String createdAt;
    private String name;
    private String color;
    private List<String> flags;
}
