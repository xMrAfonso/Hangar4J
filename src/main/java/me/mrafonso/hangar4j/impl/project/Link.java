package me.mrafonso.hangar4j.impl.project;

import java.util.List;


public class Link {
    int id;
    String type;
    String title;
    List<SubLink> links;

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public List<SubLink> getLinks() {
        return links;
    }
}
