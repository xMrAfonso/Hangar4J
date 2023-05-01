package me.mrafonso.hangar4j.impl.project;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Link {
    private int id;
    private String type;
    private String title;
    private List<SubLink> links;
}
