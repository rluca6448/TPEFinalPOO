package frontend;

import backend.CanvasState;
import frontend.model.FigureFront;
import javafx.scene.layout.VBox;

public class MainFrame extends VBox {

    public MainFrame(CanvasState<FigureFront> canvasState) {
        getChildren().add(new AppMenuBar());
        StatusPane statusPane = new StatusPane();
        getChildren().add(new PaintPane(canvasState, statusPane));
        getChildren().add(statusPane);
    }

}
