package hr.dorescanin.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents direction for movement. Also used to keep track of previous direction while navigating
 * ascii map since backtracking is not supported.
 */
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

    /**
     * <b>Note</b>: currently unused, but can be useful for handling characters like '+' which dictate that
     * direction change is necessary by 90°.
     * @return Two directions that are perpendicular to the current one.
     */
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

    /**
     * Get all possible directions that are not contained in {@code directions}.
     * <p>
     * Used for filtering next viable directions. E.g. if we're moving right twice and we hit a wall,
     * we know that we arrived from left, we cannot go right, so we're left with up and down.
     * @param directions List of directions whose opposites are needed.
     * @return Opposite directions.
     */
    public static List<Direction> getOpposites(List<Direction> directions) {
        return Arrays.stream(values()).filter(d -> !directions.contains(d)).collect(Collectors.toList());
    }
}
