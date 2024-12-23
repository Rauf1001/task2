package com.cgvsu.protocurvefxapp;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;


public class ProtoCurveController {

    @FXML
    AnchorPane anchorPane;
    @FXML
    private Canvas canvas;


    @FXML
    private void initialize() {
        anchorPane.prefWidthProperty().addListener((ov, oldValue, newValue) -> canvas.setWidth(newValue.doubleValue()));
        anchorPane.prefHeightProperty().addListener((ov, oldValue, newValue) -> canvas.setHeight(newValue.doubleValue()));

        DDA.drawDDA(
                canvas.getGraphicsContext2D(),
                100, 200, 1000, 200, Color.GREEN, Color.RED);
        BM.drawBM(
                canvas.getGraphicsContext2D(),
                100, 300, 1000, 300, Color.PALEGREEN, Color.BLUE);
        BY.drawLine(
                canvas.getGraphicsContext2D(),
                100, 400, 1000, 400);
//        DDA.drawDDA(
//                canvas.getGraphicsContext2D(),
//                100, 200, 1000, 200, Color.BLACK, Color.WHITE);
//        DDA.drawDDA(
//                canvas.getGraphicsContext2D(),
//                100, 300, 1000, 300, Color.PINK, Color.BLUE);
    }

}