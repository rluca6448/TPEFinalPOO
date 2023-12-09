package backend.model;

import backend.EffectState;
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

    EffectState stateShadow();

    void deleteShadow();

    void addGradient();

    EffectState stateGradient();

    void deleteGradient();

    void addBevel();

    EffectState stateBevel();

    void deleteBevel();
}