package frontend.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import frontend.FrontColor;
import backend.model.*;

public interface RectangleFrontInterface extends FigureFront{
    Point getTopLeft();
    Point getBottomRight();
    private void fill(GraphicsContext gc, double offset){
        gc.fillRect(getTopLeft().getX() + offset,
                getTopLeft().getY() + offset,
                Math.abs(getTopLeft().getX() - getBottomRight().getX()),
                Math.abs(getTopLeft().getY() - getBottomRight().getY()));
    }

    private void applyShadow(GraphicsContext gc) {
        if (!hasShadow()) return;
        gc.setFill(Color.GRAY);
        fill(gc, 10);
        gc.setFill(FrontColor.RGBtoColor(getColor()));
    }


    private void applyGradient(GraphicsContext gc) {
        LinearGradient linearGradient = new LinearGradient(0, 0, 1, 0, true,
                CycleMethod.NO_CYCLE,
                new Stop(0, FrontColor.RGBtoColor(getColor())),
                new Stop(1, FrontColor.RGBtoColor(getColor()).invert()));
        gc.setFill(linearGradient);
        fill(gc, 0);
    }


    private void applyBevel(GraphicsContext gc) {
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
        gc.setLineWidth(1);
    }

    default void drawFigure(GraphicsContext gc) {
        applyShadow(gc);

        if (hasGradient()) {
            applyGradient(gc);
        } else {
            gc.setFill(FrontColor.RGBtoColor(getColor()));
            fill(gc, 0);
        }

        if (hasBevel()) {
            applyBevel(gc);
        } else {
        gc.strokeRect(getTopLeft().getX(), getTopLeft().getY(),
                Math.abs(getTopLeft().getX() - getBottomRight().getX()), Math.abs(getTopLeft().getY() - getBottomRight().getY()));
        }
    }
}
