package backend.model;

import backend.RGBColor;

public class Circle extends Ellipse{

    public Circle(Point centerPoint, double radius, RGBColor color) {
        super(centerPoint, radius, radius, color);
    }

    @Override
    public String toString() {
        return String.format("Círculo [Centro: %s, Radio: %.2f]", centerPoint, getRadius());
    }

    public double getRadius() {
        return super.getsMayorAxis() / 2;
    }

}
