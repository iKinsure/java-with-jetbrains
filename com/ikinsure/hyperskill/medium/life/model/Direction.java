package com.ikinsure.hyperskill.medium.life.model;

/**
 * To move to dir use + dx, + dy
 */
public enum Direction {
    NORTH(0, 1),
    NORTH_EAST(1, 1),
    EAST(1, 0),
    SOUTH_EAST(1, -1),
    SOUTH(0, -1),
    SOUTH_WEST(-1, -1),
    WEST(-1, 0),
    NORTH_WEST(-1, 1);
    final int dx;
    final int dy;
    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }
}
