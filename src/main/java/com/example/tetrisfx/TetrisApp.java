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


public class TetrisApp extends Application {
    List<List<Integer>> occupiedSquares = new ArrayList<>(); // 2D ArrayList which contains the coordinates of the occupied squares

    final static double WIDTH = 500;
    final static double HEIGHT = 700;
    @Override
    public void start(Stage stage) {
        ArrayList<Line> grid = drawBoard();

        String[] requests = {"O", "I", "S", "Z", "L", "J", "T"};
        int initial = (int) (Math.random() * 6); // used for the first piece

        // draw the grid
        Pane pane = new Pane();
        for (Line line : grid) {
            pane.getChildren().add(line);
        }

        Pieces builder = new Pieces();
        AtomicReference<Pieces> currentPiece = new AtomicReference<>(builder.makePiece(requests[initial])); // idk what atomic reference is but intellij told me to use it
        pane.getChildren().add(currentPiece.get().getGroup()); // add currentPiece to the scene

        // typical scene creation
        Scene scene = new Scene(pane, WIDTH, HEIGHT, Color.WHITE);
        stage.setTitle("Tetris");
        stage.setScene(scene);

        // controls, pretty self explanatory
        scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
            KeyCode code = key.getCode();
    /*
    Line clearing
    this might be the hardest part yet
    A line is cleared if occupiedSquares contains 10 points with the same y value
    When a line is cleared all squares with that y value should be erased
    Then every other square must be moved 35 down
    How do I access the rectangles at the coordinates that need to be cleared?
    Once I figure that out, "erasing" them could be as simple as setting their fill to transparent
    As well as removing the coordinates from occupiedSquares
    */

            if (code == KeyCode.RIGHT) {
                int[][] coordinates = currentPiece.get().getCoordinates();
                boolean canMoveRight = true;
                for (int i = 0; i < occupiedSquares.size() && canMoveRight; i++){
                    for (int j = 0; j < coordinates.length; j++){
                        List<Integer> square = Arrays.asList(coordinates[j][0] + 50, coordinates[j][1]);
                        if (occupiedSquares.get(i).equals(square)){
                            canMoveRight = false;
                            break;
                        }
                    }
                }
                if (canMoveRight) {
                    currentPiece.get().moveRight();
                }
            }

            else if (code == KeyCode.LEFT) {
                int[][] coordinates = currentPiece.get().getCoordinates();
                boolean canMoveLeft = true;
                for (int i = 0; i < occupiedSquares.size() && canMoveLeft; i++){
                    for (int j = 0; j < coordinates.length; j++){
                        List<Integer> square = Arrays.asList(coordinates[j][0] - 50, coordinates[j][1]);
                        if (occupiedSquares.get(i).equals(square)){
                            canMoveLeft = false;
                            break;
                        }
                    }
                }
                if (canMoveLeft) {
                    currentPiece.get().moveLeft();
                }
            }

            else if (code == KeyCode.DOWN) {
                currentPiece.get().softDrop();
            }

            else if (code == KeyCode.UP) {
                int[][] coordinates = currentPiece.get().getCoordinates();
                boolean canTurnRight = true;
                if (coordinates[0][0] + 50 >= 500 || coordinates[1][0] + 50 >= 500 || coordinates[2][0] + 50 >= 500 || coordinates[3][0] + 50 >= 500){
                    canTurnRight = false;
                }
                if (coordinates[0][0] - 50 < 0  || coordinates[1][0] - 50 < 0  || coordinates[2][0] - 50 < 0 || coordinates[3][0] - 50 < 0){
                    canTurnRight = false;
                }

                for (int i = 0; i < occupiedSquares.size() && canTurnRight; i++){
                    for (int j = 0; j < coordinates.length; j++){
                        List<Integer> squareLeft = Arrays.asList(coordinates[j][0] - 50, coordinates[j][1]);
                        List<Integer> squareRight = Arrays.asList(coordinates[j][0] + 50, coordinates[j][1]);
                        List<Integer> squareUp = Arrays.asList(coordinates[j][0], coordinates[j][1] - 35);
                        List<Integer> squareDown = Arrays.asList(coordinates[j][0], coordinates[j][1] + 35);

                        if (occupiedSquares.get(i).equals(squareLeft) || occupiedSquares.get(i).equals(squareRight)
                                || occupiedSquares.get(i).equals(squareUp) || occupiedSquares.get(i).equals(squareDown)){
                            canTurnRight = false;
                            break;
                        }
                    }
                }
                if (canTurnRight) {
                    currentPiece.get().rotateRight();
                }
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
    public List<List<Integer>> getOccupiedSquares() {
        return occupiedSquares;
    }
}