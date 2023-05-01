package me.mrafonso.hangar4j.impl.project;

import java.util.List;

public class Settings {
    List<Link> links;
    List<String> tags;
    License license;
    List<String> keywords;
    String sponsors;
    Donation donation;

    public List<Link> getLinks() {
        return links;
    }

    public List<String> getTags() {
        return tags;
    }

    public License getLicense() {
        return license;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    public String getSponsors() {
        return sponsors;
    }

    public Donation getDonation() {
        return donation;
    }
}
