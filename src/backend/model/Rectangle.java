package src.backend.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

public class Rectangle implements Figure {

    private final Point topLeft, bottomRight;

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
    public Figure rotateFigure() {
        double centerX = (getTopLeft().getX() + getBottomRight().getX()) / 2;
        double centerY = (getTopLeft().getY() + getBottomRight().getY()) / 2;

        double halfWidth = Math.abs(getBottomRight().getY() - getTopLeft().getY()) / 2;
        double halfHeight = Math.abs(getBottomRight().getX() - getTopLeft().getX()) / 2;

        Point newTopLeft = new Point(centerX - halfWidth, centerY - halfHeight);
        Point newBottomRight = new Point(centerX + halfWidth, centerY + halfHeight);

        return new Rectangle(newTopLeft, newBottomRight);
    }

    @Override
    public Figure flipHorizontally() {
        Point newTopLeft = new Point(getBottomRight().getX(), getTopLeft().getY());
        Point newBottomRight = new Point(2 * getBottomRight().getX() - getTopLeft().getX(), getBottomRight().getY());

        return new Rectangle(newTopLeft, newBottomRight);
    }

    @Override
    public Figure flipVertically() {
        Point newTopLeft = new Point(getTopLeft().getX(), getBottomRight().getY());
        Point newBottomRight = new Point(getBottomRight().getX(), 2 * getBottomRight().getY() - getTopLeft().getY());

        return new Rectangle(newTopLeft, newBottomRight);
    }

    @Override
    public Figure scaleFigure(double factor) {
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

        Point newTopLeft = new Point(newTopLeftX, newTopLeftY); // creo los nuevos puntos del nuevo rectangulo
        Point newBottomRight = new Point(newBottomRightX, newBottomRightY);

        return new Rectangle(newTopLeft, newBottomRight);
    }

    @Override
    public void applyShadow(GraphicsContext gc) {
        gc.setFill(Color.GRAY);
        gc.fillRect(getTopLeft().getX() + 10.0,
                getTopLeft().getY() + 10.0,
                Math.abs(getTopLeft().getX() - getBottomRight().getX()),
                Math.abs(getTopLeft().getY() - getBottomRight().getY()));
    }

    @Override
    public void applyGradient(GraphicsContext gc, Color fillColor) {
        LinearGradient linearGradient = new LinearGradient(0, 0, 1, 0, true,
                CycleMethod.NO_CYCLE,
                new Stop(0, fillColor),
                new Stop(1, fillColor.invert()));
        gc.setFill(linearGradient);
    }

    @Override
    public void applyBevel(GraphicsContext gc) {
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

    @Override
    public void drawFigure(GraphicsContext gc) {
        gc.fillRect(getTopLeft().getX(), getTopLeft().getY(),
                Math.abs(getTopLeft().getX() - getBottomRight().getX()), Math.abs(getTopLeft().getY() - getBottomRight().getY()));
        gc.strokeRect(getTopLeft().getX(), getTopLeft().getY(),
                Math.abs(getTopLeft().getX() - getBottomRight().getX()), Math.abs(getTopLeft().getY() - getBottomRight().getY()));
    }

    @Override
    public void moveFigure(double diffX, double diffY) {
        topLeft.movePoint(diffX, diffY);
        bottomRight.movePoint(diffX, diffY);
    }
}
