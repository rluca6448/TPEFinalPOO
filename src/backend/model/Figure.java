package src.backend.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public interface Figure {

    boolean figureBelongs(Point eventPoint);

    void drawFigure(GraphicsContext gc);

    void moveFigure(double diffX, double diffY);

    boolean isWithin(Point topLeft, Point bottomRight);

    Figure rotateFigure();

    Figure flipHorizontally();

    Figure flipVertically();

    Figure scaleFigure(double factor);

    void applyShadow(GraphicsContext gc);

    void applyGradient(GraphicsContext gc, Color fillColor);

    void applyBevel(GraphicsContext gc);
}