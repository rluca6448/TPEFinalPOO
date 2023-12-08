package frontend.model;

import backend.model.Figure;
import javafx.scene.canvas.GraphicsContext;

import java.util.Set;

public interface FigureFront extends FigureWithFrontProperties<FigureFront> {

    void drawFigure(GraphicsContext gc);

    //void applyShadow(GraphicsContext gc);

    //void applyGradient(GraphicsContext gc);

    //void applyBevel(GraphicsContext gc);

}
