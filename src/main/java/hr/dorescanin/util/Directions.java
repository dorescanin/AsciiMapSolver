package hr.dorescanin.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum Directions {
    LEFT, RIGHT, UP, DOWN;

    public Directions getOpposite() {
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

    public List<Directions> getPerpendicular() {
        final List<Directions> perpendicular = new ArrayList<>();

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

    public static List<Directions> getOpposites(List<Directions> directions) {
        return Arrays.stream(values()).filter(t -> !directions.contains(t)).collect(Collectors.toList());
    }
}
