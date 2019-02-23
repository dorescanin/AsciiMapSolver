package hr.dorescanin;

import hr.dorescanin.util.CoordinatePair;

public class AsciiMapValidator {

    private AsciiMap map;

    public AsciiMapValidator(AsciiMap map) {
        this.map = map;
    }

    public CoordinatePair validateInitialPosition() {

        CoordinatePair startPosition = findToken(map.getAsciiMatrix(), '@', "More than one start position ('@') found!");

        if (startPosition == null) {
            throw new IllegalStateException("No start position ('@') found!");
        }

        return startPosition;
    }


    public CoordinatePair validateFinalPosition() {

        CoordinatePair startPosition = findToken(map.getAsciiMatrix(), 'x', "More than one end position ('x') found!");

        if (startPosition == null) {
            throw new IllegalStateException("No end position ('x') found!");
        }

        return startPosition;
    }

    private CoordinatePair findToken(char[][] matrix, char searchingToken, String multipleTokensFound) {
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
