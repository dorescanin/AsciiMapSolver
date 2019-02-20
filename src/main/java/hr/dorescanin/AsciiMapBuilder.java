package hr.dorescanin;

import java.util.Arrays;

public class AsciiMapBuilder {

    /**
     * Build ascii map matrix from given String. Map lines must be terminated with "\r\n". Example of a map:
     * <pre>
     *{@literal @}---A---+
     *         |
     * x-B-+   C
     *     |   |
     *     +---+
     * </pre>
     * @param map See above documentation
     * @return Correctly built {@link AsciiMap}
     */
    public static AsciiMap build(String map) {

        if (map == null) {
            throw new IllegalArgumentException("Ascii map must not be null");
        }

        final String[] lines = map.split(System.lineSeparator());
        int matrixHeight = lines.length;

        char[][] asciiMap = new char[matrixHeight][];

        int matrixWidth = 0;

        int lineCounter = 0;

        for (String line : lines) {

            final int length = line.length();

            if (length > matrixWidth) {
                matrixWidth = length;
            }

            asciiMap[lineCounter++] = line.toCharArray();
        }

        if (matrixWidth == 0) {
            throw new IllegalArgumentException("Ascii map must have at least one column");
        }

        // resize arrays so we have a true matrix
        for (int i = 0; i < asciiMap.length; i++) {
            char[] line = asciiMap[i];
            asciiMap[i] = Arrays.copyOf(line, matrixWidth);

            // after copying/resizing matrix, fill null characters with empty spaces for consistency
            for (int j = 0; j < asciiMap[i].length; j++) {
                if (asciiMap[i][j] == '\u0000') {
                    asciiMap[i][j] = ' ';
                }
            }
        }

        return new AsciiMap(matrixHeight, matrixWidth, asciiMap);
    }
}
