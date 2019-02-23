package hr.dorescanin.validation;

import hr.dorescanin.model.AsciiMap;
import hr.dorescanin.util.CoordinatePair;

/**
 * Validates whether map is in legal state before beginning traversal - exactly one starting position ('@') and one
 * ending position ('x') must be present. If map isn't in legal state, a {@link IllegalStateException} will be thrown.
 */
public class AsciiMapValidator {

    private AsciiMap map;

    public AsciiMapValidator(AsciiMap map) {
        this.map = map;
    }

    public CoordinatePair validateInitialPosition() {

        CoordinatePair startPosition = findToken(map, '@', "More than one start position ('@') found!");

        if (startPosition == null) {
            throw new IllegalStateException("No start position ('@') found!");
        }

        return startPosition;
    }


    public CoordinatePair validateFinalPosition() {

        CoordinatePair startPosition = findToken(map, 'x', "More than one end position ('x') found!");

        if (startPosition == null) {
            throw new IllegalStateException("No end position ('x') found!");
        }

        return startPosition;
    }

    private CoordinatePair findToken(AsciiMap map, char searchingToken, String multipleTokensFound) {
        final char[][] matrix = map.getAsciiMatrix();
        CoordinatePair startPosition = null;
        int counter = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (searchingToken == matrix[i][j]) {
                    counter++;
                    if (counter > 1) {
                        throw new IllegalStateException(multipleTokensFound);
                    }
                    startPosition = new CoordinatePair(i, j);
                }
            }
        }
        return startPosition;
    }
}
