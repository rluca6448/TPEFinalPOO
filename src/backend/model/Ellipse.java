package src.backend.model;

import javafx.scene.canvas.GraphicsContext;

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
    public void drawFigure(GraphicsContext gc) {
        gc.fillOval(getCenterPoint().getX() - (getsMayorAxis() / 2), getCenterPoint().getY() - (getsMinorAxis() / 2), getsMayorAxis(), getsMinorAxis());
        gc.strokeOval(getCenterPoint().getX() - (getsMayorAxis() / 2), getCenterPoint().getY() - (getsMinorAxis() / 2), getsMayorAxis(), getsMinorAxis());
    }

    @Override
    public void moveFigure(double diffX, double diffY) {
        centerPoint.movePoint(diffX, diffY);
    }
}
