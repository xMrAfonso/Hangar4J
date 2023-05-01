package me.mrafonso.hangar4j.impl;

import lombok.Getter;

@Getter
public class Pagination {
    private int limit;
    private int offset;
    private int count;
}
