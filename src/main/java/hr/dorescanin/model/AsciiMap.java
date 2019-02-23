package hr.dorescanin.model;

public class AsciiMap {

    private int matrixHeight;
    private int matrixWidth;
    private char[][] asciiMatrix;

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
    }

    public int getMatrixHeight() {
        return matrixHeight;
    }

    public int getMatrixWidth() {
        return matrixWidth;
    }

    public char[][] getAsciiMatrix() {
        return asciiMatrix;
    }
}
