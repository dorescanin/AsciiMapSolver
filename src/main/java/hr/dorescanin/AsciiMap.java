package hr.dorescanin;

import hr.dorescanin.util.CoordinatePair;

public class AsciiMap {

    private int matrixHeight;
    private int matrixWidth;
    private char[][] asciiMatrix;
    private CoordinatePair current;

    /**
     * Make constructor default to force clients to use {@link AsciiMapBuilder#build(String)}
     * <p>
     * <b>Note:</b> It is assumed that clients set up proper values for all parameters.
     * I.e. if {@code matrixHeight} and/or {@code matrixWidth} aren't compatible with real lengths from
     * {@code asciiMatrix}, there is no guarantee that subsequent usages of this class will function correctly.
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
