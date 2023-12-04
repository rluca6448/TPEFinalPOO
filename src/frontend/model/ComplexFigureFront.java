package frontend.model;

import backend.model.ComplexFigure;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Set;

public class ComplexFigureFront extends ComplexFigure<FigureFront> implements FigureFront {
    public ComplexFigureFront(Set<FigureFront> selectedFigures) {
        super(selectedFigures);
    }
    @Override
    public void drawFigure(GraphicsContext gc) {
        for (FigureFront figure : getFigures()) {
            figure.drawFigure(gc);
        }
    }
    @Override
    public void applyShadow(GraphicsContext gc) {
        for (FigureFront figure : getFigures()) {
            figure.applyShadow(gc);
        }
    }

    @Override
    public void applyGradient(GraphicsContext gc) {
        for (FigureFront figure : getFigures()) {
            figure.applyGradient(gc);
        }
    }

    @Override
    public void applyBevel(GraphicsContext gc) {
        for (FigureFront figure : getFigures()) {
            figure.applyBevel(gc);
        }
    }
}
