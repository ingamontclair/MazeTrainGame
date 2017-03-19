package com.example.puma.assigment3;

import android.widget.ImageView;

import java.util.List;

public class Board {

    public static final Board MAP_1 = new Board(
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

    private int top;
    private int left;
    private int width;
    private int length;

    private int boardResouceId;
    private int[] startingPosition;
    private int[] endPosition;
    private int[][] map;


    public Board(int boardResouceId, int[] startingPosition, int[] endPosition, int[][] map) {
        this.boardResouceId = boardResouceId;
        this.startingPosition = startingPosition;
        this.endPosition = endPosition;
        this.map = map;
    }

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
