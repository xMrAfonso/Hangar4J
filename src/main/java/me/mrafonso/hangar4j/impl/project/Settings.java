package me.mrafonso.hangar4j.impl.project;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Settings {
    private List<Link> links;
    private List<String> tags;
    private License license;
    private List<String> keywords;
    private String sponsors;
    private Donation donation;
}
