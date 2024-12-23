package com.cgvsu.protocurvefxapp;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

import static java.lang.Math.abs;

public class DDA {
    public static void drawDDA(
            final GraphicsContext graphicsContext,
            final int x0, final int y0,
            final int x1, final int y1,
            final Color color_start, final Color color_end
    ) {
        final PixelWriter pixelWriter = graphicsContext.getPixelWriter();

        Math_DDA(x0, y0, x1, y1, color_start, color_end, pixelWriter);

    }

    public static void Math_DDA(
            int x0, int y0,
            int x1, int y1,
            Color color_start, Color color_end,
            PixelWriter pixelWriter
    ) {
        double dx = x1 - x0;
        double dy = y1 - y0;

        double Distance = Math.sqrt(dx * dx + dy * dy);
        double step = Math.max(abs(dx), abs(dy));

        double x = x0;
        double y = y0;
        dx = dx / step;
        dy = dy / step;

        double startRed = color_start.getRed();
        double startGreen = color_start.getGreen();
        double startBlue = color_start.getBlue();

        double endRed = color_end.getRed();
        double endGreen = color_end.getGreen();
        double endBlue = color_end.getBlue();

        for (int i = 0; i <= step; i++) {
            double Distance_current = Math.sqrt((x - x0) * (x - x0) + (y - y0) * (y - y0));

            double c = Distance_current / Distance;

            double currentRed = startRed + (endRed - startRed) * c;
            double currentGreen = startGreen + (endGreen - startGreen) * c;
            double currentBlue = startBlue + (endBlue - startBlue) * c;

            Color interpolatedColor = Color.color(currentRed, currentGreen, currentBlue);

            // Устанавливаем цвет пикселя
            pixelWriter.setColor((int) Math.round(x), (int) Math.round(y), interpolatedColor);

            x += dx;
            y += dy;
        }


    }
}
