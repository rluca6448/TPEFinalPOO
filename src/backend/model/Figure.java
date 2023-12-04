package backend.model;

import javafx.scene.paint.Color;

public interface Figure {

    boolean figureBelongs(Point eventPoint);


    void moveFigure(double diffX, double diffY);

    boolean isWithin(Point topLeft, Point bottomRight);

    void rotateFigure();

    void flipHorizontally();

    void flipVertically();

    void scaleFigure(double factor);

    //todo que en lugar de usar el color de javafx guarde el hexa del color y lo vaya convirtiendo el front a color de javafx
    //todo que el color se pase como param del constructor
    void setFillColor(Color color);
    Color getColor();

    void addShadow();
    boolean hasShadow();
    void deleteShadow();

    void addGradient();
    boolean hasGradient();
    void deleteGradient();

    void addBevel();
    boolean hasBevel();
    void deleteBevel();
}