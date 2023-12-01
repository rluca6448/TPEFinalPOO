package src.backend.model;

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
    public boolean figureBelongs(Point eventPoint) {
        double xDiff = eventPoint.getX() - getCenterPoint().getX();
        double yDiff = eventPoint.getY() - getCenterPoint().getY();

        double normalizedX = Math.pow(xDiff / getsMayorAxis(), 2);
        double normalizedY = Math.pow(yDiff / getsMinorAxis(), 2);

        return normalizedX + normalizedY <= ELLIPSE_PRECISION_THRESHOLD;
    }
}
