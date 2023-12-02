package src.backend.model;

import javafx.scene.canvas.GraphicsContext;

import java.util.HashSet;
import java.util.Set;

public class ComplexFigure implements Figure {

    private final Set<Figure> figures = new HashSet<>();

    public ComplexFigure(Set<Figure> selectedFigures) {
        for (Figure figure : selectedFigures) {
            if (figure instanceof ComplexFigure complexFigure) {
                figures.addAll(complexFigure.getFigures());
            } else {
                figures.add(figure);
            }
        }
    }

    public Set<Figure> getFigures() {
        return figures;
    }

    @Override
    public boolean figureBelongs(Point eventPoint) {
        for (Figure figure : figures) {
            if (figure.figureBelongs(eventPoint)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void drawFigure(GraphicsContext gc) {
        for (Figure figure : figures) {
            figure.drawFigure(gc);
        }
    }

    @Override
    public void moveFigure(double diffX, double diffY) {
        for (Figure figure : figures) {
            figure.moveFigure(diffX, diffY);
        }
    }

    @Override
    public boolean isWithin(Point topLeft, Point bottomRight) {
        for (Figure figure : figures) {
            System.out.println(figure.toString() + figure.isWithin(topLeft, bottomRight));
            if (!figure.isWithin(topLeft, bottomRight)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder toReturn = new StringBuilder();
        for (Figure figure : figures) {
            toReturn.append(figure.toString()).append(", ");
        }
        return toReturn.toString();
    }
}
