package backend.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.ArcType;

public class Ellipse implements Figure {

    protected final Point centerPoint;
    protected final double sMayorAxis, sMinorAxis;

    public Ellipse(Point centerPoint, double sMayorAxis, double sMinorAxis) {
        this.centerPoint = centerPoint;
        this.sMayorAxis = sMayorAxis;
        this.sMinorAxis = sMinorAxis;
    }

    @Override
    public String toString() {
        return String.format("Elipse [Centro: %s, DMayor: %.2f, DMenor: %.2f]", centerPoint, sMayorAxis, sMinorAxis);
    }

    public Point getCenterPoint() {
        return centerPoint;
    }

    public double getsMayorAxis() {
        return sMayorAxis;
    }

    public double getsMinorAxis() {
        return sMinorAxis;
    }

    private static final double ELLIPSE_PRECISION_THRESHOLD = 0.25;

    @Override
    public boolean figureBelongs(Point eventPoint) { // se modifico para que sea mas claro como se calcula si un punto pertenece
        double xDiff = eventPoint.getX() - getCenterPoint().getX(); // (x - x0)^2
        double yDiff = eventPoint.getY() - getCenterPoint().getY(); // (y - y0)^2

        double normalizedX = Math.pow(xDiff / getsMayorAxis(), 2);
        double normalizedY = Math.pow(yDiff / getsMinorAxis(), 2);

        return normalizedX + normalizedY <= ELLIPSE_PRECISION_THRESHOLD;
    }

    @Override
    public boolean isWithin(Point topLeft, Point bottomRight) {
        return getCenterPoint().getX() - getsMayorAxis() / 2 >= topLeft.getX() &&
                getCenterPoint().getX() - getsMayorAxis() / 2 <= bottomRight.getX() &&
                getCenterPoint().getX() + getsMayorAxis() / 2 <= bottomRight.getX() &&
                getCenterPoint().getX() + getsMayorAxis() / 2 >= topLeft.getX() &&
                getCenterPoint().getY() + getsMinorAxis() / 2 >= topLeft.getY() &&
                getCenterPoint().getY() + getsMinorAxis() / 2 <= bottomRight.getY() &&
                getCenterPoint().getY() - getsMinorAxis() / 2 <= bottomRight.getY() &&
                getCenterPoint().getY() - getsMinorAxis() / 2 >= topLeft.getY();
    }

    @Override
    public Figure rotateFigure() {
        return new Ellipse(getCenterPoint(), getsMinorAxis(), getsMayorAxis());
    }

    @Override
    public Figure flipHorizontally() {
        Point newCenter = new Point(getCenterPoint().getX() + getsMayorAxis(), getCenterPoint().getY());
        return new Ellipse(newCenter, getsMayorAxis(), getsMinorAxis());
    }

    @Override
    public Figure flipVertically() {
        Point newCenter = new Point(getCenterPoint().getX(), getCenterPoint().getY() + getsMinorAxis());
        return new Ellipse(newCenter, getsMayorAxis(), getsMinorAxis());
    }

    @Override
    public Figure scaleFigure(double factor) {
        return new Ellipse(getCenterPoint(), getsMayorAxis() * factor, getsMinorAxis() * factor);
    }

    @Override
    public void applyShadow(GraphicsContext gc) {
        gc.setFill(Color.GRAY);
        gc.fillOval(getCenterPoint().getX() - getsMayorAxis() / 2 + 10.0,
                getCenterPoint().getY() - getsMinorAxis() / 2 + 10.0, getsMayorAxis(), getsMinorAxis());
    }

    @Override
    public void applyGradient(GraphicsContext gc, Color fillColor) {
        RadialGradient radialGradient = new RadialGradient(0, 0, 0.5, 0.5, 0.5, true,
                CycleMethod.NO_CYCLE,
                new Stop(0, fillColor),
                new Stop(1, fillColor.invert()));
        gc.setFill(radialGradient);
    }

    @Override
    public void applyBevel(GraphicsContext gc) {
        double arcX = getCenterPoint().getX() - getsMayorAxis();
        double arcY = getCenterPoint().getY() - getsMinorAxis();
        gc.setLineWidth(10);
        gc.setStroke(Color.LIGHTGRAY);
        gc.strokeArc(arcX, arcY, getsMayorAxis(), getsMinorAxis(), 45, 180, ArcType.OPEN);
        gc.setStroke(Color.BLACK);
        gc.strokeArc(arcX, arcY, getsMayorAxis(), getsMinorAxis(), 225, 180, ArcType.OPEN);
    }

    @Override
    public void drawFigure(GraphicsContext gc) {
        gc.fillOval(getCenterPoint().getX() - (getsMayorAxis() / 2), getCenterPoint().getY() - (getsMinorAxis() / 2), getsMayorAxis(), getsMinorAxis());
        gc.strokeOval(getCenterPoint().getX() - (getsMayorAxis() / 2), getCenterPoint().getY() - (getsMinorAxis() / 2), getsMayorAxis(), getsMinorAxis());
    }

    @Override
    public void moveFigure(double diffX, double diffY) {
        centerPoint.movePoint(diffX, diffY);
    }
}
