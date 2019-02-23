package hr.dorescanin.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Direction {
    LEFT, RIGHT, UP, DOWN;

    public Direction getOpposite() {
        switch (this) {
            case LEFT:
                return RIGHT;
            case RIGHT:
                return LEFT;
            case UP:
                return DOWN;
            case DOWN:
                return UP;
            default:
                throw new IllegalStateException("Unexpected value: " + this);
        }
    }

    public List<Direction> getPerpendicular() {
        final List<Direction> perpendicular = new ArrayList<>();

        switch (this) {
            case LEFT:
            case RIGHT:
                perpendicular.add(UP);
                perpendicular.add(DOWN);
                return perpendicular;
            case UP:
            case DOWN:
                perpendicular.add(LEFT);
                perpendicular.add(RIGHT);
                return perpendicular;
            default:
                throw new IllegalStateException("Unexpected value: " + this);
        }
    }

    public static List<Direction> getOpposites(List<Direction> directions) {
        return Arrays.stream(values()).filter(t -> !directions.contains(t)).collect(Collectors.toList());
    }
}
