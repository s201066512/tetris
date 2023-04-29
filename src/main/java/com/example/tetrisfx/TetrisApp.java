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
import java.io.IOException;
import java.util.ArrayList;
import static com.example.tetrisfx.Board.drawBoard;

/*
    When the piece is moved down, check if any of the blocks are touching the bottom of the game board. If any block is touching the bottom, set the isStopped flag to true.

    When the piece is moved left or right, check if any of the blocks are touching the left or right walls of the game board. If any block is touching a wall, prevent the piece from moving in that direction.

    When the piece is rotated, check if any of the blocks are overlapping with other blocks in the game board. If there is an overlap, prevent the rotation.

    When the piece is stopped, add each of its blocks to the blocks Group and remove the listeners for keyboard events so that the player can't move it anymore.

    Check for completed lines by iterating through the game board rows and counting the number of blocks in each row. If a row is full, remove all the blocks in that row and move down all the blocks above it.

    Create a new piece and add listeners for keyboard events to allow the player to move it.*/

public class TetrisApp extends Application {
    final static double WIDTH = 500;
    final static double HEIGHT = 700;
    @Override
    public void start(Stage stage) throws IOException {
        ArrayList<Line> grid = drawBoard();
        String[] requests = {"O", "I", "S", "Z", "L", "J", "T"};
        int random = (int) (Math.random() * 6); // 0 to 6
        Pane pane = new Pane();
        for (Line line : grid) {
            pane.getChildren().add(line);
        }
        Pieces builder = new Pieces();
        Pieces currentPiece = builder.makePiece(requests[random]); // add random value here
        pane.getChildren().add(currentPiece.getGroup());
        Scene scene = new Scene(pane, WIDTH, HEIGHT, Color.WHITE);
        stage.setTitle("Tetris");
        stage.setScene(scene);
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            KeyCode code = key.getCode();
            if (code == KeyCode.RIGHT) {
                currentPiece.moveRight();
            }
            else if (code == KeyCode.LEFT) {
                currentPiece.moveLeft();
            }
            else if (code == KeyCode.DOWN) {
                currentPiece.softDrop();
            }
            else if (code == KeyCode.UP) {
                currentPiece.rotateRight();
            }

        });

        Timeline fall = new Timeline(
                new KeyFrame(Duration.seconds(0.75), event -> {
                    currentPiece.softDrop();
                    if (currentPiece.isStop()){
                    }
                })
        );
        fall.setCycleCount(Timeline.INDEFINITE);
        fall.play();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}