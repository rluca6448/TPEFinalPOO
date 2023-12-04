package backend.model;

import backend.EffectState;
import backend.RGBColor;

import java.util.HashSet;
import java.util.Set;

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

    //todo modularizar en un getState privado que reciba la funcion lambda de cual state verificar
    public EffectState stateShadow() {
        boolean oneShadowed = false;
        boolean oneUnShadowed = false;
        boolean oneUndetermined = false;
        for (E figure : this) {
            switch (figure.stateShadow()) {
                case TRUE -> oneShadowed = true;
                case FALSE -> oneUnShadowed = true;
                case UNDETERMINED -> oneUndetermined = true;
            }
            if ((oneShadowed && oneUnShadowed) || oneUndetermined) {
                //todo
                System.out.println("undetermined");
                return EffectState.UNDETERMINED;
            }
        }
        //todo
        System.out.println(oneShadowed ? "true" : "false");
        return oneShadowed ? EffectState.TRUE : EffectState.FALSE;
    }
    //todo: acordarse del caso donde el checkbox es indeterminado

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
        boolean oneGradiented = false;
        boolean oneUnGradiented = false;
        boolean oneUndetermined = false;
        for (E figure : this) {
            switch (figure.stateGradient()) {
                case TRUE -> oneGradiented = true;
                case FALSE -> oneUnGradiented = true;
                case UNDETERMINED -> oneUndetermined = true;
            }
            if ((oneGradiented && oneUnGradiented) || oneUndetermined) {
                //todo
                System.out.println("undetermined");
                return EffectState.UNDETERMINED;
            }
        }
        return oneGradiented ? EffectState.TRUE : EffectState.FALSE;
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

    //todo modularizar en un getState privado que reciba la funcion lambda de cual state verificar
    public EffectState stateBevel() {
        boolean oneBeveled = false;
        boolean oneUnBeveled = false;
        boolean oneUndetermined = false;
        for (E figure : this) {
            switch (figure.stateBevel()) {
                case TRUE -> oneBeveled = true;
                case FALSE -> oneUnBeveled = true;
                case UNDETERMINED -> oneUndetermined = true;
            }
            if ((oneBeveled && oneUnBeveled) || oneUndetermined) {
                //todo
                System.out.println("undetermined");
                return EffectState.UNDETERMINED;
            }
        }
        return oneBeveled ? EffectState.TRUE : EffectState.FALSE;
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
