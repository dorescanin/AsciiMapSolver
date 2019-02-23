package hr.dorescanin;

import hr.dorescanin.util.Directions;
import org.junit.Assert;
import org.junit.Test;

import static hr.dorescanin.MapsForTesting.*;
import static org.junit.Assert.*;

public class AsciiMapTraversalTest {

    @Test
    public void isNextPositionWithinMatrix() {
        final AsciiMapTraversal traversal = new AsciiMapTraversal(AsciiMapBuilder.build(map1));

        // upper left
        Assert.assertFalse(traversal.isNextPositionWithinMatrix(0, 0, Directions.LEFT));
        Assert.assertFalse(traversal.isNextPositionWithinMatrix(0, 0, Directions.UP));
        Assert.assertTrue(traversal.isNextPositionWithinMatrix(0, 0, Directions.RIGHT));
        Assert.assertTrue(traversal.isNextPositionWithinMatrix(0, 0, Directions.DOWN));

        // lower right
        Assert.assertTrue(traversal.isNextPositionWithinMatrix(4, 8, Directions.LEFT));
        Assert.assertTrue(traversal.isNextPositionWithinMatrix(4, 8, Directions.UP));
        Assert.assertFalse(traversal.isNextPositionWithinMatrix(4, 8, Directions.RIGHT));
        Assert.assertFalse(traversal.isNextPositionWithinMatrix(4, 8, Directions.DOWN));

        // lower left
        Assert.assertFalse(traversal.isNextPositionWithinMatrix(4, 0, Directions.LEFT));
        Assert.assertTrue(traversal.isNextPositionWithinMatrix(4, 0, Directions.UP));
        Assert.assertTrue(traversal.isNextPositionWithinMatrix(4, 0, Directions.RIGHT));
        Assert.assertFalse(traversal.isNextPositionWithinMatrix(4, 0, Directions.DOWN));

        // upper right
        Assert.assertTrue(traversal.isNextPositionWithinMatrix(0, 8, Directions.LEFT));
        Assert.assertFalse(traversal.isNextPositionWithinMatrix(0, 8, Directions.UP));
        Assert.assertFalse(traversal.isNextPositionWithinMatrix(0, 8, Directions.RIGHT));
        Assert.assertTrue(traversal.isNextPositionWithinMatrix(0, 8, Directions.DOWN));

        // center
        Assert.assertTrue(traversal.isNextPositionWithinMatrix(2, 4, Directions.LEFT));
        Assert.assertTrue(traversal.isNextPositionWithinMatrix(2, 4, Directions.UP));
        Assert.assertTrue(traversal.isNextPositionWithinMatrix(2, 4, Directions.RIGHT));
        Assert.assertTrue(traversal.isNextPositionWithinMatrix(2, 4, Directions.DOWN));
    }

    @Test
    public void testTraversal() {
        final AsciiMapTraversal traversal = new AsciiMapTraversal(AsciiMapBuilder.build(map2));
        traversal.traverse();
    }
}