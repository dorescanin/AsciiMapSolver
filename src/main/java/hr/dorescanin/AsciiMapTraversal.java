package hr.dorescanin;

import hr.dorescanin.util.CoordinatePair;
import hr.dorescanin.util.Directions;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import static hr.dorescanin.util.Directions.*;

public class AsciiMapTraversal {

    private AsciiMap map;
    private CoordinatePair initialPosition, currentPosition, finalPosition;
    private StringBuilder letters = new StringBuilder();
    private StringBuilder pathAsCharacters = new StringBuilder();
    private Set<CoordinatePair> visitedCoordinates;

    public AsciiMapTraversal(AsciiMap map) {
        this.map = map;
        final AsciiMapValidator validator = new AsciiMapValidator(map);
        initialPosition = validator.validateInitialPosition();
        currentPosition = initialPosition;
        finalPosition = validator.validateFinalPosition();
        visitedCoordinates = new HashSet<>();
        visitedCoordinates.add(initialPosition);
        pathAsCharacters.append("@");
    }

    public void traverse() {

        System.out.println(currentPosition);
        System.out.println(peek(DOWN));
        goNext(currentPosition, DOWN);
        goNext(currentPosition, DOWN);
        goNext(currentPosition, DOWN);
        System.out.println(currentPosition);

    }

    char peek(Directions nextDirection) {
        return peek(currentPosition, nextDirection);
    }

    char peek(CoordinatePair currentPosition, Directions nextDirection) {
        final CoordinatePair nextPosition = nextPosition(currentPosition, nextDirection);
        if (nextPosition != null) {
            return getCharAtPosition(nextPosition);
        }
        return ' ';
    }

    CoordinatePair goNext(CoordinatePair currentPosition, Directions nextDirection) {
        final CoordinatePair newPosition = nextPosition(currentPosition, nextDirection);
        if (!visitedCoordinates.contains(newPosition)) {
            visitedCoordinates.add(newPosition);
            final char charAtPosition = getCharAtPosition(newPosition);
            pathAsCharacters.append(charAtPosition);
            final Pattern bigletter = Pattern.compile("[A-Z]");
            final boolean isBigLetter = bigletter.matcher(Character.toString(charAtPosition)).matches();
            if (isBigLetter) {
                letters.append(charAtPosition);
            }
        }
        this.currentPosition = newPosition;
        return newPosition;
    }


    CoordinatePair nextPosition(CoordinatePair currentPosition, Directions nextDirection) {
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

    boolean isNextPositionWithinMatrix(int x, int y, Directions nextPosition) {
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

    boolean isNextPositionValid(CoordinatePair currentPosition, Directions nextPosition) {
        final int x = currentPosition.getX();
        final int y = currentPosition.getY();

        if (nextPosition == null) {
            return false;
        }




        return false;
    }

    private char getCharAtPosition(CoordinatePair pair) {
        return getCharAtPosition(pair.getX(), pair.getY());
    }

    private char getCharAtPosition(int x, int y) {
        final char[][] matrix = map.getAsciiMatrix();
         return matrix[x][y];
    }

    public AsciiMap getMap() {
        return map;
    }
}
