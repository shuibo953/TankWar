package com.shuibo.game;

public enum Dir {
    UP, DOWN, LEFT, RIGHT;

    public Dir getReverse() {
        switch (this) {
            case UP:
                return DOWN;
            case DOWN:
                return UP;
            case LEFT:
                return RIGHT;
            case RIGHT:
                return LEFT;
            default:
                return null;
        }
    }
}