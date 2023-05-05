package me.mrafonso.hangar4j.impl.project;

import java.util.List;

public record Settings(List<Link> links, List<String> tags, License license, List<String> keywords, String sponsors, Donation donation) {

}
