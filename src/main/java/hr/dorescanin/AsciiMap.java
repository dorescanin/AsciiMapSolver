package hr.dorescanin;

import hr.dorescanin.util.CoordinatePair;

import java.util.HashSet;
import java.util.Set;

public class AsciiMap {

    private int matrixHeight;
    private int matrixWidth;
    private char[][] asciiMatrix;
    private CoordinatePair current;
    private Set<CoordinatePair> visitedCoordinates;

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
        this.visitedCoordinates = new HashSet<>();
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

    public void visit(CoordinatePair pair) {
        visitedCoordinates.add(pair);
    }

    public boolean isVisited(CoordinatePair pair) {
        return visitedCoordinates.contains(pair);
    }

}
