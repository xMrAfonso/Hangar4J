package me.mrafonso.hangar4j.impl.project;

import java.util.List;

/**
 * Represents a Hangar Project Settings.
 *
 * @param links     The list of Link objects.
 * @param tags      The list of tags.
 * @param license   The License object.
 * @param keywords  The list of keywords.
 * @param sponsors  The sponsors.
 * @param donation  The Donation object.
 */
public record Settings(List<Link> links, List<String> tags, License license, List<String> keywords, String sponsors, Donation donation) {

}
