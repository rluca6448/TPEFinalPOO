package frontend.model;

import backend.EffectState;
import backend.model.Point;
import frontend.FrontColor;
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

    private void fill(GraphicsContext gc, double offset){
        gc.fillOval(getCenterPoint().getX() - getsMayorAxis() / 2 + offset,
                getCenterPoint().getY() - getsMinorAxis() / 2 + offset, getsMayorAxis(), getsMinorAxis());
    }

    private void applyShadow(GraphicsContext gc) {
        if (stateShadow() != EffectState.TRUE) return;
        gc.setFill(Color.GRAY);
        fill(gc, 10.0);
        gc.setFill(FrontColor.RGBtoColor(getColor()));
    }
    
    private void applyGradient(GraphicsContext gc) {
        RadialGradient radialGradient = new RadialGradient(0, 0, 0.5, 0.5, 0.5, true,
                CycleMethod.NO_CYCLE,
                new Stop(0, FrontColor.RGBtoColor(getColor())),
                new Stop(1, FrontColor.RGBtoColor(getColor()).invert()));
        gc.setFill(radialGradient);
        fill(gc, 0);
    }


    private void applyBevel(GraphicsContext gc) {
        double arcX = getCenterPoint().getX() - getsMayorAxis()/2;
        double arcY = getCenterPoint().getY() - getsMinorAxis()/2;
        gc.setLineWidth(10);
        gc.setStroke(Color.LIGHTGRAY);
        gc.strokeArc(arcX, arcY, getsMayorAxis(), getsMinorAxis(), 45, 180, ArcType.OPEN);
        gc.setStroke(Color.BLACK);
        gc.strokeArc(arcX, arcY, getsMayorAxis(), getsMinorAxis(), 225, 180, ArcType.OPEN);
        gc.setLineWidth(1);
    }


    default void drawFigure(GraphicsContext gc) {
        applyShadow(gc);

        gc.setFill(FrontColor.RGBtoColor(getColor()));
        if (stateGradient() == EffectState.TRUE) {
            applyGradient(gc);
        } else {
            fill(gc, 0);
        }

        if (stateBevel() == EffectState.TRUE) {
            applyBevel(gc);
        } else {
            gc.strokeOval(getCenterPoint().getX() - (getsMayorAxis() / 2), getCenterPoint().getY() - (getsMinorAxis() / 2), getsMayorAxis(), getsMinorAxis());
        }

    }
}
