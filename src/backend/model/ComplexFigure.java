package src.backend.model;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.List;

public class ComplexFigure implements Figure {

    private final List<Figure> figures = new ArrayList<>();

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
}
