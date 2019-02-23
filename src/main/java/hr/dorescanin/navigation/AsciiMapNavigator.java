package hr.dorescanin.navigation;

import hr.dorescanin.model.AsciiMap;
import hr.dorescanin.util.CoordinatePair;
import hr.dorescanin.util.Direction;

import static hr.dorescanin.util.Direction.*;

public class AsciiMapNavigator {

    private AsciiMap map;

    public AsciiMapNavigator(AsciiMap map) {
        this.map = map;
    }

    public CoordinatePair nextPosition(CoordinatePair currentPosition, Direction nextDirection) {
        final int x = currentPosition.getX();
        final int y = currentPosition.getY();

        if (nextDirection == null) {
            return null;
        }

        switch (nextDirection) {
            case LEFT:
                if (isNextPositionWithinMatrix(x, y, LEFT) && isLeftNonEmpty(x, y)) {
                    return shiftLeft(x, y);
                }
                break;
            case RIGHT:
                if (isNextPositionWithinMatrix(x, y, RIGHT) && isRightNonEmpty(x, y)) {
                    return shiftRight(x, y);
                }
                break;
            case UP:
                if (isNextPositionWithinMatrix(x, y, UP) && isUpNonEmpty(x, y)) {
                    return shiftUp(x, y);
                }
                break;
            case DOWN:
                if (isNextPositionWithinMatrix(x, y, DOWN) && isDownNonEmpty(x, y)) {
                    return shiftDown(x, y);
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + nextDirection);
        }

        return null;
    }

    private CoordinatePair shiftDown(int x, int y) {
        return new CoordinatePair(x + 1, y);
    }

    private CoordinatePair shiftUp(int x, int y) {
        return new CoordinatePair(x - 1, y);
    }

    private CoordinatePair shiftRight(int x, int y) {
        return new CoordinatePair(x, y + 1);
    }

    private CoordinatePair shiftLeft(int x, int y) {
        return new CoordinatePair(x, y - 1);
    }


    private boolean isDownNonEmpty(int x, int y) {
        return getCharAtPosition(x + 1, y) != ' ';
    }

    private boolean isUpNonEmpty(int x, int y) {
        return getCharAtPosition(x - 1, y) != ' ';
    }

    private boolean isRightNonEmpty(int x, int y) {
        return getCharAtPosition(x, y + 1) != ' ';
    }

    private boolean isLeftNonEmpty(int x, int y) {
        return getCharAtPosition(x, y - 1) != ' ';
    }

    public boolean isNextPositionWithinMatrix(int x, int y, Direction nextPosition) {
        if (nextPosition == null) {
            return false;
        }

        switch (nextPosition) {
            case LEFT:
                return y - 1 >= 0;
            case RIGHT:
                return y + 1 < map.getMatrixWidth();
            case UP:
                return x - 1 >= 0;
            case DOWN:
                return x + 1 < map.getMatrixHeight();
            default:
                throw new IllegalStateException("Unexpected value: " + nextPosition);
        }
    }

    public char getCharAtPosition(CoordinatePair pair) {
        return getCharAtPosition(pair.getX(), pair.getY());
    }

    private char getCharAtPosition(int x, int y) {
        final char[][] matrix = map.getAsciiMatrix();
        return matrix[x][y];
    }
}
