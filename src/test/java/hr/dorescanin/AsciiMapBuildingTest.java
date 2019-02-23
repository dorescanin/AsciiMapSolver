package hr.dorescanin;

import hr.dorescanin.model.AsciiMap;
import hr.dorescanin.model.AsciiMapBuilder;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class AsciiMapBuildingTest {

    @Test
    public void testMapBuilding1() {
        genericMapBuilding(MapsForTesting.map1(), 5, 9);
    }

    @Test
    public void testMapBuilding2() {
        genericMapBuilding(MapsForTesting.map2, 7, 11);
    }

    @Test
    public void testMapBuilding3() {
        genericMapBuilding(MapsForTesting.map3, 9, 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEmptyMapBuilding() {

        String map = "" ;
        genericMapBuilding(map, 1, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNullMapBuilding() {

        String map = null;
        genericMapBuilding(map, 0, 0);
    }


    private void genericMapBuilding(String map, int rowNumber, int columnNumber) {
        final AsciiMap asciiMap = AsciiMapBuilder.build(map);
        final char[][] matrix = asciiMap.getAsciiMatrix();

        assertEquals(matrix.length, rowNumber);

        final int maxColumnLength = Arrays.stream(matrix).mapToInt(t -> t.length).max().orElse(-1);
        assertEquals(maxColumnLength, columnNumber);
    }
}