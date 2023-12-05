package frontend.model;

import backend.EffectState;
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

    private void applyShadow(GraphicsContext gc) {
        if (stateShadow() != EffectState.TRUE) return;
        gc.setFill(Color.GRAY);
        gc.fillRect(getTopLeft().getX() + 10.0,
                getTopLeft().getY() + 10.0,
                Math.abs(getTopLeft().getX() - getBottomRight().getX()),
                Math.abs(getTopLeft().getY() - getBottomRight().getY()));
        gc.setFill(FrontColor.RGBtoColor(getColor()));
    }


    private void applyGradient(GraphicsContext gc) {
        if (stateGradient() != EffectState.TRUE) return;
        LinearGradient linearGradient = new LinearGradient(0, 0, 1, 0, true,
                CycleMethod.NO_CYCLE,
                new Stop(0, FrontColor.RGBtoColor(getColor())),
                new Stop(1, FrontColor.RGBtoColor(getColor()).invert()));
        gc.setFill(linearGradient);
        gc.fillRect(getTopLeft().getX(), getTopLeft().getY(),
                Math.abs(getTopLeft().getX() - getBottomRight().getX()), Math.abs(getTopLeft().getY() - getBottomRight().getY()));
    }


    private void applyBevel(GraphicsContext gc) {
        if (stateBevel() != EffectState.TRUE) return;
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

        gc.setFill(FrontColor.RGBtoColor(getColor()));
        gc.fillRect(getTopLeft().getX(), getTopLeft().getY(),
                Math.abs(getTopLeft().getX() - getBottomRight().getX()), Math.abs(getTopLeft().getY() - getBottomRight().getY()));

        applyGradient(gc);

        gc.strokeRect(getTopLeft().getX(), getTopLeft().getY(),
                Math.abs(getTopLeft().getX() - getBottomRight().getX()), Math.abs(getTopLeft().getY() - getBottomRight().getY()));

        applyBevel(gc);
    }
}
