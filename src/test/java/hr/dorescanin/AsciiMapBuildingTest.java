package hr.dorescanin;

import org.junit.Test;

import java.util.Arrays;
import java.util.OptionalInt;

import static org.junit.Assert.*;

public class AsciiMapBuildingTest {

    @Test
    public void testMapBuilding1() {

        String map =
                "@---A---+\r\n" +
                "        |\r\n" +
                "x-B-+   C\r\n" +
                "    |   |\r\n" +
                "    +---+";

        genericMapBuilding(map, 5, 9);
    }

    @Test
    public void testMapBuilding2() {

        String map =
                " @\r\n" +
                " | C----+\r\n" +
                " A |    |\r\n" +
                " +---B--+\r\n" +
                "   |      x\r\n" +
                "   |      |\r\n" +
                "   +---D--+";

        genericMapBuilding(map, 7, 11);
    }

    @Test
    public void testMapBuilding3() {

        String map =
                "  @---+\r\n" +
                "      B\r\n" +
                "K-----|--A\r\n" +
                "|     |  |\r\n" +
                "|  +--E  |\r\n" +
                "|  |     |\r\n" +
                "+--E--Ex C\r\n" +
                "   |     |\r\n" +
                "   +--F--+";

        genericMapBuilding(map, 9, 10);
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

        for (int i = 0; i < matrix.length; i++) {
            assertArrayEquals(map.split("\r\n")[i].toCharArray(), matrix[i]);
        }

        assertEquals(matrix.length, rowNumber);

        final int maxColumnLength = Arrays.stream(matrix).mapToInt(t -> t.length).max().orElse(-1);
        assertEquals(maxColumnLength, columnNumber);
    }
}