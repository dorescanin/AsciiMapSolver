package hr.dorescanin;

import hr.dorescanin.model.AsciiMap;
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

class AsciiMapTraversal {

    private AsciiMapNavigator navigator;
    private CoordinatePair initialPosition;
    private CoordinatePair currentPosition;
    private StringBuilder letters = new StringBuilder();
    private StringBuilder pathAsCharacters = new StringBuilder();
    private Set<CoordinatePair> visitedCoordinates;
    private Direction forbidden, continuation;

    private final Pattern uppercaseLetters = Pattern.compile("[A-Z]");

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

    void traverse() {

        for (Direction d : values()) {
            final char nextChar = peek(d);
            if (nextChar != ' ') {
                continuation = d;
                forbidden = d.getOpposite();
                goNext(initialPosition, d);
                break;
            }
        }

        for (int i = 0; i < 1000; i++) {

            final Direction d = findNextPossibleDirection();
            continuation = d;
            forbidden = d.getOpposite();
            goNext(currentPosition, d);

            final char charAtPosition = navigator.getCharAtPosition(currentPosition);

            if (charAtPosition == 'x') {
                System.out.println("Win!");
                System.out.println(currentPosition);
                System.out.println(pathAsCharacters);
                System.out.println(letters);
                break;
            }
        }
    }

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

    private char peek(Direction nextDirection) {
        return peek(currentPosition, nextDirection);
    }

    private char peek(CoordinatePair currentPosition, Direction nextDirection) {
        final CoordinatePair nextPosition = navigator.nextPosition(currentPosition, nextDirection);
        if (nextPosition != null) {
            return navigator.getCharAtPosition(nextPosition);
        }
        return ' ';
    }

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
