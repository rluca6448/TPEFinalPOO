package frontend.model;

import backend.model.Point;
import backend.model.Rectangle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

public class RectangleFront extends Rectangle implements RectangleFrontInterface {
    public RectangleFront(Point topLeft, Point bottomRight) {
        super(topLeft, bottomRight);
    }

}
