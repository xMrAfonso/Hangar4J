package me.mrafonso.hangar4j.impl;

public record Pagination(int limit, int offset, int count) {
    public int getLimit() {
        return limit;
    }

    public int getOffset() {
        return offset;
    }

    public int getCount() {
        return count;
    }
}
