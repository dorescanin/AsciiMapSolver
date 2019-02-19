package hr.dorescanin;

import hr.dorescanin.util.CoordinatePair;

public class AsciiMap {

    private int matrixHeight;
    private int matrixWidth;
    private char[][] asciiMatrix;
    private CoordinatePair current;

    /**
     * Make constructor private to force clients to use {@link AsciiMapBuilder#build(String)}
     */
    AsciiMap(int matrixHeight, int matrixWidth, char[][] asciiMatrix) {
        this.matrixHeight = matrixHeight;
        this.matrixWidth = matrixWidth;
        this.asciiMatrix = asciiMatrix;
        this.current = new CoordinatePair(0, 0);
    }

    public int getMatrixHeight() {
        return matrixHeight;
    }

    public int getMatrixWidth() {
        return matrixWidth;
    }

    char[][] getAsciiMatrix() {
        return asciiMatrix;
    }

    public CoordinatePair getCurrent() {
        return current;
    }
}
