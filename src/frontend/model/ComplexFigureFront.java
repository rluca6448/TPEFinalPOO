package frontend.model;

import backend.model.ComplexFigure;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.List;
import java.util.Set;

public class ComplexFigureFront extends ComplexFigure<FigureFront> implements FigureFront {
    public ComplexFigureFront(Set<FigureFront> selectedFigures) {
        super(selectedFigures);
    }
    public ComplexFigureFront(){
        super();
    }
    @Override
    public void drawFigure(GraphicsContext gc) {
        for (FigureFront figure : getFigures()) {
            figure.drawFigure(gc);
        }
    }
}
