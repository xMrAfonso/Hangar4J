package me.mrafonso.hangar4j.impl.project;

public record UserActions(boolean starred, boolean watching, boolean flagged) {

    public boolean isStarred() {
        return starred;
    }

    public boolean isWatching() {
        return watching;
    }

    public boolean isFlagged() {
        return flagged;
    }
}
