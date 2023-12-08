package frontend.model;

import backend.RGBColor;
import backend.model.Circle;
import backend.model.Point;

public class CircleFront extends Circle implements EllipseFrontInterface {
    public CircleFront(Point centerPoint, double radius, RGBColor color) {
        super(centerPoint, radius, color);
        setFigure(this);
    }
}
