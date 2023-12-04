package frontend.model;

import backend.model.Point;
import backend.model.Square;

public class SquareFront extends Square implements RectangleFrontInterface {
    public SquareFront(Point topLeft, double size) {
        super(topLeft, size);
    }
}
