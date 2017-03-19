package com.example.puma.assigment3;

import android.widget.ImageView;

import java.util.List;

public class Board {

    private int top;
    private int left;
    private int width;
    private int length;

    public Board(int boardResourceId, int[] startingPosition, int[] endPosition, int[][] map) {


    }

    public static Board MAP_1 = new Board(
            R.drawable.s101,
            new int[]{0, 1},
            new int[]{7, 5},
            new int[][]{
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {1, 1, 1, 1, 0, 0, 0, 0},
                    {0, 0, 0, 1, 0, 0, 0, 0},
                    {0, 0, 0, 1, 0, 0, 0, 0},
                    {0, 0, 0, 1, 1, 1, 1, 1},
                    {0, 0, 0, 0, 0, 0, 0, 0}
            }
    );

    public void setScreenDimensions(int top, int left, int width, int length) {
        this.top = top;
        this.left = left;
        this.width = width;
        this.length = length;
    }

    public boolean run(ImageView train, List<Direction> instructions) {
        if (width == 0 || left == 0)
            throw new RuntimeException("Screen dimensions are not set");

        return true;
    }

}
