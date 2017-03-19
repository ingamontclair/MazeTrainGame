package com.example.puma.assigment3;

import java.util.List;

public class Board {

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


    public boolean run (List<Direction> instructions) {
        return true;
    }

}
