package me.petrolingus.pdc;

import javafx.application.Platform;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import me.petrolingus.pdc.core.Algorithm;
import me.petrolingus.pdc.core.Particle;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Controller {

    public Canvas canvas;

    public void initialize() {

        final double width = canvas.getWidth();
        final double height = canvas.getHeight();
        GraphicsContext graphicsContext = canvas.getGraphicsContext2D();

        // Make canvas background black
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRect(0, 0, width, height);

        // Create and run algorithm
        Algorithm algorithm = new Algorithm(100);
        new Thread(algorithm).start();

        // Drawing particles from algorithm
        Canvas backgroundCanvas = new Canvas(1200, 1200);
        GraphicsContext backgroundContext = backgroundCanvas.getGraphicsContext2D();
        backgroundContext.setFill(Color.ORANGE);
        SnapshotParameters params = new SnapshotParameters();
        params.setFill(Color.BLACK);
        Executors.newSingleThreadScheduledExecutor().scheduleWithFixedDelay(() -> {

            // Clear and fill background canvas
            backgroundContext.clearRect(0, 0, 1200, 1200);
            for (Particle particle : algorithm.getParticles()) {
                double x = 1200 * particle.getX() - 5;
                double y = 1200 * particle.getY() - 5;
                backgroundContext.fillOval(x, y, 30, 30);
            }

            // Copy image from background canvas to view canvas
            Platform.runLater(() -> {
                graphicsContext.clearRect(0, 0, 1200, 1200);
                graphicsContext.drawImage(backgroundCanvas.snapshot(params, null), 0, 0);
            });
        }, 0, 6, TimeUnit.MILLISECONDS);

    }

}
