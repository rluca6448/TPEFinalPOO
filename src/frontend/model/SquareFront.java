package frontend.model;

import backend.RGBColor;
import backend.model.Point;
import backend.model.Square;

public class SquareFront extends Square implements RectangleFrontInterface {
    public SquareFront(Point topLeft, double size, RGBColor color) {
        super(topLeft, size, color);
        setFigure(this);
    }
}
