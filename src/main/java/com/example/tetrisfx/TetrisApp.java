package com.example.tetrisfx;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Predicate;
import static com.example.tetrisfx.Board.drawBoard;

/*
*/
public class TetrisApp extends Application {
    Pane pane = new Pane();
    List<List<Integer>> occupiedSquares = new ArrayList<>(); // 2D ArrayList which contains the coordinates of the occupied squares
    Group drawnSquares = new Group();
    List<Pieces> oldPieces = new ArrayList<>();
    int count = 0;
    boolean shouldClear = false;
    final static double WIDTH = 500;
    final static double HEIGHT = 700;
    @Override
    public void start(Stage stage) {
        ArrayList<Line> grid = drawBoard();
        String[] requests = {"O", "I", "S", "Z", "L", "J", "T"};
        int initial = (int) (Math.random() * 7); // used for the first piece

        // draw the grid
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

            if (code == KeyCode.RIGHT) {
                int[][] coordinates = currentPiece.get().getCoordinates();
                boolean canMoveRight = true;
                for (int i = 0; i < occupiedSquares.size() && canMoveRight; i++){
                    for (int[] coordinate : coordinates) {
                        List<Integer> square = Arrays.asList(coordinate[0] + 50, coordinate[1]);
                        if (occupiedSquares.get(i).equals(square)) {
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
                    for (int[] coordinate : coordinates) {
                        List<Integer> square = Arrays.asList(coordinate[0] - 50, coordinate[1]);
                        if (occupiedSquares.get(i).equals(square)) {
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
                boolean canTurnRight = coordinates[0][0] + 50 < 500 && coordinates[1][0] + 50 < 500 && coordinates[2][0] + 50 < 500 && coordinates[3][0] + 50 < 500;
                // a piece should not be able to rotate if its coordinates after turning would go beyond the grid
                if (coordinates[0][0] - 50 < 0  || coordinates[1][0] - 50 < 0  || coordinates[2][0] - 50 < 0 || coordinates[3][0] - 50 < 0){
                    canTurnRight = false;
                }

                // it also should not be able to rotate if any of its squares moved right/left, or up/down would lead to it being in the same position as a pre-existing piece
                for (int i = 0; i < occupiedSquares.size() && canTurnRight; i++){
                    for (int[] coordinate : coordinates) {
                        List<Integer> squareLeft = Arrays.asList(coordinate[0] - 50, coordinate[1]);
                        List<Integer> squareRight = Arrays.asList(coordinate[0] + 50, coordinate[1]);
                        List<Integer> squareUp = Arrays.asList(coordinate[0], coordinate[1] - 35);
                        List<Integer> squareDown = Arrays.asList(coordinate[0], coordinate[1] + 35);

                        // if any of the squares moved right/left, up/down, would lead to intersection, then it cannot rotate
                        if (occupiedSquares.get(i).equals(squareLeft) || occupiedSquares.get(i).equals(squareRight)
                                || occupiedSquares.get(i).equals(squareUp) || occupiedSquares.get(i).equals(squareDown)) {
                            canTurnRight = false;
                            break;
                        }
                    }
                }
                if (canTurnRight) { // otherwise it can rotate
                    currentPiece.get().rotateRight();
                }
            }
        });

        Timeline fall = new Timeline(
                new KeyFrame(Duration.seconds(0.75), event -> { // do every 0.75 seconds
                    if (!currentPiece.get().isStop()){
                        currentPiece.get().softDrop();
                    }
                })
        );
        // yeah, this many nested for-loops being done every 0.01 seconds seems like a very bad idea
        Timeline check = new Timeline(
                new KeyFrame(Duration.seconds(0.01), event -> { // doing so constantly seems like a bad idea
                    draw();
                    shouldClearLine();
                    if (shouldClear){
                        clearLine(); // this should move all occupiedSquares coordinates down 35
                    }
                    if (currentPiece.get().isStop()){
                        int[][] currentPieceCoordinates = currentPiece.get().getCoordinates(); // if a piece has stopped moving then add its coordinates to occupiedSquares
                        pane.getChildren().remove(currentPiece.get().getGroup());
                        for (int i = 0; i < 4; i++) {
                            List<Integer> square = new ArrayList<>();
                            square.add(currentPieceCoordinates[i][0]);
                            square.add(currentPieceCoordinates[i][1]);
                            occupiedSquares.add(square);
                        }
                        // make a new piece
                        oldPieces.add(currentPiece.get());
                        int random = (int) (Math.random() * 7); // random piece value
                        currentPiece.set(builder.makePiece(requests[random]));
                        pane.getChildren().add(currentPiece.get().getGroup()); // re-add current piece to the scene
                    }

                    // <Should a piece stop moving>
                    int[][] coordinates = currentPiece.get().getCoordinates();
                    for (List<Integer> occupiedSquare : occupiedSquares) {
                        for (int[] coordinate : coordinates) {
                            // if moving a piece down 35 would lead to it being in the same spot as a pre-existing piece
                            List<Integer> square = Arrays.asList(coordinate[0], coordinate[1] + 35);
                            if (occupiedSquare.equals(square)) {
                                currentPiece.get().setStop(true); // stop it before it gets to that point
                                count++;
                            }
                        }
                    }
                    // </Should a piece stop moving>
                })
        );
        fall.setCycleCount(Timeline.INDEFINITE); // does forever
        check.setCycleCount(Timeline.INDEFINITE);
        fall.play(); // makes them start
        check.play();
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public void draw(){
        // draws the occupiedSquares
        drawnSquares.getChildren().clear();
        for (List<Integer> square : occupiedSquares) {
            double x = square.get(0);
            double y = square.get(1);
            Rectangle block = new Rectangle(x, y, 50, 35);
            block.setFill(Color.WHEAT);
            drawnSquares.getChildren().add(block);
        }
        if (!pane.getChildren().contains(drawnSquares)) {
            pane.getChildren().add(drawnSquares);
        }
    }
    public void shouldClearLine() {
        Map<Integer, Integer> yCounts = new HashMap<>();
        for (List<Integer> square : occupiedSquares) {
            int y = square.get(1);
            yCounts.put(y, yCounts.getOrDefault(y, 0) + 1);
        }
        if (yCounts.values().stream().anyMatch(Predicate.isEqual(10))) {
            shouldClear = true;
        }
    }

    public void clearLine(){
        drawnSquares.getChildren().clear();
        List<List<Integer>> toRemove = new ArrayList<>();
        for (List<Integer> occupiedSquare : occupiedSquares) {
            if (occupiedSquare.get(1).equals(665)) {
                toRemove.add(occupiedSquare);
            }
        }
        occupiedSquares.removeAll(toRemove); // remove the squares which were on the bottom
        for (List<Integer> occupiedSquare : occupiedSquares) {
            occupiedSquare.set(1, occupiedSquare.get(1) + 35); // move each square down 35
        }
        shouldClear = false;
    }
}