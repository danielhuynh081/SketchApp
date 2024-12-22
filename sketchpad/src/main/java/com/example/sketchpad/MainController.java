package com.example.sketchpad;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;

public class MainController {

    @FXML
    private Canvas canvas;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private ComboBox<Integer> brushSizeComboBox;
    @FXML
    private ComboBox<String> brushTypeComboBox;
    @FXML
    private Button clearButton;

    private GraphicsContext gc;
    private Model model;
    private double lastX, lastY;
    private String currentBrushType = "Pencil";

    @FXML
    public void initialize() {
        // Create a new instance of the Model class
        model = new Model();

        gc = canvas.getGraphicsContext2D();
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(5);

        setupBrushSizeComboBox();
        setupBrushTypeComboBox();
        setupColorPicker();
        setupCanvasListeners();
        setupClearButton();
    }

    private void setupBrushSizeComboBox() {
        ObservableList<Integer> list = FXCollections.observableArrayList(1, 3, 5, 10, 15, 20, 30, 40, 50);
        brushSizeComboBox.setItems(list);
        brushSizeComboBox.setValue(5);
        brushSizeComboBox.setOnAction(e -> gc.setLineWidth(brushSizeComboBox.getValue()));
    }

    private void setupBrushTypeComboBox() {
        ObservableList<String> list = FXCollections.observableArrayList("Pencil", "Pen", "Marker", "Crayon", "Eraser");
        brushTypeComboBox.setItems(list);
        brushTypeComboBox.setValue("Pencil");
        brushTypeComboBox.setOnAction(e -> currentBrushType = brushTypeComboBox.getValue());
    }

    private void setupColorPicker() {
        colorPicker.setValue(Color.BLACK);
        colorPicker.setOnAction(e -> gc.setStroke(colorPicker.getValue()));
    }

    private void setupCanvasListeners() {
        canvas.setOnMousePressed(e -> {
            lastX = e.getX();
            lastY = e.getY();
        });

        canvas.setOnMouseDragged(e -> {
            switch (currentBrushType) {
                case "Pen":
                    model.drawPen(gc, lastX, lastY, e.getX(), e.getY());
                    break;
                case "Pencil":
                    model.drawPencil(gc, lastX, lastY, e.getX(), e.getY());
                    break;
                case "Marker":
                    model.drawMarker(gc, lastX, lastY, e.getX(), e.getY());
                    break;
                case "Crayon":
                    model.drawCrayon(gc, lastX, lastY, e.getX(), e.getY());
                    break;
                case "Eraser":
                    model.eraser(gc, lastX, lastY, e.getX(), e.getY());
                    break;
            }
            lastX = e.getX();
            lastY = e.getY();
        });
    }

    private void setupClearButton() {
        clearButton.setOnAction(e -> gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight()));
    }
}
