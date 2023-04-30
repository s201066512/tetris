package com.example.tetrisfx;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import static com.example.tetrisfx.Board.drawBoard;

/*
    When the piece is moved down, check if any of the blocks are touching the bottom of the game board. If any block is touching the bottom, set the isStopped flag to true.

    When the piece is moved left or right, check if any of the blocks are touching the left or right walls of the game board. If any block is touching a wall, prevent the piece from moving in that direction.

    When the piece is rotated, check if any of the blocks are overlapping with other blocks in the game board. If there is an overlap, prevent the rotation.

    Check for completed lines by iterating through the game board rows and counting the number of blocks in each row. If a row is full, remove all the blocks in that row and move down all the blocks above it.

    */

public class TetrisApp extends Application {
    final static double WIDTH = 500;
    final static double HEIGHT = 700;
    @Override
    public void start(Stage stage) {
        ArrayList<Line> grid = drawBoard();
        List<List<Integer>> occupiedSquares = new ArrayList<>();
        String[] requests = {"O", "I", "S", "Z", "L", "J", "T"};
        int initial = (int) (Math.random() * 6); // 0 to 6
        Pane pane = new Pane();
        for (Line line : grid) {
            pane.getChildren().add(line);
        }
        Pieces builder = new Pieces();
        AtomicReference<Pieces> currentPiece = new AtomicReference<>(builder.makePiece(requests[initial])); // add random value here
        pane.getChildren().add(currentPiece.get().getGroup());

        Scene scene = new Scene(pane, WIDTH, HEIGHT, Color.WHITE);
        stage.setTitle("Tetris");
        stage.setScene(scene);
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            KeyCode code = key.getCode();
            if (code == KeyCode.RIGHT) {
                currentPiece.get().moveRight();
            }
            else if (code == KeyCode.LEFT) {
                currentPiece.get().moveLeft();
            }
            else if (code == KeyCode.DOWN) {
                currentPiece.get().softDrop();
            }
            else if (code == KeyCode.UP) {
                currentPiece.get().rotateRight();
            }

        });

        Timeline fall = new Timeline(
                new KeyFrame(Duration.seconds(0.75), event -> {
                    if (!currentPiece.get().isStop()){
                        currentPiece.get().softDrop();
                    }
                })
        );
        Timeline check = new Timeline(
                new KeyFrame(Duration.seconds(0.01), event -> {
                    if (currentPiece.get().isStop()){
                        int[][] currentPieceCoordinates = currentPiece.get().getCoordinates();
                        for (int i = 0; i < 4; i++) {
                            List<Integer> square = new ArrayList<>();
                            square.add(currentPieceCoordinates[i][0]);
                            square.add(currentPieceCoordinates[i][1]);
                            occupiedSquares.add(square);
                        }
                        int random = (int) (Math.random() * 6); // 0 to 6
                        currentPiece.set(builder.makePiece(requests[random])); // add random value here
                        pane.getChildren().add(currentPiece.get().getGroup());
                    }
                    int[][] coordinates = currentPiece.get().getCoordinates();
                    for (int i = 0; i < occupiedSquares.size(); i++){
                        for (int j = 0; j < coordinates.length; j++){
                            List<Integer> square = Arrays.asList(coordinates[j][0], coordinates[j][1] + 35);
                            if (occupiedSquares.get(i).equals(square)){
                                currentPiece.get().setStop(true);
                            }
                        }
                    }
                })
        );
        fall.setCycleCount(Timeline.INDEFINITE);
        check.setCycleCount(Timeline.INDEFINITE);
        fall.play();
        check.play();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}