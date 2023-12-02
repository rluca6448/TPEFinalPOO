package src.backend.model;

import javafx.scene.canvas.GraphicsContext;

public interface Figure {

    boolean figureBelongs(Point eventPoint);

    void drawFigure(GraphicsContext gc);

    void moveFigure(double diffX, double diffY);

    boolean isWithin(Point topLeft, Point bottomRight);
}