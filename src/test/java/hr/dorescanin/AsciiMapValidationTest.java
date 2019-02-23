package hr.dorescanin;

import hr.dorescanin.util.CoordinatePair;
import org.junit.Assert;
import org.junit.Test;

public class AsciiMapValidationTest {

    @Test
    public void testMap1() {
        final AsciiMap map1 = AsciiMapBuilder.build(MapsForTesting.map1);
        final AsciiMapValidator validator = new AsciiMapValidator(map1);
        final CoordinatePair initialPosition = validator.validateInitialPosition();
        final CoordinatePair finalPosition = validator.validateFinalPosition();

        Assert.assertEquals(initialPosition, new CoordinatePair(0, 0));
        Assert.assertEquals(finalPosition, new CoordinatePair(2, 0));
    }

    @Test
    public void testMap2() {
        final AsciiMap map2 = AsciiMapBuilder.build(MapsForTesting.map2);
        final AsciiMapValidator validator = new AsciiMapValidator(map2);
        final CoordinatePair initialPosition = validator.validateInitialPosition();
        final CoordinatePair finalPosition = validator.validateFinalPosition();

        Assert.assertEquals(initialPosition, new CoordinatePair(0, 1));
        Assert.assertEquals(finalPosition, new CoordinatePair(4, 10));
    }

    @Test
    public void testMap3() {
        final AsciiMap map3 = AsciiMapBuilder.build(MapsForTesting.map3);
        final AsciiMapValidator validator = new AsciiMapValidator(map3);
        final CoordinatePair initialPosition = validator.validateInitialPosition();
        final CoordinatePair finalPosition = validator.validateFinalPosition();

        Assert.assertEquals(initialPosition, new CoordinatePair(0, 2));
        Assert.assertEquals(finalPosition, new CoordinatePair(6, 7));
    }

    @Test(expected = IllegalStateException.class)
    public void testEmptyMapInitialPosition() {
        final String asciiMap = "123";
        validateInitial(asciiMap);
    }

    @Test(expected = IllegalStateException.class)
    public void testEmptyMapFinalPosition() {
        final String asciiMap = "123";
        validateFinal(asciiMap);
    }

    @Test(expected = IllegalStateException.class)
    public void testTwoInitialPositions() {
        final String asciiMap = "@@";
        validateInitial(asciiMap);
    }

    @Test(expected = IllegalStateException.class)
    public void testTwoFinalPositions() {
        final String asciiMap = "xx";
        validateFinal(asciiMap);
    }

    private void validateFinal(String asciiMap) {
        final AsciiMap map = AsciiMapBuilder.build(asciiMap);
        final AsciiMapValidator validator = new AsciiMapValidator(map);
        validator.validateFinalPosition();
    }

    private void validateInitial(String asciiMap) {
        final AsciiMap map = AsciiMapBuilder.build(asciiMap);
        final AsciiMapValidator validator = new AsciiMapValidator(map);
        validator.validateInitialPosition();
    }
}
