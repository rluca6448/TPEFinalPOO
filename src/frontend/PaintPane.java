package frontend;

import frontend.model.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import backend.*;
import backend.model.*;

import java.util.ArrayList;
import java.util.List;

public class PaintPane extends BorderPane {

    // BackEnd
    CanvasState<FigureFront> canvasState;

    // Canvas y relacionados
    Canvas canvas = new Canvas(800, 600);
    GraphicsContext gc = canvas.getGraphicsContext2D();
    Color lineColor = Color.BLACK;
    Color defaultFillColor = Color.YELLOW;

    // Botones Barra Izquierda
    ToggleButton selectionButton = new ToggleButton("Seleccionar");
    ToggleButton rectangleButton = new ToggleButton("Rectángulo");
    ToggleButton circleButton = new ToggleButton("Círculo");
    ToggleButton squareButton = new ToggleButton("Cuadrado");
    ToggleButton ellipseButton = new ToggleButton("Elipse");
    ToggleButton deleteButton = new ToggleButton("Borrar");
    ToggleButton groupButton = new ToggleButton("Agrupar");
    ToggleButton ungroupButton = new ToggleButton("Desagrupar");
    ToggleButton rotateRightButton = new ToggleButton("Girar D");
    ToggleButton flipHorizontallyButton = new ToggleButton("Voltear H");
    ToggleButton flipVerticallyButton = new ToggleButton("Voltear V");
    ToggleButton scaleButton = new ToggleButton("Escalar +");
    ToggleButton downscaleButton = new ToggleButton("Escalar -");
    CheckBox shadowButton = new CheckBox("Sombra");
    CheckBox gradientButton = new CheckBox("Gradiente");
    CheckBox beveledButton = new CheckBox("Biselado");

    // Selector de color de relleno
    ColorPicker fillColorPicker = new ColorPicker(defaultFillColor);

    // Dibujar una figura
    Point startPoint;

    // Seleccionar una figura
    SelectedFigures selectedFigures = new SelectedFigures();

    // StatusBar
    StatusPane statusPane;

    //Color background gris
    private static final String GRAY = "#999";

    // Colores de relleno de cada figura

