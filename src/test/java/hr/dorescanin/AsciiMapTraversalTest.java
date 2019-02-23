package hr.dorescanin;

import hr.dorescanin.model.AsciiMapBuilder;
import hr.dorescanin.util.Direction;
import org.junit.Assert;
import org.junit.Test;

import static hr.dorescanin.MapsForTesting.map1;

public class AsciiMapTraversalTest {

    @Test
    public void isNextPositionWithinMatrix() {
        final AsciiMapTraversal traversal = new AsciiMapTraversal(AsciiMapBuilder.build(map1()));
        final AsciiMapNavigator navigator = traversal.getNavigator();

        // upper left
        Assert.assertFalse(navigator.isNextPositionWithinMatrix(0, 0, Direction.LEFT));
        Assert.assertFalse(navigator.isNextPositionWithinMatrix(0, 0, Direction.UP));
        Assert.assertTrue(navigator.isNextPositionWithinMatrix(0, 0, Direction.RIGHT));
        Assert.assertTrue(navigator.isNextPositionWithinMatrix(0, 0, Direction.DOWN));

        // lower right
        Assert.assertTrue(navigator.isNextPositionWithinMatrix(4, 8, Direction.LEFT));
        Assert.assertTrue(navigator.isNextPositionWithinMatrix(4, 8, Direction.UP));
        Assert.assertFalse(navigator.isNextPositionWithinMatrix(4, 8, Direction.RIGHT));
        Assert.assertFalse(navigator.isNextPositionWithinMatrix(4, 8, Direction.DOWN));

        // lower left
        Assert.assertFalse(navigator.isNextPositionWithinMatrix(4, 0, Direction.LEFT));
        Assert.assertTrue(navigator.isNextPositionWithinMatrix(4, 0, Direction.UP));
        Assert.assertTrue(navigator.isNextPositionWithinMatrix(4, 0, Direction.RIGHT));
        Assert.assertFalse(navigator.isNextPositionWithinMatrix(4, 0, Direction.DOWN));

        // upper right
        Assert.assertTrue(navigator.isNextPositionWithinMatrix(0, 8, Direction.LEFT));
        Assert.assertFalse(navigator.isNextPositionWithinMatrix(0, 8, Direction.UP));
        Assert.assertFalse(navigator.isNextPositionWithinMatrix(0, 8, Direction.RIGHT));
        Assert.assertTrue(navigator.isNextPositionWithinMatrix(0, 8, Direction.DOWN));

        // center
        Assert.assertTrue(navigator.isNextPositionWithinMatrix(2, 4, Direction.LEFT));
        Assert.assertTrue(navigator.isNextPositionWithinMatrix(2, 4, Direction.UP));
        Assert.assertTrue(navigator.isNextPositionWithinMatrix(2, 4, Direction.RIGHT));
        Assert.assertTrue(navigator.isNextPositionWithinMatrix(2, 4, Direction.DOWN));
    }

    @Test
    public void testTraversal1() {
        final AsciiMapTraversal traversal = new AsciiMapTraversal(AsciiMapBuilder.build(MapsForTesting.map1()));
        traversal.traverse();
        Assert.assertEquals(traversal.getLetters(), "ACB");
        Assert.assertEquals(traversal.getPathAsCharacters(), "@---A---+|C|+---+|+-B-x");
    }

    @Test
    public void testTraversal2() {
        final AsciiMapTraversal traversal = new AsciiMapTraversal(AsciiMapBuilder.build(MapsForTesting.map2()));
        traversal.traverse();
        Assert.assertEquals(traversal.getLetters(), "ABCD");
        Assert.assertEquals(traversal.getPathAsCharacters(), "@|A+---B--+|+----C|-||+---D--+|x");
    }

    @Test
    public void testTraversal3() {
        final AsciiMapTraversal traversal = new AsciiMapTraversal(AsciiMapBuilder.build(MapsForTesting.map3()));
        traversal.traverse();
        Assert.assertEquals(traversal.getLetters(), "BEEFCAKE");
        Assert.assertEquals(traversal.getPathAsCharacters(), "@---+B||E--+|E|+--F--+|C|||A--|-----K|||+--E--Ex");
    }

}