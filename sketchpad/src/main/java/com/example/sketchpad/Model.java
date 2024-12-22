package com.example.sketchpad;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;

public class Model {
    public static void drawPen(GraphicsContext gc, double lastX, double lastY, double currentX, double currentY) {
        gc.setLineCap(StrokeLineCap.ROUND);
        gc.strokeLine(lastX, lastY, currentX, currentY);
    }

    public static void eraser(GraphicsContext gc, double lastX, double lastY, double currentX, double currentY) {
        double eraserSize = gc.getLineWidth();
        gc.clearRect(currentX - eraserSize / 2, currentY - eraserSize / 2, eraserSize, eraserSize);
    }

    public static void drawPencil(GraphicsContext gc, double lastX, double lastY, double currentX, double currentY) {
        gc.setLineCap(StrokeLineCap.ROUND);
        Color currentColor = (Color) gc.getStroke();
        gc.setStroke(new Color(currentColor.getRed(), currentColor.getGreen(), currentColor.getBlue(), 0.8));
        for (int i = 0; i < 10; i++) {
            double offsetX = (Math.random() - 0.5) * 2;
            double offsetY = (Math.random() - 0.5) * 2;
            gc.strokeLine(lastX + offsetX, lastY + offsetY, currentX + offsetX, currentY + offsetY);
        }
    }

    public static void drawMarker(GraphicsContext gc, double lastX, double lastY, double currentX, double currentY) {
        gc.setLineCap(StrokeLineCap.ROUND);
        gc.setStroke(new Color(((Color) gc.getStroke()).getRed(), ((Color) gc.getStroke()).getGreen(), ((Color) gc.getStroke()).getBlue(), 0.2));
        gc.strokeLine(lastX, lastY, currentX, currentY);
    }

    public static void drawCrayon(GraphicsContext gc, double lastX, double lastY, double currentX, double currentY) {
        gc.setLineCap(StrokeLineCap.ROUND);
        gc.setLineJoin(StrokeLineJoin.ROUND);
        Color currentColor = (Color) gc.getStroke();
        gc.setStroke(new Color(currentColor.getRed(), currentColor.getGreen(), currentColor.getBlue(), .3)); //Keep Color and change opacity
        // Randomness to add rough texture to the crayon line
        for (int i = 0; i < 12; i++) {  // Adding a few more random strokes to simulate roughness
            // Slight random offset to create a streaky texture
            double offsetX = (Math.random() - 0.5) * 5;  // Random horizontal offset
            double offsetY = (Math.random() - 0.5) * 5;  // Random vertical offset

            // Slight variation in the line direction to make the strokes less uniform
            double angle = Math.random() * Math.PI * 2;  // Random angle to simulate the unevenness of crayon strokes

            // Random length for the crayon stroke
            double randomLength = Math.random() * 3 + 2;

            // Apply the offset and angle to the stroke points
            double x1 = lastX + offsetX + Math.cos(angle) * randomLength;
            double y1 = lastY + offsetY + Math.sin(angle) * randomLength;
            double x2 = currentX + offsetX + Math.cos(angle) * randomLength;
            double y2 = currentY + offsetY + Math.sin(angle) * randomLength;

            // Draw the rough crayon stroke
            gc.strokeLine(x1, y1, x2, y2);
        }
    }
}
