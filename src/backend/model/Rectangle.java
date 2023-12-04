package backend.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

public class Rectangle extends FigureImpl {

    private Point topLeft, bottomRight;

    public Rectangle(Point topLeft, Point bottomRight) {
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
        //todo simplficar cuenta
        double centerX = (topLeft.getX() + bottomRight.getX()) / 2; // calculo las coordenadas del centro
        double centerY = (topLeft.getY() + bottomRight.getY()) / 2;

        double width = bottomRight.getX() - topLeft.getX(); // calculo el ancho y el alto
        double height = bottomRight.getY() - topLeft.getY();

        double newWidth = width * factor; // calculo los nuevos ancho y alto
        double newHeight = height * factor;

        double newTopLeftX = centerX - newWidth / 2; // calcula las nuevas coordenadas
        double newTopLeftY = centerY - newHeight / 2;
        double newBottomRightX = centerX + newWidth / 2;
        double newBottomRightY = centerY + newHeight / 2;

        topLeft = new Point(newTopLeftX, newTopLeftY); // creo los nuevos puntos del nuevo rectangulo
        bottomRight = new Point(newBottomRightX, newBottomRightY);
    }



    @Override
    public void moveFigure(double diffX, double diffY) {
        topLeft.movePoint(diffX, diffY);
        bottomRight.movePoint(diffX, diffY);
    }

}
