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
Input
    Up arrow and X are to rotate 90° clockwise.
    Space to hard drop.
    Shift and C are to hold.
    Ctrl and Z are to rotate 90° counterclockwise.
    Esc and F1 are to pause.

UI
    Grid ten spaces across and twenty spaces down
    Display pieces
Gameplay
    Create tetrominoes
        Cyan I
        Yellow O
        Purple T
        Green S
        Red Z
        Blue J
        Orange L
        Randomly select one and add it to a queue
    Beginning at the top, they will:
        be in correct orientation and color
            The I and O spawn in the middle columns
            The rest spawn in the left-middle columns
            The tetriminoes spawn horizontally with J, L and T spawning flat-side first.
            Spawn above playfield, row 21 for I, and 21/22 for all other tetriminoes.
            Immediately drop one space if no existing Block is in its path
    Half second lock delay (pieces can still be moved on the ground for half a second)

Good things to also have
    Hold function
    Ghost piece display

Doing:
    First I'll try creating the pieces and allowing them to be translated through user input
    Actually I need the grid first to determine sizes
    So now how do I make the pieces?
        And I can't just make them all, I only want them to be added to the scene when they're called
        So maybe make a method which creates a new shape each time?
    Test making a piece, adding it to the scene and moving it
    Added the pieces, and they fall and can be moved left and right
    Need to add the rotation methods
    Then add detection so that pieces stop when they hit the bottom and then when they hit a piece which already stopped
    Also needs to make a new piece and set it to current piece when one stops
*/

public class TetrisApp extends Application {
    final static double WIDTH = 500;
    final static double HEIGHT = 700;
    @Override
    public void start(Stage stage) throws IOException {
        ArrayList<Line> grid = drawBoard();
        Pane pane = new Pane();
        for (Line line : grid) {
            pane.getChildren().add(line);
        }
        Pieces builder = new Pieces();
        Pieces currentPiece = builder.makePiece("S");
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