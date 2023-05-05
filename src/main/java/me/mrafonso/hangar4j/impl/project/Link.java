package me.mrafonso.hangar4j.impl.project;

import java.util.List;

public record Link(int id, String type, String title, List<SubLink> links) {

}
