package hr.dorescanin;

import hr.dorescanin.model.AsciiMap;
import hr.dorescanin.navigation.AsciiMapNavigator;
import hr.dorescanin.util.CoordinatePair;
import hr.dorescanin.util.Direction;
import hr.dorescanin.validation.AsciiMapValidator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import static hr.dorescanin.util.Direction.getOpposites;
import static hr.dorescanin.util.Direction.values;

/**
 * Main class implementing algorithm for traversal. After instantiation, {@link #traverse()} should be called at most once.
 */
class AsciiMapTraversal {

    private static final int MAX_ITERATIONS = 100000;
    private AsciiMapNavigator navigator;
    private CoordinatePair initialPosition;
    private CoordinatePair currentPosition;
    private StringBuilder letters = new StringBuilder();
    private StringBuilder pathAsCharacters = new StringBuilder();
    private Set<CoordinatePair> visitedCoordinates;
    private Direction forbidden, continuation;

    private final Pattern uppercaseLetters = Pattern.compile("[A-Z]");
    private boolean isAlreadyTraversed = false;

    AsciiMapTraversal(AsciiMap map) {
        final AsciiMapValidator validator = new AsciiMapValidator(map);
        initialPosition = validator.validateInitialPosition();
        currentPosition = initialPosition;
        validator.validateFinalPosition();
        visitedCoordinates = new HashSet<>();
        visitedCoordinates.add(initialPosition);
        pathAsCharacters.append("@");
        navigator = new AsciiMapNavigator(map);
    }

    /**
     * Entry point for traversal. First finds initial next position and sets all needed variables for continued iteration.
     * Afterwards, tries to find the final character in the map.
     */
    void traverse() {

        if (isAlreadyTraversed) {
            throw new IllegalStateException("Map traversal can be used only once per class");
        }

        isAlreadyTraversed = true;

        for (Direction d : values()) {
            final char nextChar = peek(d);
            if (nextChar != ' ') {
                continuation = d;
                forbidden = d.getOpposite();
                goNext(initialPosition, d);
                break;
            }
        }

        for (int i = 0; i < MAX_ITERATIONS; i++) {
            final Direction d = findNextPossibleDirection();
            continuation = d;
            forbidden = d.getOpposite();
            goNext(currentPosition, d);

            final char charAtPosition = navigator.getCharAtPosition(currentPosition);

            if (charAtPosition == 'x') {
                System.out.println("----------------------------------------------------------------------------------");
                System.out.println(pathAsCharacters);
                System.out.println(letters);
                break;
            }
        }
    }

    /**
     * Finds next valid direction, based on current position, direction we arrived from and direction we're heading to.
     * <p>
     * Example:
     * <pre>
     * direction of movement: ->
     * current position: *
     * previous position (forbidden): LEFT
     * next favored position (continuation): RIGHT
     *
     *{@literal @}---A-*-+
     *         |
     * x-B-+   C
     *     |   |
     *     +---+
     * </pre>
     * Algorithm will try to follow the favoured direction (used for crossing already visited paths, check map 3).
     * If that is not possible, it will try to find next possible direction. In that case, it decides that previous
     * and continuation directions are forbidden and only 1 further direction is available, since no backtracking
     * is possible.
     * <p>
     * If there are 0 or 2 further directions available, an {@link IllegalStateException} is thrown.
     *
     * @return Next valid direction for map traversal
     */
    private Direction findNextPossibleDirection() {

        final char peek = peek(currentPosition, continuation);

        if (peek != ' ') {
            return continuation;
        }

        final List<Direction> otherPossibleDirections = getOpposites(Arrays.asList(forbidden, continuation));

        final Direction d1 = otherPossibleDirections.get(0);
        final Direction d2 = otherPossibleDirections.get(1);

        final char c1 = peek(currentPosition, d1);
        final char c2 = peek(currentPosition, d2);

        if (c1 != ' ' && c2 != ' ') {
            throw new IllegalStateException("Map doesn't seem to be traversible! Two other locations are possible!");
        }

        if (c1 == ' ' && c2 == ' ') {
            throw new IllegalStateException("Map doesn't seem to be traversible! No other locations are possible!");
        }

        return c1 != ' ' ? d1 : d2;
    }

    private char peek(Direction nextDirection) {
        return peek(currentPosition, nextDirection);
    }

    /**
     * Checks what lies at next position.
     * @param currentPosition Location from which peeking is being done.
     * @param nextDirection Direction for peeking.
     * @return Valid character, or empty character in case location is not traversable.
     */
    private char peek(CoordinatePair currentPosition, Direction nextDirection) {
        final CoordinatePair nextPosition = navigator.nextPosition(currentPosition, nextDirection);
        if (nextPosition != null) {
            return navigator.getCharAtPosition(nextPosition);
        }
        return ' ';
    }

    /**
     * Goes to next position and marks character that's being found.
     * @param currentPosition Current position.
     * @param nextDirection Further direction.
     */
    private void goNext(CoordinatePair currentPosition, Direction nextDirection) {
        final CoordinatePair newPosition = navigator.nextPosition(currentPosition, nextDirection);
        final char charAtPosition = navigator.getCharAtPosition(newPosition);
        pathAsCharacters.append(charAtPosition);

        if (!visitedCoordinates.contains(newPosition)) {
            visitedCoordinates.add(newPosition);
            final boolean isBigLetter = isUppercaseLetter(charAtPosition);
            if (isBigLetter) {
                letters.append(charAtPosition);
            }
        }

        this.currentPosition = newPosition;
    }

    /**
     * Validate whether or not character is uppercase, only ASCII letters are supported.
     * @param peek Character for checking.
     * @return True if {@code peek} is in range [A-Z], false otherwise.
     */
    private boolean isUppercaseLetter(char peek) {
        return uppercaseLetters.matcher(Character.toString(peek)).matches();
    }

    String getLetters() {
        return letters.toString();
    }

    String getPathAsCharacters() {
        return pathAsCharacters.toString();
    }

    AsciiMapNavigator getNavigator() {
        return navigator;
    }
}
