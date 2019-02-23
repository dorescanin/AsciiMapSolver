package hr.dorescanin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

class MapsForTesting {


    static String map1() {
        return getMapContentFromFile("map1.txt");
    }

    static String map2() {
        return getMapContentFromFile("map2.txt");
    }

    static String map3() {
        return getMapContentFromFile("map3.txt");
    }

    static String mapDoubleStart() {
        return getMapContentFromFile("mapDoubleStart.txt");
    }

    static String mapDoubleEnd() {
        return getMapContentFromFile("mapDoubleEnd.txt");
    }

    private static String getMapContentFromFile(String fileName) {
        final File file = new File("src/test/java/hr/dorescanin/" + fileName);

        try {
            return new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
