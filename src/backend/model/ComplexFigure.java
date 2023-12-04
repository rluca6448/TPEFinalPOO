package backend.model;

import backend.EffectState;
import frontend.model.FigureFront;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.HashSet;
import java.util.Set;

public class ComplexFigure<E extends Figure> extends HashSet<E> implements Figure {
    //todo que extienda a hashset

    public ComplexFigure(Set<E> selectedFigures) {
        super();
        addAll(selectedFigures);
    }

    public ComplexFigure(){
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

    public void addShadow(){
        for (E figure : this) {
            figure.addShadow();
        }
    }
    //todo modularizar en un getState privado que reciba la funcion lambda de cual state verificar
    public EffectState stateShadow(){
        boolean oneShadowed = false;
        boolean oneUnShadowed = false;
        for (E figure : this) {
            if (figure.stateShadow() == EffectState.TRUE){
                oneShadowed = true;
            } else {
                oneUnShadowed = true;
            }
            if (oneShadowed && oneUnShadowed){
                //todo
                System.out.println("undetermined");
                return EffectState.UNDETERMINED;
            }
        }
        //todo
        System.out.println(oneShadowed ? "true" : "false");
        return oneShadowed ? EffectState.TRUE : EffectState.FALSE;
    }
    //todo: acordarse del caso donde el checkbox es ideterminado

    public void deleteShadow(){
        for (E figure : this) {
            figure.deleteShadow();
        }
    }
    public void addGradient(){
        for (E figure : this) {
            figure.addGradient();
        }
    }
    //todo modularizar en un getState privado que reciba la funcion lambda de cual state verificar
    public EffectState stateGradient(){
        boolean oneGradiented = false;
        boolean oneUnGradiented = false;
        for (E figure : this) {
            if (figure.stateGradient() == EffectState.TRUE){
                oneGradiented = true;
            } else {
                oneUnGradiented = true;
            }
            if (oneGradiented && oneUnGradiented){
                return EffectState.UNDETERMINED;
            }
        }
        return oneGradiented ? EffectState.TRUE : EffectState.FALSE;
    }
    public void deleteGradient(){
        for (E figure : this) {
            figure.deleteGradient();
        }
    }
    public void addBevel(){
        for (E figure : this) {
            figure.addBevel();
        }
    }
    //todo modularizar en un getState privado que reciba la funcion lambda de cual state verificar
    public EffectState stateBevel(){
        boolean oneBeveled = false;
        boolean oneUnBeveled = false;
        for (E figure : this) {
            if (figure.stateBevel() == EffectState.TRUE){
                oneBeveled = true;
            } else {
                oneUnBeveled = true;
            }
            if (oneBeveled && oneUnBeveled){
                return EffectState.UNDETERMINED;
            }
        }
        return oneBeveled ? EffectState.TRUE : EffectState.FALSE;
    }
    public void deleteBevel(){
        for (E figure : this) {
            figure.deleteBevel();
        }
    }
    public void setFillColor(Color color) {
        for (E figure : this) {
            figure.setFillColor(color);
        }
    }
    public Color getColor() {
        return Color.YELLOW;
    }

//    public boolean contains(E figure) {
//        return figures.contains(figure);
//    }
}
