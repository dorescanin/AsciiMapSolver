package hr.dorescanin;

import hr.dorescanin.util.CoordinatePair;

public class AsciiMapTraversal {

    private AsciiMap map;
    private CoordinatePair initialPosition, finalPosition;

    public AsciiMapTraversal(AsciiMap map) {
        this.map = map;

        final AsciiMapValidator validator = new AsciiMapValidator(map);
        this.initialPosition = validator.validateInitialPosition();
        this.finalPosition = validator.validateFinalPosition();
    }

    public AsciiMap getMap() {
        return map;
    }
}
