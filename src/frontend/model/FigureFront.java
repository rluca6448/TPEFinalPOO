package frontend.model;

import backend.model.Figure;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public interface FigureFront extends Figure {
    void drawFigure(GraphicsContext gc);
    void applyShadow(GraphicsContext gc);

    void applyGradient(GraphicsContext gc);

    void applyBevel(GraphicsContext gc);
}
