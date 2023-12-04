package src.frontend;

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
import src.backend.CanvasState;
import src.backend.model.*;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PaintPane extends BorderPane {

    // BackEnd
    CanvasState canvasState;

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
    Set<Figure> selectedFigures = new HashSet<>();

    // StatusBar
    StatusPane statusPane;

    // Colores de relleno de cada figura
    Map<Figure, Color> figureColorMap = new HashMap<>();

    public PaintPane(CanvasState canvasState, StatusPane statusPane) {
        this.canvasState = canvasState;
        this.statusPane = statusPane;

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
        buttonsBox.setStyle("-fx-background-color: #999");
        buttonsBox.setPrefWidth(100);

        CheckBox[] styleArr = {shadowButton, gradientButton, beveledButton};

        shadowButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                for (Figure figure : selectedFigures) {
                    figure.applyShadow(gc);
                }
            } else {
                redrawCanvas();
            }
        });

        gradientButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                for (Figure figure : selectedFigures) {
                    figure.applyGradient(gc, fillColorPicker.getValue());
                }
            } else {
                redrawCanvas();
            }
        });

        beveledButton.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                for (Figure figure : selectedFigures) {
                    figure.applyBevel(gc);
                }
            } else {
                redrawCanvas();
            }
        });

        HBox checkBoxesBox = new HBox(10);
        Label checkBoxesLabel = new Label("Efectos: ");
        checkBoxesBox.setStyle("-fx-background-color: #999");
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
            Figure newFigure;
            if (rectangleButton.isSelected()) {
                newFigure = new Rectangle(startPoint, endPoint);
            } else if (circleButton.isSelected()) {
                double circleRadius = Math.abs(endPoint.getX() - startPoint.getX());
                newFigure = new Circle(startPoint, circleRadius);
            } else if (squareButton.isSelected()) {
                double size = Math.abs(endPoint.getX() - startPoint.getX());
                newFigure = new Square(startPoint, size);
            } else if (ellipseButton.isSelected()) {
                Point centerPoint = new Point(Math.abs(endPoint.getX() + startPoint.getX()) / 2, (Math.abs((endPoint.getY() + startPoint.getY())) / 2));
                double sMayorAxis = Math.abs(endPoint.getX() - startPoint.getX());
                double sMinorAxis = Math.abs(endPoint.getY() - startPoint.getY());
                newFigure = new Ellipse(centerPoint, sMayorAxis, sMinorAxis);
            } else {
                return;
            }
            figureColorMap.put(newFigure, fillColorPicker.getValue());
            canvasState.addFigure(newFigure);
            startPoint = null;
            redrawCanvas();
        });

        deleteButton.setOnAction(event -> {
            if (!selectedFigures.isEmpty()) {
                for (Figure figure : selectedFigures) {
                    canvasState.deleteFigure(figure);
                }
                selectedFigures.clear();
                redrawCanvas();
            }
        });

        groupButton.setOnAction(event -> {
            if (!selectedFigures.isEmpty()) {
                Figure newFigure = new ComplexFigure(selectedFigures);
                canvasState.addFigure(newFigure);
                for (Figure figure : selectedFigures) {
                    canvasState.deleteFigure(figure);
                }
                selectedFigures.clear();
                redrawCanvas();
            }
        });

        ungroupButton.setOnAction(event -> {
            if (!selectedFigures.isEmpty()) {
                for (Figure figure : selectedFigures) {
                    if (figure instanceof ComplexFigure complexFigure) {
                        for (Figure simpleFigure : complexFigure.getFigures()) {
                            canvasState.addFigure(simpleFigure);
                        }
                        canvasState.deleteFigure(figure);
                    }
                }
                selectedFigures.clear();
                redrawCanvas();
            }
        });

        rotateRightButton.setOnAction(event -> {
            if (!selectedFigures.isEmpty()) {
                Set<Figure> newFigures = new HashSet<>();
                Set<Figure> oldFigures = new HashSet<>();
                for (Figure figure : selectedFigures) {
                    Figure newFigure = figure.rotateFigure();
                    oldFigures.add(figure);
                    newFigures.add(newFigure);
                    figureColorMap.put(newFigure, figureColorMap.get(figure));
                    canvasState.deleteFigure(figure);
                    canvasState.addFigure(newFigure);
                }
                selectedFigures.removeAll(oldFigures);
                selectedFigures.addAll(newFigures);
                startPoint = null;
                redrawCanvas();
            }
        });

        flipHorizontallyButton.setOnAction(event -> {
            if (!selectedFigures.isEmpty()) {
                Set<Figure> newFigures = new HashSet<>();
                Set<Figure> oldFigures = new HashSet<>();
                for (Figure figure : selectedFigures) {
                    Figure newFigure = figure.flipHorizontally();
                    oldFigures.add(figure);
                    newFigures.add(newFigure);
                    figureColorMap.put(newFigure, figureColorMap.get(figure));
                    canvasState.deleteFigure(figure);
                    canvasState.addFigure(newFigure);
                }
                selectedFigures.removeAll(oldFigures);
                selectedFigures.addAll(newFigures);
                startPoint = null;
                redrawCanvas();
            }
        });

        flipVerticallyButton.setOnAction(event -> {
            if (!selectedFigures.isEmpty()) {
                Set<Figure> newFigures = new HashSet<>();
                Set<Figure> oldFigures = new HashSet<>();
                for (Figure figure : selectedFigures) {
                    Figure newFigure = figure.flipVertically();
                    oldFigures.add(figure);
                    newFigures.add(newFigure);
                    figureColorMap.put(newFigure, figureColorMap.get(figure));
                    canvasState.deleteFigure(figure);
                    canvasState.addFigure(newFigure);
                }
                selectedFigures.removeAll(oldFigures);
                selectedFigures.addAll(newFigures);
                startPoint = null;
                redrawCanvas();
            }
        });

        scaleButton.setOnAction(event -> {
            if (!selectedFigures.isEmpty()) {
                Set<Figure> newFigures = new HashSet<>();
                Set<Figure> oldFigures = new HashSet<>();
                for (Figure figure : selectedFigures) {
                    Figure newFigure = figure.scaleFigure(1.25);
                    oldFigures.add(figure);
                    newFigures.add(newFigure);
                    figureColorMap.put(newFigure, figureColorMap.get(figure));
                    canvasState.deleteFigure(figure);
                    canvasState.addFigure(newFigure);
                }
                selectedFigures.removeAll(oldFigures);
                selectedFigures.addAll(newFigures);
                startPoint = null;
                redrawCanvas();
            }
        });

        downscaleButton.setOnAction(event -> {
            if (!selectedFigures.isEmpty()) {
                Set<Figure> newFigures = new HashSet<>();
                Set<Figure> oldFigures = new HashSet<>();
                for (Figure figure : selectedFigures) {
                    Figure newFigure = figure.scaleFigure(0.75);
                    oldFigures.add(figure);
                    newFigures.add(newFigure);
                    figureColorMap.put(newFigure, figureColorMap.get(figure));
                    canvasState.deleteFigure(figure);
                    canvasState.addFigure(newFigure);
                }
                selectedFigures.removeAll(oldFigures);
                selectedFigures.addAll(newFigures);
                startPoint = null;
                redrawCanvas();
            }
        });

        canvas.setOnMouseMoved(event -> {
            Point eventPoint = new Point(event.getX(), event.getY());
            boolean found = false;
            StringBuilder label = new StringBuilder();
            for (Figure figure : canvasState.figures()) {
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
                for (Figure figure : canvasState.figures()) {
                    if (figure.isWithin(startPoint, eventPoint) || figure.figureBelongs(eventPoint)) {
                        selectedFigures.add(figure);
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
        for (Figure figure : canvasState.figures()) {
            if (selectedFigures.contains(figure)) {
                gc.setStroke(Color.RED);
            } else {
                gc.setStroke(lineColor);
            }
            gc.setFill(figureColorMap.get(figure));
            figure.drawFigure(gc);
        }
    }
}