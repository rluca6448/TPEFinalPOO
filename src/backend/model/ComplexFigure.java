package backend.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.HashSet;
import java.util.Set;

public class ComplexFigure<E extends Figure> implements Figure {

    private final Set<E> figures = new HashSet<>();

    public ComplexFigure(Set<E> selectedFigures) {
        figures.addAll(selectedFigures);
    }

    public Set<E> getFigures() {
        return new HashSet<>(figures);
    }

    @Override
    public boolean figureBelongs(Point eventPoint) {
        for (E figure : figures) {
            if (figure.figureBelongs(eventPoint)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void moveFigure(double diffX, double diffY) {
        for (E figure : figures) {
            figure.moveFigure(diffX, diffY);
        }
    }

    @Override
    public boolean isWithin(Point topLeft, Point bottomRight) {
        for (E figure : figures) {
            if (!figure.isWithin(topLeft, bottomRight)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void rotateFigure() {
        for (E figure : figures) {
            figure.rotateFigure();
        }
    }

    @Override
    public void flipHorizontally() {
        for (E figure : figures) {
            figure.flipHorizontally();
        }
    }

    @Override
    public void flipVertically() {
        for (E figure : figures) {
            figure.flipVertically();
        }
    }

    @Override
    public void scaleFigure(double factor) {
        for (E figure : figures) {
            figure.scaleFigure(factor);
        }
    }



    @Override
    public String toString() {
        StringBuilder toReturn = new StringBuilder();
        for (E figure : figures) {
            toReturn.append(figure.toString()).append(", ");
        }
        return toReturn.toString();
    }

    public void addShadow(){
        for (E figure : figures) {
            figure.addShadow();
        }
    }
    public boolean hasShadow(){
        for (E figure : figures) {
            if (!figure.hasShadow()){
                return false;
            }
        }
        return true;
    }
    //todo: acordarse del caso donde el checkbox es ideterminado

    public void deleteShadow(){
        for (E figure : figures) {
            figure.deleteShadow();
        }
    }
    public void addGradient(){
        for (E figure : figures) {
            figure.addGradient();
        }
    }
    public boolean hasGradient(){
        for (E figure : figures) {
            if (!figure.hasGradient()){
                return false;
            }
        }
        return true;
    }
    public void deleteGradient(){
        for (E figure : figures) {
            figure.deleteGradient();
        }
    }
    public void addBevel(){
        for (E figure : figures) {
            figure.addBevel();
        }
    }
    public boolean hasBevel(){
        for (E figure : figures) {
            if (!figure.hasBevel()){
                return false;
            }
        }
        return true;
    }
    public void deleteBevel(){
        for (E figure : figures) {
            figure.deleteBevel();
        }
    }
    public void setFillColor(Color color) {
        for (E figure : figures) {
            figure.setFillColor(color);
        }
    }
    public Color getColor() {
        return Color.YELLOW;
    }
}
