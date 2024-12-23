package com.cgvsu.protocurvefxapp;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.paint.Color;

public class BY {

    // Функция для рисования пикселя с координатами (x, y) и яркостью c

    // Функция для рисования линии от (x1, y1) до (x2, y2) с использованием сглаживания
    public static void drawLine(
            final GraphicsContext graphicsContext,
            int x1, int y1,
            int x2, int y2
    ) {
        final PixelWriter pixelWriter = graphicsContext.getPixelWriter();
        if (x2 < x1) {
            // Меняем местами начальную и конечную точку
            int temp = x1;
            x1 = x2;
            x2 = temp;
            temp = y1;
            y1 = y2;
            y2 = temp;
        }

        double dx = x2 - x1;
        double dy = y2 - y1;
        double gradient = dy / dx;

        // Обработка начальной точки
        int xend = round(x1);
        double yend = y1 + gradient * (xend - x1);
        double xgap = 1 - fpart(x1 + 0.5);
        int xpxl1 = xend;
        int ypxl1 = ipart(yend);
        plot(xpxl1, ypxl1, (1 - fpart(yend)) * xgap, pixelWriter);
        plot(xpxl1, ypxl1 + 1, fpart(yend) * xgap, pixelWriter);

        double intery = yend + gradient; // Первое пересечение по оси Y для цикла

        // Обработка конечной точки
        xend = round(x2);
        yend = y2 + gradient * (xend - x2);
        xgap = fpart(x2 + 0.5);
        int xpxl2 = xend;
        int ypxl2 = ipart(yend);
        plot(xpxl2, ypxl2, (1 - fpart(yend)) * xgap, pixelWriter);
        plot(xpxl2, ypxl2 + 1, fpart(yend) * xgap, pixelWriter);

        // Основной цикл от рисования пикселей между начальной и конечной точкой
        for (int x = xpxl1 + 1; x < xpxl2; x++) {
            plot(x, ipart(intery), 1 - fpart(intery), pixelWriter);
            plot(x, ipart(intery) + 1, fpart(intery), pixelWriter);
            intery += gradient;
        }
    }
    public static void plot(int x, int y, double c, PixelWriter pixelWriter) {
        // Убедитесь, что яркость находится в пределах от 0 до 1
        c = Math.max(0, Math.min(1, c));
        // Устанавливаем цвет пикселя
        pixelWriter.setColor(x, y, new Color(c, c, c, 1.0));
    }

    // Функция для вычисления целой части числа
    public static int ipart(double x) {
        return (int) Math.floor(x);
    }

    // Функция для округления числа до ближайшего целого
    public static int round(double x) {
        return ipart(x + 0.5);
    }

    // Функция для вычисления дробной части числа
    public static double fpart(double x) {
        return x - Math.floor(x);
    }


}
