package com.example.tetrisfx;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
/*
Create rectangles depending on the request
    1 will be O, 2 will be I, 3 will be T, 4 will be L, 5 will be S, 6 will be Z
Just create the rectangle and then center it after

Every move needs to be checked (i.e. No illegal moves)
    Make a method which is used and checks that the new position of the tetriminoe isn't intersecting an existing one

Represent each piece with a group of rectangles

Have assign each piece a current position (what row and column)
    Add these variables into the constructor
So process for making a shape so far
    Pieces OBlock = new Pieces(getOblock(), 0, 0);
    After making a piece all my transforms will use the setColumn and setRow methods

*/
public class Pieces {
    int[][] coordinates;
    Group blocks;
    String type;
    public Pieces(){}

    public Pieces(Group blocks, String type, int[][] coordinates){
        this.coordinates = coordinates;
        this.blocks = blocks;
        type = this.type;
    }

    // each cell is 50px wide and 35px tall
    // grid is 10 by 20
    // middle is 250
    public Pieces makePiece(String request){
        if (request.equals("O")){
            Rectangle block1 = new Rectangle(200,0,50,35);
            Rectangle block2 = new Rectangle(250,0,50,35);
            Rectangle block3 = new Rectangle(200,35,50,35);
            Rectangle block4 = new Rectangle(250,35,50,35);
            block1.setFill(Color.YELLOW);
            block2.setFill(Color.YELLOW);
            block3.setFill(Color.YELLOW);
            block4.setFill(Color.YELLOW);
            coordinates = new int[][]{{200, 0}, {250, 0}, {200, 35}, {250, 35}};
            Group squares = new Group(block1, block2, block3, block4);
            return new Pieces(squares, "O", coordinates);
        }
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
        int[] b1X = {coordinates[0][0] + 50, coordinates[0][1]};
        int[] b2X = {coordinates[1][0] + 50, coordinates[1][1]};
        int[] b3X = {coordinates[2][0] + 50, coordinates[2][1]};
        int[] b4X = {coordinates[3][0] + 50, coordinates[3][1]};
        int[][] newCoordinates = {b1X, b2X, b3X, b4X};
        Rectangle b1 = (Rectangle) blocks.getChildren().get(0);
        b1.setX(b1X[0]);
        Rectangle b2 = (Rectangle) blocks.getChildren().get(1);
        b2.setX(b2X[0]);
        Rectangle b3 = (Rectangle) blocks.getChildren().get(2);
        b3.setX(b3X[0]);
        Rectangle b4 = (Rectangle) blocks.getChildren().get(3);
        b4.setX(b4X[0]);
        setCoordinates(newCoordinates);
    }
    public void moveLeft(){
        int[] b1X = {coordinates[0][0] - 50, coordinates[0][1]};
        int[] b2X = {coordinates[1][0] - 50, coordinates[1][1]};
        int[] b3X = {coordinates[2][0] - 50, coordinates[2][1]};
        int[] b4X = {coordinates[3][0] - 50, coordinates[3][1]};
        int[][] newCoordinates = {b1X, b2X, b3X, b4X};
        Rectangle b1 = (Rectangle) blocks.getChildren().get(0);
        b1.setX(b1X[0]);
        Rectangle b2 = (Rectangle) blocks.getChildren().get(1);
        b2.setX(b2X[0]);
        Rectangle b3 = (Rectangle) blocks.getChildren().get(2);
        b3.setX(b3X[0]);
        Rectangle b4 = (Rectangle) blocks.getChildren().get(3);
        b4.setX(b4X[0]);
        setCoordinates(newCoordinates);
    }
    public void softDrop(){
        int[] b1Y = {coordinates[0][0], coordinates[0][1] + 35};
        int[] b2Y = {coordinates[1][0], coordinates[1][1] + 35};
        int[] b3Y = {coordinates[2][0], coordinates[2][1] + 35};
        int[] b4Y = {coordinates[3][0], coordinates[3][1] + 35};
        int[][] newCoordinates = {b1Y, b2Y, b3Y, b4Y};
        Rectangle b1 = (Rectangle) blocks.getChildren().get(0);
        b1.setY(b1Y[1]);
        Rectangle b2 = (Rectangle) blocks.getChildren().get(1);
        b2.setY(b2Y[1]);
        Rectangle b3 = (Rectangle) blocks.getChildren().get(2);
        b3.setY(b3Y[1]);
        Rectangle b4 = (Rectangle) blocks.getChildren().get(3);
        b4.setY(b4Y[1]);
        setCoordinates(newCoordinates);
    }

    public void falling() {
        long start = System.currentTimeMillis();
        int[] b1Y = {coordinates[0][0], coordinates[0][1] + 35};
        int[] b2Y = {coordinates[1][0], coordinates[1][1] + 35};
        int[] b3Y = {coordinates[2][0], coordinates[2][1] + 35};
        int[] b4Y = {coordinates[3][0], coordinates[3][1] + 35};
        int[][] newCoordinates = {b1Y, b2Y, b3Y, b4Y};
        Rectangle b1 = (Rectangle) blocks.getChildren().get(0);
        b1.setY(b1Y[1]);
        Rectangle b2 = (Rectangle) blocks.getChildren().get(1);
        b2.setY(b2Y[1]);
        Rectangle b3 = (Rectangle) blocks.getChildren().get(2);
        b3.setY(b3Y[1]);
        Rectangle b4 = (Rectangle) blocks.getChildren().get(3);
        b4.setY(b4Y[1]);
        setCoordinates(newCoordinates);
    }
    public Group getGroup(){
        return this.blocks;
    }

    public int[][] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(int[][] newCoordinates) {
        this.coordinates = newCoordinates;
    }
}
