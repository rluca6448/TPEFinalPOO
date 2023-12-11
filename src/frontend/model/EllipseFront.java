package frontend.model;


import backend.model.Ellipse;

import backend.model.Point;

public class EllipseFront extends Ellipse implements EllipseFrontInterface {
    public EllipseFront(Point centerPoint, double sMayorAxis, double sMinorAxis, backend.RGBColor color) {
        super(centerPoint, sMayorAxis, sMinorAxis, color);
    }

}
