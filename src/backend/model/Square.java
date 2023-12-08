package backend.model;

import backend.RGBColor;

public class Square extends Rectangle {

    public Square(Point topLeft, double size, RGBColor color) {
        super(topLeft, new Point(topLeft.getX() + size, topLeft.getY() + size), color);
    }

    @Override
    public String toString() {
        return String.format("Cuadrado [ %s , %s ]", getTopLeft(), getBottomRight());
    }

}