package com.cgvsu.protocurvefxapp;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

import static java.lang.Math.abs;

public class BM {
    public static void drawBM(
            final GraphicsContext graphicsContext,
            final int x0, final int y0,
            final int x1, final int y1,
            final Color color_start, final Color color_end
    ) {
        final PixelWriter pixelWriter = graphicsContext.getPixelWriter();

        Math_BM(x0, y0, x1, y1, color_start, color_end,pixelWriter);
    }

    public static void Math_BM(
            int x0, int y0,
            int x1, int y1,
            Color color_start, Color color_end,
            PixelWriter pixelWriter
    ) {

        if (x0 == x1) {
            drawVerticaclLine(x0, y0, y1, pixelWriter);
            return;
        }

        boolean step = Math.abs(y1 - y0) > Math.abs(x1 - x0);
        if (step) {
            int temp = x0;
            y0 = temp;
            temp = x1;
            x1 = y1;
            y1 = temp;
        }
        if (x0 > x1) {
            int temp = x0;
            x0 = x1;
            x1 = temp;
            temp = y0;
            y0 = y1;
            y1 = temp;
        }

        double dx = abs(x1 - x0);
        double dy = abs(y1 - y0);

        double Distance = Math.sqrt(dx * dx + dy * dy);

        double error = dx / 2;
        double ystep = (y0 < y1) ? 1 : -1;

        double y = y0;

        double startRed = color_start.getRed();
        double startGreen = color_start.getGreen();
        double startBlue = color_start.getBlue();

        double endRed = color_end.getRed();
        double endGreen = color_end.getGreen();
        double endBlue = color_end.getBlue();

        for (int x = x0; x <= x1; x++) {
            double Distance_current = Math.sqrt((x - x0) * (x - x0) + (y - y0) * (y - y0));

            double c = Distance_current / Distance;

            double currentRed = startRed + (endRed - startRed) * c;
            double currentGreen = startGreen + (endGreen - startGreen) * c;
            double currentBlue = startBlue + (endBlue - startBlue) * c;

            Color interpolatedColor = Color.color(currentRed, currentGreen, currentBlue);

            pixelWriter.setColor((int) Math.round(x), (int) Math.round(y), interpolatedColor);

            error -= dy;
            if (error < 0) {
                y += ystep;
                error += dx;
            }
        }
    }

    private static void drawVerticaclLine(
            int x0, int y0,
            int y1,
            PixelWriter pixelWriter) {
        if (y0 > y1) {
            int temp = y0;
            y0 = y1;
            y1 = temp;
        }
        for (int y = y0; y <= y1; y++) {
            pixelWriter.setColor(Math.round(x0), (int) Math.round(y), Color.BLACK);
        }
    }
}
