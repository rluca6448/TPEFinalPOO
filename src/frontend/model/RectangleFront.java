package frontend.model;

import backend.RGBColor;
import backend.model.Point;
import backend.model.Rectangle;

public class RectangleFront extends Rectangle implements RectangleFrontInterface {
    public RectangleFront(Point topLeft, Point bottomRight, RGBColor color) {
        super(topLeft, bottomRight, color);
    }

}
