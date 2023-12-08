package backend.model;

import backend.EffectState;
import backend.RGBColor;

import java.util.*;
import java.util.function.Function;

public class ComplexFigure<E extends Figure> extends ArrayList<E> implements Figure {

    public ComplexFigure(Collection<E> selectedFigures) {
        super();
        addAll(selectedFigures);
    }

    public ComplexFigure() {
        super();
    }

//    public List<E> getFigures() {
//        return List.copyOf(this);
//    }

    public <T extends Figure> List<T> getFigures(){
        List<T> toReturn = new ArrayList<>();
        for (Figure figure : this){
            toReturn.addAll(figure.getFigures());
        }
        return toReturn;
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

    public RGBColor getColor() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof ComplexFigure<?> aux) {

            Iterator<E> it1 = this.iterator();
            Iterator<?> it2 = aux.iterator();

            while (it1.hasNext() && it2.hasNext()) {
                E figure1 = it1.next();
                Object figure2 = it2.next();

                if (figure1 != null && !figure1.equals(figure2)) {
                    return false;
                } else if (figure1 == null && figure2 != null) {
                    return false;
                }
            }

            return !it1.hasNext() && !it2.hasNext();
        }
        return false;
    }

    //la función getColor nunca se usa; solo está implementada porque lo pide la interface

//    public boolean contains(E figure) {
//        return figures.contains(figure);
//    }
}
