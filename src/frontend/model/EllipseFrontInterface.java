package frontend.model;

import backend.EffectState;
import backend.model.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.ArcType;

public interface EllipseFrontInterface extends FigureFront {
    Point getCenterPoint();
    double getsMayorAxis();
    double getsMinorAxis();

    //todo que la sombra salga atras
    default void applyShadow(GraphicsContext gc) {
        if (stateShadow() != EffectState.TRUE) return;
        gc.setFill(Color.GRAY);
        gc.fillOval(getCenterPoint().getX() - getsMayorAxis() / 2 + 10.0,
                getCenterPoint().getY() - getsMinorAxis() / 2 + 10.0, getsMayorAxis(), getsMinorAxis());
        gc.setFill(getColor());
        drawFigure(gc);
    }


    default void applyGradient(GraphicsContext gc) {
        if (stateGradient() != EffectState.TRUE) return;
        RadialGradient radialGradient = new RadialGradient(0, 0, 0.5, 0.5, 0.5, true,
                CycleMethod.NO_CYCLE,
                new Stop(0, getColor()),
                new Stop(1, getColor().invert()));
        gc.setFill(radialGradient);
        gc.fillOval(getCenterPoint().getX() - (getsMayorAxis() / 2), getCenterPoint().getY() - (getsMinorAxis() / 2), getsMayorAxis(), getsMinorAxis());
    }


    default void applyBevel(GraphicsContext gc) {
        if (stateBevel() != EffectState.TRUE) return;
        double arcX = getCenterPoint().getX() - getsMayorAxis();
        double arcY = getCenterPoint().getY() - getsMinorAxis();
        gc.setLineWidth(10);
        gc.setStroke(Color.LIGHTGRAY);
        gc.strokeArc(arcX, arcY, getsMayorAxis(), getsMinorAxis(), 45, 180, ArcType.OPEN);
        gc.setStroke(Color.BLACK);
        gc.strokeArc(arcX, arcY, getsMayorAxis(), getsMinorAxis(), 225, 180, ArcType.OPEN);
    }


    default void drawFigure(GraphicsContext gc) {
        gc.setFill(getColor());
        gc.fillOval(getCenterPoint().getX() - (getsMayorAxis() / 2), getCenterPoint().getY() - (getsMinorAxis() / 2), getsMayorAxis(), getsMinorAxis());
        gc.strokeOval(getCenterPoint().getX() - (getsMayorAxis() / 2), getCenterPoint().getY() - (getsMinorAxis() / 2), getsMayorAxis(), getsMinorAxis());
    }
}
