package frontend.model;

import backend.model.Circle;
import backend.model.Point;

public class CircleFront extends Circle implements EllipseFrontInterface {
    public CircleFront(Point centerPoint, double radius) {
        super(centerPoint, radius);
    }
}
