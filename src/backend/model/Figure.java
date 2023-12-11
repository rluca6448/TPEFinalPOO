package backend.model;

import backend.RGBColor;

public interface Figure {

    boolean figureBelongs(Point eventPoint);

    void moveFigure(double diffX, double diffY);

    boolean isWithin(Point topLeft, Point bottomRight);

    void rotateFigure();

    void flipHorizontally();

    void flipVertically();

    void scaleFigure(double factor);

    RGBColor getColor();

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