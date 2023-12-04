package backend.model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

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
            if (!figure.isWithin(topLeft, bottomRight)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public Figure rotateFigure() {
        Set<Figure> toReturn = new HashSet<>();
        for (Figure figure : figures) {
            toReturn.add(figure.rotateFigure());
        }
        return new ComplexFigure(toReturn);
    }

    @Override
    public Figure flipHorizontally() {
        Set<Figure> toReturn = new HashSet<>();
        for (Figure figure : figures) {
            toReturn.add(figure.flipHorizontally());
        }
        return new ComplexFigure(toReturn);
    }

    @Override
    public Figure flipVertically() {
        Set<Figure> toReturn = new HashSet<>();
        for (Figure figure : figures) {
            toReturn.add(figure.flipVertically());
        }
        return new ComplexFigure(toReturn);
    }

    @Override
    public Figure scaleFigure(double factor) {
        Set<Figure> toReturn = new HashSet<>();
        for (Figure figure : figures) {
            toReturn.add(figure.scaleFigure(factor));
        }
        return new ComplexFigure(toReturn);
    }

    @Override
    public void applyShadow(GraphicsContext gc) {
        for (Figure figure : figures) {
            figure.applyShadow(gc);
        }
    }

    @Override
    public void applyGradient(GraphicsContext gc, Color fillColor) {
        for (Figure figure : figures) {
            figure.applyGradient(gc, fillColor);
        }
    }

    @Override
    public void applyBevel(GraphicsContext gc) {
        for (Figure figure : figures) {
            figure.applyBevel(gc);
        }
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
