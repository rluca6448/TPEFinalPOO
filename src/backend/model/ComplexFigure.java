package backend.model;

import backend.EffectState;
import backend.RGBColor;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

public class ComplexFigure<E extends Figure> extends HashSet<E> implements Figure {

    public ComplexFigure(Set<E> selectedFigures) {
        super();
        addAll(selectedFigures);
    }

    public ComplexFigure() {
        super();
    }

    public Set<E> getFigures() {
        return Set.copyOf(this);
    }

//    public boolean isEmpty(){return figures.isEmpty();}
//    public void add(E figure){
//        figures.add(figure);
//    }

    @Override
    public boolean figureBelongs(Point eventPoint) {
        for (E figure : this) {
            if (figure.figureBelongs(eventPoint)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void moveFigure(double diffX, double diffY) {
        for (E figure : this) {
            figure.moveFigure(diffX, diffY);
        }
    }

    @Override
    public boolean isWithin(Point topLeft, Point bottomRight) {
        for (E figure : this) {
            if (!figure.isWithin(topLeft, bottomRight)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void rotateFigure() {
        for (E figure : this) {
            figure.rotateFigure();
        }
    }

    @Override
    public void flipHorizontally() {
        for (E figure : this) {
            figure.flipHorizontally();
        }
    }

    @Override
    public void flipVertically() {
        for (E figure : this) {
            figure.flipVertically();
        }
    }

    @Override
    public void scaleFigure(double factor) {
        for (E figure : this) {
            figure.scaleFigure(factor);
        }
    }


    @Override
    public String toString() {
        StringBuilder toReturn = new StringBuilder();
        for (E figure : this) {
            toReturn.append(figure.toString()).append(", ");
        }
        return toReturn.toString();
    }

    public void addShadow() {
        for (E figure : this) {
            figure.addShadow();
        }
    }

    private EffectState getState(Function<E, EffectState> stateFunction) {
        boolean oneTrue = false;
        boolean oneFalse = false;
        boolean oneUndetermined = false;
        for (E figure : this) {
            switch (stateFunction.apply(figure)) {
                case TRUE -> oneTrue = true;
                case FALSE -> oneFalse = true;
                case UNDETERMINED -> oneUndetermined = true;
            }
            if ((oneTrue && oneFalse) || oneUndetermined) {
                return EffectState.UNDETERMINED;
            }
        }
        return oneTrue ? EffectState.TRUE : EffectState.FALSE;
    }
    public EffectState stateShadow() {

        return getState(Figure::stateShadow);
    }

    public void deleteShadow() {
        for (E figure : this) {
            figure.deleteShadow();
        }
    }

    public void addGradient() {
        for (E figure : this) {
            figure.addGradient();
        }
    }

    //todo modularizar en un getState privado que reciba la funcion lambda de cual state verificar
    public EffectState stateGradient() {
        return getState(Figure::stateGradient);
    }

    public void deleteGradient() {
        for (E figure : this) {
            figure.deleteGradient();
        }
    }

    public void addBevel() {
        for (E figure : this) {
            figure.addBevel();
        }
    }

    public EffectState stateBevel() {
        return getState(Figure::stateBevel);
    }

    public void deleteBevel() {
        for (E figure : this) {
            figure.deleteBevel();
        }
    }

    public void setFillColor(RGBColor RGBColor) {
        for (E figure : this) {
            figure.setFillColor(RGBColor);
        }
    }

    public RGBColor getColor() {
        return null;
    }
    //la función getColor nunca se usa; solo está implementada porque lo pide la interface

//    public boolean contains(E figure) {
//        return figures.contains(figure);
//    }
}
