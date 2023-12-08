package backend.model;

import backend.EffectState;
import backend.RGBColor;

import java.util.Set;

public interface Figure {
//    boolean isComplex();
//    Set<Figure> getFigures2();
//    List<E> getFigures();
    <T extends Figure> List<T>  getFigures();

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