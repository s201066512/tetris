package com.example.tetrisfx;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Pieces {
    int[][] coordinates;
    Group blocks;
    String type;
    int orientation = 0; // orientation by default is 0
    boolean stop = false; // used to determine whether the piece should still be moving
    // if this value is true a new piece will be created and set to be the new currentPiece

    public Pieces(){
    }

    public Pieces(Group blocks, String type, int[][] coordinates){
        this.blocks = blocks;
        this.type = type;
        this.coordinates = coordinates;
    }

    // each cell is 50px wide and 35px tall
    // grid is 10 by 20
    // middle is 250
    public Pieces makePiece(String request){
        if (request.equals("O")){ // create new piece depending on the request
            // creates rectangles
            Rectangle block1 = new Rectangle(200,0,50,35);
            Rectangle block2 = new Rectangle(250,0,50,35);
            Rectangle block3 = new Rectangle(200,35,50,35);
            Rectangle block4 = new Rectangle(250,35,50,35);
            // colors the rectangles
            block1.setFill(Color.YELLOW);
            block2.setFill(Color.YELLOW);
            block3.setFill(Color.YELLOW);
            block4.setFill(Color.YELLOW);
            coordinates = new int[][]{{200, 0}, {250, 0}, {200, 35}, {250, 35}}; // adds coordinates to each rectangle
            Group squares = new Group(block1, block2, block3, block4); // creates a group of rectangles
            return new Pieces(squares, "O", coordinates); // return the new Pieces object
        }
        // repeat for each different shape
        if (request.equals("I")){
            Rectangle block1 = new Rectangle(150,0,50,35);
            Rectangle block2 = new Rectangle(200,0,50,35);
            Rectangle block3 = new Rectangle(250,0,50,35);
            Rectangle block4 = new Rectangle(300,0,50,35);
            block1.setFill(Color.CYAN);
            block2.setFill(Color.CYAN);
            block3.setFill(Color.CYAN);
            block4.setFill(Color.CYAN);
            coordinates = new int[][]{{150, 0}, {200, 0}, {250, 0}, {300, 0}};
            Group squares = new Group(block1, block2, block3, block4);
            return new Pieces(squares, "I", coordinates);
        }
        if (request.equals("S")){
            Rectangle block1 = new Rectangle(250,0,50,35);
            Rectangle block2 = new Rectangle(300,0,50,35);
            Rectangle block3 = new Rectangle(250,35,50,35);
            Rectangle block4 = new Rectangle(200,35,50,35);
            block1.setFill(Color.GREEN);
            block2.setFill(Color.GREEN);
            block3.setFill(Color.GREEN);
            block4.setFill(Color.GREEN);
            coordinates = new int[][]{{250, 0}, {300, 0}, {250, 35}, {200, 35}};
            Group squares = new Group(block1, block2, block3, block4);
            return new Pieces(squares, "S", coordinates);
        }
        if (request.equals("Z")){
            Rectangle block1 = new Rectangle(200,0,50,35);
            Rectangle block2 = new Rectangle(250,0,50,35);
            Rectangle block3 = new Rectangle(250,35,50,35);
            Rectangle block4 = new Rectangle(300,35,50,35);
            block1.setFill(Color.RED);
            block2.setFill(Color.RED);
            block3.setFill(Color.RED);
            block4.setFill(Color.RED);
            coordinates = new int[][]{{200, 0}, {250, 0}, {250, 35}, {300, 35}};
            Group squares = new Group(block1, block2, block3, block4);
            return new Pieces(squares, "Z", coordinates);
        }
        if (request.equals("L")){
            Rectangle block1 = new Rectangle(300,0,50,35);
            Rectangle block2 = new Rectangle(300,35,50,35);
            Rectangle block3 = new Rectangle(250,35,50,35);
            Rectangle block4 = new Rectangle(200,35,50,35);
            block1.setFill(Color.ORANGE);
            block2.setFill(Color.ORANGE);
            block3.setFill(Color.ORANGE);
            block4.setFill(Color.ORANGE);
            coordinates = new int[][]{{300, 0}, {300, 35}, {250, 35}, {200, 35}};
            Group squares = new Group(block1, block2, block3, block4);
            return new Pieces(squares, "L", coordinates);
        }
        if (request.equals("J")){
            Rectangle block1 = new Rectangle(200,0,50,35);
            Rectangle block2 = new Rectangle(200,35,50,35);
            Rectangle block3 = new Rectangle(250,35,50,35);
            Rectangle block4 = new Rectangle(300,35,50,35);
            block1.setFill(Color.BLUE);
            block2.setFill(Color.BLUE);
            block3.setFill(Color.BLUE);
            block4.setFill(Color.BLUE);
            coordinates = new int[][]{{200, 0}, {200, 35}, {250, 35}, {300, 35}};
            Group squares = new Group(block1, block2, block3, block4);
            return new Pieces(squares, "J", coordinates);
        }
        if (request.equals("T")){
            Rectangle block1 = new Rectangle(250,0,50,35);
            Rectangle block2 = new Rectangle(200,35,50,35);
            Rectangle block3 = new Rectangle(250,35,50,35);
            Rectangle block4 = new Rectangle(300,35,50,35);
            block1.setFill(Color.PURPLE);
            block2.setFill(Color.PURPLE);
            block3.setFill(Color.PURPLE);
            block4.setFill(Color.PURPLE);
            coordinates = new int[][]{{250, 0}, {200, 35}, {250, 35}, {300, 35}};
            Group squares = new Group(block1, block2, block3, block4);
            return new Pieces(squares, "T", coordinates);
        }
        return null;
    }
    public void moveRight(){
        // create coordinate values which are each 1 square to the right of the former x coordinate values
        int[] b1 = {coordinates[0][0] + 50, coordinates[0][1]};
        int[] b2 = {coordinates[1][0] + 50, coordinates[1][1]};
        int[] b3 = {coordinates[2][0] + 50, coordinates[2][1]};
        int[] b4 = {coordinates[3][0] + 50, coordinates[3][1]};
        if (b1[0] + 50 == 550 || b2[0] + 50 == 550 || b3[0] + 50 == 550 || b4[0] + 50 == 550){ // if any would go beyond the right "wall"
            // move them left to negate that
            b1[0] = b1[0] - 50;
            b2[0] = b2[0] - 50;
            b3[0] = b3[0] - 50;
            b4[0] = b4[0] - 50;
        }
        int[][] newCoordinates = {b1, b2, b3, b4}; // now create the new coordinate values
        setCoordinates(newCoordinates); // set the new coordinate values
        // apply the transforms
        Rectangle block1 = (Rectangle) blocks.getChildren().get(0);
        block1.setX(b1[0]);
        Rectangle block2 = (Rectangle) blocks.getChildren().get(1);
        block2.setX(b2[0]);
        Rectangle block3 = (Rectangle) blocks.getChildren().get(2);
        block3.setX(b3[0]);
        Rectangle block4 = (Rectangle) blocks.getChildren().get(3);
        block4.setX(b4[0]);
    }
    public void moveLeft(){
        // same as moveRight but going the other way
        int[] b1 = {coordinates[0][0] - 50, coordinates[0][1]};
        int[] b2 = {coordinates[1][0] - 50, coordinates[1][1]};
        int[] b3 = {coordinates[2][0] - 50, coordinates[2][1]};
        int[] b4 = {coordinates[3][0] - 50, coordinates[3][1]};
        if (b1[0] - 50 == -100 || b2[0] - 50 == -100 || b3[0] - 50 == -100  || b4[0] - 50 == -100 ){
            b1[0] = b1[0] + 50;
            b2[0] = b2[0] + 50;
            b3[0] = b3[0] + 50;
            b4[0] = b4[0] + 50;
        }
        int[][] newCoordinates = {b1, b2, b3, b4};
        setCoordinates(newCoordinates);
        Rectangle block1 = (Rectangle) blocks.getChildren().get(0);
        block1.setX(b1[0]);
        Rectangle block2 = (Rectangle) blocks.getChildren().get(1);
        block2.setX(b2[0]);
        Rectangle block3 = (Rectangle) blocks.getChildren().get(2);
        block3.setX(b3[0]);
        Rectangle block4 = (Rectangle) blocks.getChildren().get(3);
        block4.setX(b4[0]);
    }
    public void softDrop(){
        if (!stop){ // if the piece has not yet stopped
            // move its y coordinates one down
            int[] b1 = {coordinates[0][0], coordinates[0][1] + 35};
            int[] b2 = {coordinates[1][0], coordinates[1][1] + 35};
            int[] b3 = {coordinates[2][0], coordinates[2][1] + 35};
            int[] b4 = {coordinates[3][0], coordinates[3][1] + 35};
            int[][] newCoordinates = {b1, b2, b3, b4};
            setCoordinates(newCoordinates);
            int lowestY = Math.max(b1[1], Math.max(b2[1], Math.max(b3[1], b4[1]))); // get the lowest y value
            if (lowestY < 665){ // if that y value is less than 665
                // move it down
                Rectangle block1 = (Rectangle) blocks.getChildren().get(0);
                block1.setY(b1[1]);
                Rectangle block2 = (Rectangle) blocks.getChildren().get(1);
                block2.setY(b2[1]);
                Rectangle block3 = (Rectangle) blocks.getChildren().get(2);
                block3.setY(b3[1]);
                Rectangle block4 = (Rectangle) blocks.getChildren().get(3);
                block4.setY(b4[1]);
            }
            else{
                // otherwise, stop it
                stop = true;
                // and to be honest I'm not sure if the setX and setY methods are even necessary here
                Rectangle block1 = (Rectangle) blocks.getChildren().get(0);
                block1.setX(newCoordinates[0][0]);
                block1.setY(newCoordinates[0][1]);
                Rectangle block2 = (Rectangle) blocks.getChildren().get(1);
                block2.setX(newCoordinates[1][0]);
                block2.setY(newCoordinates[1][1]);
                Rectangle block3 = (Rectangle) blocks.getChildren().get(2);
                block3.setX(newCoordinates[2][0]);
                block3.setY(newCoordinates[2][1]);
                Rectangle block4 = (Rectangle) blocks.getChildren().get(3);
                block4.setX(newCoordinates[3][0]);
                block4.setY(newCoordinates[3][1]);
            }
        }
    }

    /*
    The general idea behind each of these movement methods is to:
    - create new coordinates where you want the piece to be after
    - update the piece's coordinates to the new ones
    - move the rectangles by the values of the new coordinates
    */
    public void rotateRight() {
        if (type.equals("I")) {
            if (orientation == 0){ // each rotation depends on the orientation
                // each transform here depends on the piece
                // but from then on it's the same for any of the other movement methods
                int[] b1 = {coordinates[0][0] + 50, coordinates[0][1] + 35};
                int[] b2 = {coordinates[1][0], coordinates[1][1]};
                int[] b3 = {coordinates[2][0] - 50, coordinates[2][1] - 35};
                int[] b4 = {coordinates[3][0] - 100, coordinates[3][1] + 70};
                int[][] newCoordinates = {b1, b2, b3, b4};
                setCoordinates(newCoordinates);
                Rectangle block1 = (Rectangle) blocks.getChildren().get(0);
                block1.setX(newCoordinates[0][0]);
                block1.setY(newCoordinates[0][1]);
                Rectangle block2 = (Rectangle) blocks.getChildren().get(1);
                block2.setX(newCoordinates[1][0]);
                block2.setY(newCoordinates[1][1]);
                Rectangle block3 = (Rectangle) blocks.getChildren().get(2);
                block3.setX(newCoordinates[2][0]);
                block3.setY(newCoordinates[2][1]);
                Rectangle block4 = (Rectangle) blocks.getChildren().get(3);
                block4.setX(newCoordinates[3][0]);
                block4.setY(newCoordinates[3][1]);
                orientation = 1;
            }
            else{
                int[] b1 = {coordinates[0][0] - 50, coordinates[0][1] - 35};
                int[] b2 = {coordinates[1][0], coordinates[1][1]};
                int[] b3 = {coordinates[2][0] + 50, coordinates[2][1] + 35};
                int[] b4 = {coordinates[3][0] + 100, coordinates[3][1] - 70};
                int[][] newCoordinates = {b1, b2, b3, b4};
                setCoordinates(newCoordinates);
                Rectangle block1 = (Rectangle) blocks.getChildren().get(0);
                block1.setX(newCoordinates[0][0]);
                block1.setY(newCoordinates[0][1]);
                Rectangle block2 = (Rectangle) blocks.getChildren().get(1);
                block2.setX(newCoordinates[1][0]);
                block2.setY(newCoordinates[1][1]);
                Rectangle block3 = (Rectangle) blocks.getChildren().get(2);
                block3.setX(newCoordinates[2][0]);
                block3.setY(newCoordinates[2][1]);
                Rectangle block4 = (Rectangle) blocks.getChildren().get(3);
                block4.setX(newCoordinates[3][0]);
                block4.setY(newCoordinates[3][1]);
                orientation = 0;
            }
        }
        if (type.equals("S")) {
            if (orientation == 0){
                int[] b1 = {coordinates[0][0], coordinates[0][1]};
                int[] b2 = {coordinates[1][0], coordinates[1][1] + 35};
                int[] b3 = {coordinates[2][0], coordinates[2][1]};
                int[] b4 = {coordinates[3][0] + 100, coordinates[3][1] + 35};
                int[][] newCoordinates = {b1, b2, b3, b4};
                setCoordinates(newCoordinates);
                Rectangle block1 = (Rectangle) blocks.getChildren().get(0);
                block1.setX(newCoordinates[0][0]);
                block1.setY(newCoordinates[0][1]);
                Rectangle block2 = (Rectangle) blocks.getChildren().get(1);
                block2.setX(newCoordinates[1][0]);
                block2.setY(newCoordinates[1][1]);
                Rectangle block3 = (Rectangle) blocks.getChildren().get(2);
                block3.setX(newCoordinates[2][0]);
                block3.setY(newCoordinates[2][1]);
                Rectangle block4 = (Rectangle) blocks.getChildren().get(3);
                block4.setX(newCoordinates[3][0]);
                block4.setY(newCoordinates[3][1]);
                orientation = 1;
            }
            else{
                int[] b1 = {coordinates[0][0], coordinates[0][1]};
                int[] b2 = {coordinates[1][0], coordinates[1][1] - 35};
                int[] b3 = {coordinates[2][0], coordinates[2][1]};
                int[] b4 = {coordinates[3][0] - 100, coordinates[3][1] - 35};
                int[][] newCoordinates = {b1, b2, b3, b4};
                setCoordinates(newCoordinates);
                Rectangle block1 = (Rectangle) blocks.getChildren().get(0);
                block1.setX(newCoordinates[0][0]);
                block1.setY(newCoordinates[0][1]);
                Rectangle block2 = (Rectangle) blocks.getChildren().get(1);
                block2.setX(newCoordinates[1][0]);
                block2.setY(newCoordinates[1][1]);
                Rectangle block3 = (Rectangle) blocks.getChildren().get(2);
                block3.setX(newCoordinates[2][0]);
                block3.setY(newCoordinates[2][1]);
                Rectangle block4 = (Rectangle) blocks.getChildren().get(3);
                block4.setX(newCoordinates[3][0]);
                block4.setY(newCoordinates[3][1]);
                orientation = 0;
            }
        }
        if (type.equals("Z")) {
            if (orientation == 0){
                int[] b1 = {coordinates[0][0] + 100, coordinates[0][1]};
                int[] b2 = {coordinates[1][0], coordinates[1][1] + 70};
                int[] b3 = {coordinates[2][0], coordinates[2][1]};
                int[] b4 = {coordinates[3][0], coordinates[3][1]};
                int[][] newCoordinates = {b1, b2, b3, b4};
                setCoordinates(newCoordinates);
                Rectangle block1 = (Rectangle) blocks.getChildren().get(0);
                block1.setX(newCoordinates[0][0]);
                block1.setY(newCoordinates[0][1]);
                Rectangle block2 = (Rectangle) blocks.getChildren().get(1);
                block2.setX(newCoordinates[1][0]);
                block2.setY(newCoordinates[1][1]);
                Rectangle block3 = (Rectangle) blocks.getChildren().get(2);
                block3.setX(newCoordinates[2][0]);
                block3.setY(newCoordinates[2][1]);
                Rectangle block4 = (Rectangle) blocks.getChildren().get(3);
                block4.setX(newCoordinates[3][0]);
                block4.setY(newCoordinates[3][1]);
                orientation = 1;
            }
            else{
                int[] b1 = {coordinates[0][0] - 100, coordinates[0][1]};
                int[] b2 = {coordinates[1][0], coordinates[1][1] - 70};
                int[] b3 = {coordinates[2][0], coordinates[2][1]};
                int[] b4 = {coordinates[3][0], coordinates[3][1]};
                int[][] newCoordinates = {b1, b2, b3, b4};
                setCoordinates(newCoordinates);
                Rectangle block1 = (Rectangle) blocks.getChildren().get(0);
                block1.setX(newCoordinates[0][0]);
                block1.setY(newCoordinates[0][1]);
                Rectangle block2 = (Rectangle) blocks.getChildren().get(1);
                block2.setX(newCoordinates[1][0]);
                block2.setY(newCoordinates[1][1]);
                Rectangle block3 = (Rectangle) blocks.getChildren().get(2);
                block3.setX(newCoordinates[2][0]);
                block3.setY(newCoordinates[2][1]);
                Rectangle block4 = (Rectangle) blocks.getChildren().get(3);
                block4.setX(newCoordinates[3][0]);
                block4.setY(newCoordinates[3][1]);
                orientation = 0;
            }
        }
        if (type.equals("L")) {
            if (orientation == 0){
                int[] b1 = {coordinates[0][0] - 50, coordinates[0][1]};
                int[] b2 = {coordinates[1][0], coordinates[1][1] + 35};
                int[] b3 = {coordinates[2][0], coordinates[2][1]};
                int[] b4 = {coordinates[3][0] + 50, coordinates[3][1] + 35};
                int[][] newCoordinates = {b1, b2, b3, b4};
                setCoordinates(newCoordinates);
                Rectangle block1 = (Rectangle) blocks.getChildren().get(0);
                block1.setX(newCoordinates[0][0]);
                block1.setY(newCoordinates[0][1]);
                Rectangle block2 = (Rectangle) blocks.getChildren().get(1);
                block2.setX(newCoordinates[1][0]);
                block2.setY(newCoordinates[1][1]);
                Rectangle block3 = (Rectangle) blocks.getChildren().get(2);
                block3.setX(newCoordinates[2][0]);
                block3.setY(newCoordinates[2][1]);
                Rectangle block4 = (Rectangle) blocks.getChildren().get(3);
                block4.setX(newCoordinates[3][0]);
                block4.setY(newCoordinates[3][1]);
                orientation = 1;
            }
            else if (orientation == 1){
                int[] b1 = {coordinates[0][0] - 50, coordinates[0][1] + 35};
                int[] b2 = {coordinates[1][0], coordinates[1][1] - 35};
                int[] b3 = {coordinates[2][0], coordinates[2][1]};
                int[] b4 = {coordinates[3][0] - 50, coordinates[3][1]};
                int[][] newCoordinates = {b1, b2, b3, b4};
                setCoordinates(newCoordinates);
                Rectangle block1 = (Rectangle) blocks.getChildren().get(0);
                block1.setX(newCoordinates[0][0]);
                block1.setY(newCoordinates[0][1]);
                Rectangle block2 = (Rectangle) blocks.getChildren().get(1);
                block2.setX(newCoordinates[1][0]);
                block2.setY(newCoordinates[1][1]);
                Rectangle block3 = (Rectangle) blocks.getChildren().get(2);
                block3.setX(newCoordinates[2][0]);
                block3.setY(newCoordinates[2][1]);
                Rectangle block4 = (Rectangle) blocks.getChildren().get(3);
                block4.setX(newCoordinates[3][0]);
                block4.setY(newCoordinates[3][1]);
                orientation = 2;
            }
            else if (orientation == 2){
                int[] b1 = {coordinates[0][0], coordinates[0][1] - 35};
                int[] b2 = {coordinates[1][0] - 50, coordinates[1][1] - 35};
                int[] b3 = {coordinates[2][0], coordinates[2][1]};
                int[] b4 = {coordinates[3][0] + 50, coordinates[3][1]};
                int[][] newCoordinates = {b1, b2, b3, b4};
                setCoordinates(newCoordinates);
                Rectangle block1 = (Rectangle) blocks.getChildren().get(0);
                block1.setX(newCoordinates[0][0]);
                block1.setY(newCoordinates[0][1]);
                Rectangle block2 = (Rectangle) blocks.getChildren().get(1);
                block2.setX(newCoordinates[1][0]);
                block2.setY(newCoordinates[1][1]);
                Rectangle block3 = (Rectangle) blocks.getChildren().get(2);
                block3.setX(newCoordinates[2][0]);
                block3.setY(newCoordinates[2][1]);
                Rectangle block4 = (Rectangle) blocks.getChildren().get(3);
                block4.setX(newCoordinates[3][0]);
                block4.setY(newCoordinates[3][1]);
                orientation = 3;
            }
            else if (orientation == 3){
                int[] b1 = {coordinates[0][0] + 100, coordinates[0][1]};
                int[] b2 = {coordinates[1][0] + 50, coordinates[1][1] + 35};
                int[] b3 = {coordinates[2][0], coordinates[2][1]};
                int[] b4 = {coordinates[3][0] - 50, coordinates[3][1] - 35};
                int[][] newCoordinates = {b1, b2, b3, b4};
                setCoordinates(newCoordinates);
                Rectangle block1 = (Rectangle) blocks.getChildren().get(0);
                block1.setX(newCoordinates[0][0]);
                block1.setY(newCoordinates[0][1]);
                Rectangle block2 = (Rectangle) blocks.getChildren().get(1);
                block2.setX(newCoordinates[1][0]);
                block2.setY(newCoordinates[1][1]);
                Rectangle block3 = (Rectangle) blocks.getChildren().get(2);
                block3.setX(newCoordinates[2][0]);
                block3.setY(newCoordinates[2][1]);
                Rectangle block4 = (Rectangle) blocks.getChildren().get(3);
                block4.setX(newCoordinates[3][0]);
                block4.setY(newCoordinates[3][1]);
                orientation = 0;
            }
        }
        if (type.equals("J")) {
            if (orientation == 0){
                int[] b1 = {coordinates[0][0] + 50, coordinates[0][1]};
                int[] b2 = {coordinates[1][0] + 50, coordinates[1][1] + 35};
                int[] b3 = {coordinates[2][0], coordinates[2][1]};
                int[] b4 = {coordinates[3][0], coordinates[3][1] - 35};
                int[][] newCoordinates = {b1, b2, b3, b4};
                setCoordinates(newCoordinates);
                Rectangle block1 = (Rectangle) blocks.getChildren().get(0);
                block1.setX(newCoordinates[0][0]);
                block1.setY(newCoordinates[0][1]);
                Rectangle block2 = (Rectangle) blocks.getChildren().get(1);
                block2.setX(newCoordinates[1][0]);
                block2.setY(newCoordinates[1][1]);
                Rectangle block3 = (Rectangle) blocks.getChildren().get(2);
                block3.setX(newCoordinates[2][0]);
                block3.setY(newCoordinates[2][1]);
                Rectangle block4 = (Rectangle) blocks.getChildren().get(3);
                block4.setX(newCoordinates[3][0]);
                block4.setY(newCoordinates[3][1]);
                orientation = 1;
            }
            else if (orientation == 1){
                int[] b1 = {coordinates[0][0] - 50, coordinates[0][1] + 35};
                int[] b2 = {coordinates[1][0] + 50, coordinates[1][1]};
                int[] b3 = {coordinates[2][0], coordinates[2][1]};
                int[] b4 = {coordinates[3][0], coordinates[3][1] + 35};
                int[][] newCoordinates = {b1, b2, b3, b4};
                setCoordinates(newCoordinates);
                Rectangle block1 = (Rectangle) blocks.getChildren().get(0);
                block1.setX(newCoordinates[0][0]);
                block1.setY(newCoordinates[0][1]);
                Rectangle block2 = (Rectangle) blocks.getChildren().get(1);
                block2.setX(newCoordinates[1][0]);
                block2.setY(newCoordinates[1][1]);
                Rectangle block3 = (Rectangle) blocks.getChildren().get(2);
                block3.setX(newCoordinates[2][0]);
                block3.setY(newCoordinates[2][1]);
                Rectangle block4 = (Rectangle) blocks.getChildren().get(3);
                block4.setX(newCoordinates[3][0]);
                block4.setY(newCoordinates[3][1]);
                orientation = 2;
            }
            else if (orientation == 2){
                int[] b1 = {coordinates[0][0] + 50, coordinates[0][1] - 35};
                int[] b2 = {coordinates[1][0] - 100, coordinates[1][1]};
                int[] b3 = {coordinates[2][0], coordinates[2][1]};
                int[] b4 = {coordinates[3][0] - 50, coordinates[3][1] + 35};
                int[][] newCoordinates = {b1, b2, b3, b4};
                setCoordinates(newCoordinates);
                Rectangle block1 = (Rectangle) blocks.getChildren().get(0);
                block1.setX(newCoordinates[0][0]);
                block1.setY(newCoordinates[0][1]);
                Rectangle block2 = (Rectangle) blocks.getChildren().get(1);
                block2.setX(newCoordinates[1][0]);
                block2.setY(newCoordinates[1][1]);
                Rectangle block3 = (Rectangle) blocks.getChildren().get(2);
                block3.setX(newCoordinates[2][0]);
                block3.setY(newCoordinates[2][1]);
                Rectangle block4 = (Rectangle) blocks.getChildren().get(3);
                block4.setX(newCoordinates[3][0]);
                block4.setY(newCoordinates[3][1]);
                orientation = 3;
            }
            else if (orientation == 3){
                int[] b1 = {coordinates[0][0] - 50, coordinates[0][1]};
                int[] b2 = {coordinates[1][0], coordinates[1][1] - 35};
                int[] b3 = {coordinates[2][0], coordinates[2][1]};
                int[] b4 = {coordinates[3][0] + 50, coordinates[3][1] - 35};
                int[][] newCoordinates = {b1, b2, b3, b4};
                setCoordinates(newCoordinates);
                Rectangle block1 = (Rectangle) blocks.getChildren().get(0);
                block1.setX(newCoordinates[0][0]);
                block1.setY(newCoordinates[0][1]);
                Rectangle block2 = (Rectangle) blocks.getChildren().get(1);
                block2.setX(newCoordinates[1][0]);
                block2.setY(newCoordinates[1][1]);
                Rectangle block3 = (Rectangle) blocks.getChildren().get(2);
                block3.setX(newCoordinates[2][0]);
                block3.setY(newCoordinates[2][1]);
                Rectangle block4 = (Rectangle) blocks.getChildren().get(3);
                block4.setX(newCoordinates[3][0]);
                block4.setY(newCoordinates[3][1]);
                orientation = 0;
            }
        }
        if (type.equals("T")) {
            if (orientation == 0){
                int[] b1 = {coordinates[0][0], coordinates[0][1]};
                int[] b2 = {coordinates[1][0] + 50, coordinates[1][1] + 35};
                int[] b3 = {coordinates[2][0], coordinates[2][1]};
                int[] b4 = {coordinates[3][0], coordinates[3][1]};
                int[][] newCoordinates = {b1, b2, b3, b4};
                setCoordinates(newCoordinates);
                Rectangle block1 = (Rectangle) blocks.getChildren().get(0);
                block1.setX(newCoordinates[0][0]);
                block1.setY(newCoordinates[0][1]);
                Rectangle block2 = (Rectangle) blocks.getChildren().get(1);
                block2.setX(newCoordinates[1][0]);
                block2.setY(newCoordinates[1][1]);
                Rectangle block3 = (Rectangle) blocks.getChildren().get(2);
                block3.setX(newCoordinates[2][0]);
                block3.setY(newCoordinates[2][1]);
                Rectangle block4 = (Rectangle) blocks.getChildren().get(3);
                block4.setX(newCoordinates[3][0]);
                block4.setY(newCoordinates[3][1]);
                orientation = 1;
            }
            else if (orientation == 1){
                int[] b1 = {coordinates[0][0] - 50, coordinates[0][1] + 35};
                int[] b2 = {coordinates[1][0], coordinates[1][1]};
                int[] b3 = {coordinates[2][0], coordinates[2][1]};
                int[] b4 = {coordinates[3][0], coordinates[3][1]};
                int[][] newCoordinates = {b1, b2, b3, b4};
                setCoordinates(newCoordinates);
                Rectangle block1 = (Rectangle) blocks.getChildren().get(0);
                block1.setX(newCoordinates[0][0]);
                block1.setY(newCoordinates[0][1]);
                Rectangle block2 = (Rectangle) blocks.getChildren().get(1);
                block2.setX(newCoordinates[1][0]);
                block2.setY(newCoordinates[1][1]);
                Rectangle block3 = (Rectangle) blocks.getChildren().get(2);
                block3.setX(newCoordinates[2][0]);
                block3.setY(newCoordinates[2][1]);
                Rectangle block4 = (Rectangle) blocks.getChildren().get(3);
                block4.setX(newCoordinates[3][0]);
                block4.setY(newCoordinates[3][1]);
                orientation = 2;
            }
            else if (orientation == 2){
                int[] b1 = {coordinates[0][0], coordinates[0][1]};
                int[] b2 = {coordinates[1][0], coordinates[1][1]};
                int[] b3 = {coordinates[2][0], coordinates[2][1]};
                int[] b4 = {coordinates[3][0] - 50, coordinates[3][1] - 35};
                int[][] newCoordinates = {b1, b2, b3, b4};
                setCoordinates(newCoordinates);
                Rectangle block1 = (Rectangle) blocks.getChildren().get(0);
                block1.setX(newCoordinates[0][0]);
                block1.setY(newCoordinates[0][1]);
                Rectangle block2 = (Rectangle) blocks.getChildren().get(1);
                block2.setX(newCoordinates[1][0]);
                block2.setY(newCoordinates[1][1]);
                Rectangle block3 = (Rectangle) blocks.getChildren().get(2);
                block3.setX(newCoordinates[2][0]);
                block3.setY(newCoordinates[2][1]);
                Rectangle block4 = (Rectangle) blocks.getChildren().get(3);
                block4.setX(newCoordinates[3][0]);
                block4.setY(newCoordinates[3][1]);
                orientation = 3;
            }
            else if (orientation == 3){
                int[] b1 = {coordinates[0][0] + 50, coordinates[0][1] - 35};
                int[] b2 = {coordinates[1][0] - 50, coordinates[1][1] - 35};
                int[] b3 = {coordinates[2][0], coordinates[2][1]};
                int[] b4 = {coordinates[3][0] + 50, coordinates[3][1] + 35};
                int[][] newCoordinates = {b1, b2, b3, b4};
                setCoordinates(newCoordinates);
                Rectangle block1 = (Rectangle) blocks.getChildren().get(0);
                block1.setX(newCoordinates[0][0]);
                block1.setY(newCoordinates[0][1]);
                Rectangle block2 = (Rectangle) blocks.getChildren().get(1);
                block2.setX(newCoordinates[1][0]);
                block2.setY(newCoordinates[1][1]);
                Rectangle block3 = (Rectangle) blocks.getChildren().get(2);
                block3.setX(newCoordinates[2][0]);
                block3.setY(newCoordinates[2][1]);
                Rectangle block4 = (Rectangle) blocks.getChildren().get(3);
                block4.setX(newCoordinates[3][0]);
                block4.setY(newCoordinates[3][1]);
                orientation = 0;
            }
        }
    }

    public Group getGroup(){
        return this.blocks;
    }

    public boolean isStop() {
        return stop;
    }
    public void setStop(boolean boo){
        stop = boo;
    }
    public int[][] getCoordinates() {
        return coordinates;
    }

    public String getType() {
        return type;
    }

    public void setCoordinates(int[][] newCoordinates) {
        this.coordinates = newCoordinates;
    }
}
