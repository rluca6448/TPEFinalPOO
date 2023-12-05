package backend.model;

import backend.RGBColor;

import java.util.HashSet;
import java.util.Set;

public class Rectangle<E extends Figure> extends FigureImpl<E> {

    private Point topLeft, bottomRight;

    public Rectangle(Point topLeft, Point bottomRight, RGBColor color) {
        super(color);
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    public Point getTopLeft() {
        return topLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }

    @Override
    public String toString() {
        return String.format("Rectángulo [ %s , %s ]", topLeft, bottomRight);
    }

    @Override
    public boolean figureBelongs(Point eventPoint) {
        return eventPoint.getX() > getTopLeft().getX() && eventPoint.getX() < getBottomRight().getX() &&
                eventPoint.getY() > getTopLeft().getY() && eventPoint.getY() < getBottomRight().getY();
    }

    @Override
    public boolean isWithin(Point topLeft, Point bottomRight) {
        return getTopLeft().getX() > topLeft.getX() && getTopLeft().getY() > topLeft.getY() &&
                getBottomRight().getX() < bottomRight.getX() && getBottomRight().getY() < bottomRight.getY();
    }

    @Override
    public void rotateFigure() {
        double centerX = (getTopLeft().getX() + getBottomRight().getX()) / 2;
        double centerY = (getTopLeft().getY() + getBottomRight().getY()) / 2;

        double halfWidth = Math.abs(getBottomRight().getY() - getTopLeft().getY()) / 2;
        double halfHeight = Math.abs(getBottomRight().getX() - getTopLeft().getX()) / 2;

        topLeft = new Point(centerX - halfWidth, centerY - halfHeight);
        bottomRight = new Point(centerX + halfWidth, centerY + halfHeight);

    }

    @Override
    public void flipHorizontally() {
        Point newTopLeft = new Point(getBottomRight().getX(), getTopLeft().getY());
        Point newBottomRight = new Point(2 * getBottomRight().getX() - getTopLeft().getX(), getBottomRight().getY());
        topLeft = newTopLeft;
        bottomRight = newBottomRight;
    }

    @Override
    public void flipVertically() {
        Point newTopLeft = new Point(getTopLeft().getX(), getBottomRight().getY());
        Point newBottomRight = new Point(getBottomRight().getX(), 2 * getBottomRight().getY() - getTopLeft().getY());
        topLeft = newTopLeft;
        bottomRight = newBottomRight;
    }

    @Override
    public void scaleFigure(double factor) {
        double centerX = (topLeft.getX() + bottomRight.getX()) / 2;
        double centerY = (topLeft.getY() + bottomRight.getY()) / 2;

        double halfWidth = (bottomRight.getX() - topLeft.getX()) / 2;
        double halfHeight = (bottomRight.getY() - topLeft.getY()) / 2;

        topLeft = new Point(centerX - halfWidth * factor, centerY - halfHeight * factor);
        bottomRight = new Point(centerX + halfWidth * factor, centerY + halfHeight * factor);
    }


    @Override
    public void moveFigure(double diffX, double diffY) {
        topLeft.movePoint(diffX, diffY);
        bottomRight.movePoint(diffX, diffY);
    }

}
