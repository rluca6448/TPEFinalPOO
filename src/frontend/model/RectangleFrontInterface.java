package frontend.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import backend.model.*;

public interface RectangleFrontInterface extends FigureFront{
    Point getTopLeft();
    Point getBottomRight();

    default void applyShadow(GraphicsContext gc) {
        if (!hasShadow()) return;
        gc.setFill(Color.GRAY);
        gc.fillRect(getTopLeft().getX() + 10.0,
                getTopLeft().getY() + 10.0,
                Math.abs(getTopLeft().getX() - getBottomRight().getX()),
                Math.abs(getTopLeft().getY() - getBottomRight().getY()));
        gc.setFill(getColor());
        drawFigure(gc);
    }


    default void applyGradient(GraphicsContext gc) {
        if (!hasGradient()) return;
        LinearGradient linearGradient = new LinearGradient(0, 0, 1, 0, true,
                CycleMethod.NO_CYCLE,
                new Stop(0, getColor()),
                new Stop(1, getColor().invert()));
        gc.setFill(linearGradient);
    }


    default void applyBevel(GraphicsContext gc) {
        if (!hasBevel()) return;
        double x = getTopLeft().getX();
        double y = getTopLeft().getY();
        double width = Math.abs(x - getBottomRight().getX());
        double height = Math.abs(y - getBottomRight().getY());
        gc.setLineWidth(10);
        gc.setStroke(Color.LIGHTGRAY);
        gc.strokeLine(x, y, x + width, y);
        gc.strokeLine(x, y, x, y + height);
        gc.setStroke(Color.BLACK);
        gc.strokeLine(x + width, y, x + width, y + height);
        gc.strokeLine(x, y + height, x + width, y + height);
    }

    default void drawFigure(GraphicsContext gc) {
        gc.setFill(getColor());
        gc.fillRect(getTopLeft().getX(), getTopLeft().getY(),
                Math.abs(getTopLeft().getX() - getBottomRight().getX()), Math.abs(getTopLeft().getY() - getBottomRight().getY()));
        gc.strokeRect(getTopLeft().getX(), getTopLeft().getY(),
                Math.abs(getTopLeft().getX() - getBottomRight().getX()), Math.abs(getTopLeft().getY() - getBottomRight().getY()));
    }
}
