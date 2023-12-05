package frontend.model;

import backend.RGBColor;
import backend.model.Ellipse;
import backend.model.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.ArcType;

public class EllipseFront extends Ellipse implements EllipseFrontInterface {
    public EllipseFront(Point centerPoint, double sMayorAxis, double sMinorAxis, backend.RGBColor color) {
        super(centerPoint, sMayorAxis, sMinorAxis, color);
    }

}
