package hr.dorescanin;

import hr.dorescanin.model.AsciiMap;
import hr.dorescanin.model.AsciiMapBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        final int length = args.length;

        if (length == 0) {
            System.out.println("First argument must be filename containing map");
            return;
        }

        final String fileName = args[0];

        final byte[] bytes = Files.readAllBytes(Paths.get(fileName));
        final String map = new String(bytes);

        final AsciiMap asciiMap = AsciiMapBuilder.build(map);
        final AsciiMapTraversal traversal = new AsciiMapTraversal(asciiMap);
        traversal.traverse();
    }
}
