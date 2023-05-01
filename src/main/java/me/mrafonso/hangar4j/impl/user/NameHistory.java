package me.mrafonso.hangar4j.impl.user;

public record NameHistory(String oldName, String newName, String date) {

    public String getOldName() {
        return oldName;
    }

    public String getNewName() {
        return newName;
    }

    public String getDate() {
        return date;
    }
}
