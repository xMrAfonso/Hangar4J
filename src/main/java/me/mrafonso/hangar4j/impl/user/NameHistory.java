package me.mrafonso.hangar4j.impl.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NameHistory {
    private String oldName;
    private String newName;
    private String date;
}
