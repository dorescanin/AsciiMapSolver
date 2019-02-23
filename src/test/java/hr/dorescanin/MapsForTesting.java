package hr.dorescanin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

class MapsForTesting {


    static String map1() {
        final File file = new File("src/test/java/hr/dorescanin/map1.txt");

        try {
            return new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    static String map2 =

            " @\r\n" +
            " | C----+\r\n" +
            " A |    |\r\n" +
            " +---B--+\r\n" +
            "   |      x\r\n" +
            "   |      |\r\n" +
            "   +---D--+";

    static String map3 =

            "  @---+\r\n" +
            "      B\r\n" +
            "K-----|--A\r\n" +
            "|     |  |\r\n" +
            "|  +--E  |\r\n" +
            "|  |     |\r\n" +
            "+--E--Ex C\r\n" +
            "   |     |\r\n" +
            "   +--F--+";
}