    public PaintPane(CanvasState<FigureFront> canvasState, StatusPane statusPane) {
        this.canvasState = canvasState;
        this.statusPane = statusPane;
        shadowButton.setAllowIndeterminate(false);
        gradientButton.setAllowIndeterminate(false);
        beveledButton.setAllowIndeterminate(false);

        ToggleButton[] toolsArr = {selectionButton, rectangleButton, circleButton, squareButton, ellipseButton, deleteButton,
                groupButton, ungroupButton,
                rotateRightButton, flipHorizontallyButton, flipVerticallyButton, scaleButton, downscaleButton};
        ToggleGroup tools = new ToggleGroup();
        for (ToggleButton tool : toolsArr) {
            tool.setMinWidth(90);
            tool.setToggleGroup(tools);
            tool.setCursor(Cursor.HAND);
        }
        VBox buttonsBox = new VBox(10);
        buttonsBox.getChildren().addAll(toolsArr);
        buttonsBox.getChildren().add(fillColorPicker);
        buttonsBox.setPadding(new Insets(5));
        buttonsBox.setStyle("-fx-background-color: " + GRAY);
        buttonsBox.setPrefWidth(100);

        CheckBox[] styleArr = {shadowButton, gradientButton, beveledButton};

        shadowButton.setOnAction(event -> {
            if (shadowButton.isSelected()) {
                for (FigureFront figure : selectedFigures) {
                    figure.addShadow();
                }
            } else {
                for (FigureFront figure : selectedFigures) {
                    figure.deleteShadow();
                }
            }
            redrawCanvas();
        });
        gradientButton.setOnAction(event -> {
            if (gradientButton.isSelected()) {
                for (FigureFront figure : selectedFigures) {
                    figure.addGradient();
                }
            } else {
                for (FigureFront figure : selectedFigures) {
                    figure.deleteGradient();
                }
            }
            redrawCanvas();
        });
        beveledButton.setOnAction(event -> {
            if (beveledButton.isSelected()) {
                for (FigureFront figure : selectedFigures) {
                    figure.addBevel();
                }
            } else {
                for (FigureFront figure : selectedFigures) {
                    figure.deleteBevel();
                }
            }
            redrawCanvas();
        });

        HBox checkBoxesBox = new HBox(10);
        Label checkBoxesLabel = new Label("Efectos: ");
        checkBoxesBox.setStyle("-fx-background-color: " + GRAY);
        checkBoxesBox.setPrefWidth(100);
        checkBoxesBox.setPadding(new Insets(5));
        checkBoxesBox.getChildren().add(checkBoxesLabel);
        checkBoxesBox.getChildren().addAll(styleArr);
        checkBoxesBox.setAlignment(Pos.CENTER);
        setTop(checkBoxesBox);

        gc.setLineWidth(1);

        canvas.setOnMousePressed(event -> startPoint = new Point(event.getX(), event.getY()));

        canvas.setOnMouseReleased(event -> {
            Point endPoint = new Point(event.getX(), event.getY());
            if (startPoint == null) {
                return;
            }
            if (endPoint.getX() < startPoint.getX() || endPoint.getY() < startPoint.getY()) {
                return;
            }
            FigureFront newFigure;
            RGBColor color = new RGBColor(fillColorPicker.getValue().getRed(),
                    fillColorPicker.getValue().getGreen(),
                    fillColorPicker.getValue().getBlue());
            if (rectangleButton.isSelected()) {
                newFigure = new RectangleFront(startPoint, endPoint, color);
            } else if (circleButton.isSelected()) {
                double circleRadius = Math.abs(endPoint.getX() - startPoint.getX());
                newFigure = new CircleFront(startPoint, circleRadius, color);
            } else if (squareButton.isSelected()) {
                double size = Math.abs(endPoint.getX() - startPoint.getX());
                newFigure = new SquareFront(startPoint, size, color);
            } else if (ellipseButton.isSelected()) {
                Point centerPoint = new Point(Math.abs(endPoint.getX() + startPoint.getX()) / 2, (Math.abs((endPoint.getY() + startPoint.getY())) / 2));
                double sMayorAxis = Math.abs(endPoint.getX() - startPoint.getX());
                double sMinorAxis = Math.abs(endPoint.getY() - startPoint.getY());
                newFigure = new EllipseFront(centerPoint, sMayorAxis, sMinorAxis, color);
            } else {
                return;
            }
            canvasState.addFigure(newFigure);
            startPoint = null;
            redrawCanvas();
        });

        deleteButton.setOnAction(event -> {
            if (!selectedFigures.isEmpty()) {
                for (FigureFront figure : selectedFigures) {
                    canvasState.deleteFigure(figure);
                }
                selectedFigures.clear();
                redrawCanvas();
            }
        });

        groupButton.setOnAction(event -> {
            if (!selectedFigures.isEmpty()) {
                canvasState.group(new ArrayList<>(selectedFigures));
                selectedFigures.clear();
                redrawCanvas();
            }
        });

        ungroupButton.setOnAction(event -> {
            if (!selectedFigures.isEmpty()) {
                canvasState.unGroup(new ArrayList<>(selectedFigures));
                selectedFigures.clear();
                redrawCanvas();
            }
        });

        rotateRightButton.setOnAction(event -> {
            if (!selectedFigures.isEmpty()) {
                for (Figure figure : selectedFigures) {
                    figure.rotateFigure();
                }
                startPoint = null;
                redrawCanvas();
            }
        });

        flipHorizontallyButton.setOnAction(event -> {
            if (!selectedFigures.isEmpty()) {
                for (Figure figure : selectedFigures) {
                    figure.flipHorizontally();
                }
                startPoint = null;
                redrawCanvas();
            }
        });

        flipVerticallyButton.setOnAction(event -> {
            if (!selectedFigures.isEmpty()) {
                for (Figure figure : selectedFigures) {
                    figure.flipVertically();
                }
                startPoint = null;
                redrawCanvas();
            }
        });

        scaleButton.setOnAction(event -> {
            if (!selectedFigures.isEmpty()) {
                for (Figure figure : selectedFigures) {
                    figure.scaleFigure(1.25);
                }
                startPoint = null;
                redrawCanvas();
            }
        });

        downscaleButton.setOnAction(event -> {
            if (!selectedFigures.isEmpty()) {
                for (Figure figure : selectedFigures) {
                    figure.scaleFigure(0.75);
                }
                startPoint = null;
                redrawCanvas();
            }
        });

        canvas.setOnMouseMoved(event -> {
            Point eventPoint = new Point(event.getX(), event.getY());
            boolean found = false;
            StringBuilder label = new StringBuilder();
            for (FigureFront figure : canvasState.figures()) {
                if (figure.figureBelongs(eventPoint)) {
                    found = true;
                    label.append(figure);
                }
            }
            if (found) {
                statusPane.updateStatus(label.toString());
            } else {
                statusPane.updateStatus(eventPoint.toString());
            }
        });

        canvas.setOnMouseClicked(event -> {

            if (selectionButton.isSelected()) {
                Point eventPoint = new Point(event.getX(), event.getY());
                boolean found = false;
                StringBuilder label = new StringBuilder("Se seleccionó: ");
                for (FigureFront figure : canvasState.figures()) {
                    if (figure.isWithin(startPoint, eventPoint) || figure.figureBelongs(eventPoint)) {
                        List<FigureFront> group = canvasState.getGroup(figure);
                        if (!selectedFigures.containsAll(group)) selectedFigures.addAll(group);
                        found = true;
                        label.append(figure).append(" ");
                    }
                }

                if (found) {

                    statusPane.updateStatus(label.toString());
                } else {
                    selectedFigures.clear();
                    statusPane.updateStatus("Ninguna figura encontrada");
                }
                selectedFigures.updateCheckBox(shadowButton, Figure::hasShadow);
                selectedFigures.updateCheckBox(beveledButton, Figure::hasBevel);
                selectedFigures.updateCheckBox(gradientButton, Figure::hasGradient);
                redrawCanvas();
            }
        });

        canvas.setOnMouseDragged(event -> {
            if (selectionButton.isSelected()) {
                Point eventPoint = new Point(event.getX(), event.getY());
                if (!selectedFigures.isEmpty()) {
                    double diffX = (eventPoint.getX() - startPoint.getX()) / 100;
                    double diffY = (eventPoint.getY() - startPoint.getY()) / 100;
                    for (Figure figure : selectedFigures) {
                        figure.moveFigure(diffX, diffY);
                    }
                    redrawCanvas();
                }
            }
        });

        setLeft(buttonsBox);
        setRight(canvas);
    }

    void redrawCanvas() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        for (FigureFront figure : canvasState.figures()) {
            if (selectedFigures.contains(figure)) {
                gc.setStroke(Color.RED);
            } else {
                gc.setStroke(lineColor);
            }
            figure.drawFigure(gc);
        }
    }
}