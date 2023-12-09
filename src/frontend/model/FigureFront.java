package frontend.model;

import backend.model.Figure;
import javafx.scene.canvas.GraphicsContext;

public interface FigureFront extends Figure {

    void drawFigure(GraphicsContext gc);

}
