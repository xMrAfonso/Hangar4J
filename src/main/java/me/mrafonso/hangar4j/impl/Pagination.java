package me.mrafonso.hangar4j.impl;

/**
 * Represents a Hangar Pagination.
 *
 * @param limit     The limit of the pagination.
 * @param offset    The offset of the pagination.
 * @param count     The count of the pagination.
 */
public record Pagination(int limit, int offset, int count) {

}
