package hr.dorescanin.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static hr.dorescanin.util.Directions.*;

public class DirectionsTest {

    @Test
    public void getOpposite() {
        Assert.assertEquals(UP, DOWN.getOpposite());
        Assert.assertEquals(DOWN, UP.getOpposite());
        Assert.assertEquals(LEFT, RIGHT.getOpposite());
        Assert.assertEquals(RIGHT, LEFT.getOpposite());
    }

    @Test
    public void getPerpendicular() {

        final List<Directions> leftRight = new ArrayList<>();
        leftRight.add(LEFT);
        leftRight.add(RIGHT);

        final List<Directions> upDown = new ArrayList<>();
        upDown.add(UP);
        upDown.add(DOWN);

        Assert.assertEquals(UP.getPerpendicular(), leftRight);
        Assert.assertEquals(DOWN.getPerpendicular(), leftRight);
        Assert.assertEquals(LEFT.getPerpendicular(), upDown);
        Assert.assertEquals(RIGHT.getPerpendicular(), upDown);
    }

    @Test
    public void testOpposites() {
        Assert.assertEquals(Collections.emptyList(), Directions.getOpposites(Arrays.asList(UP, LEFT, RIGHT, DOWN)));
        Assert.assertEquals(Arrays.asList(UP, DOWN), Directions.getOpposites(Arrays.asList(LEFT, RIGHT)));
        Assert.assertEquals(Arrays.asList(LEFT, UP, DOWN), Directions.getOpposites(Collections.singletonList(RIGHT)));
        Assert.assertEquals(Collections.singletonList(UP), Directions.getOpposites(Arrays.asList(LEFT, RIGHT, DOWN)));
        Assert.assertEquals(Arrays.asList(LEFT, RIGHT, UP, DOWN), Directions.getOpposites(Collections.emptyList()));
    }
}