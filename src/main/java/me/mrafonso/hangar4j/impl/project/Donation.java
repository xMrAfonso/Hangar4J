package me.mrafonso.hangar4j.impl.project;

public record Donation(boolean enable, String subject) {

    public boolean isEnable() {
        return enable;
    }

    public String getSubject() {
        return subject;
    }
}
