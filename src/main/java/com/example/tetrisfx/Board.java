package com.example.tetrisfx;

import javafx.scene.shape.Line;

import java.util.ArrayList;

public class Board {
    final static double WIDTH = 500;
    final static double HEIGHT = 700;
    public static ArrayList<Line> drawBoard(){
        ArrayList<Line> grid = new ArrayList<>();
        final int COLS = 10;
        final int ROWS = 20;
        for(int i = 0; i < 10; i++){
            Line column = new Line((double) WIDTH /10 * i,0, (double) WIDTH /10 * i,1000);
            grid.add(column);
        }
        for (int i = 0; i < 20; i++){
            Line row = new Line(0, (double) HEIGHT /20 * i, 1000, (double) HEIGHT /20 * i);
            grid.add(row);
        }
        return grid;
    }
}
