package com.example.puma.assigment3;

/**
 * Created by Inga on 3/21/2017.
 */

public class BoardConfiguration {

    /* Initial map setup
    * maps are 8 x 6
    * 0 - not passable, 1 - rail, 2 fork or turn
    * */

    public static final BoardConfiguration MAP_1 = new BoardConfiguration(
            R.drawable.s101,
            Direction.RIGHT,
            new int[]{1, 1},
            new int[]{7, 4},
            new int[][]{
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 1, 1, 2, 0, 0, 0, 0},
                    {0, 0, 0, 1, 0, 0, 0, 0},
                    {0, 0, 0, 1, 0, 0, 0, 0},
                    {0, 0, 0, 2, 1, 1, 1, 1},
                    {0, 0, 0, 0, 0, 0, 0, 0}
            }
    );

    public static final BoardConfiguration MAP_2 = new BoardConfiguration(
            R.drawable.s102,
            Direction.RIGHT,
            new int[]{2, 5},
            new int[]{6, 1},
            new int[][]{
                    {0, 0, 0, 0, 0, 0, 0, 0},
                    {0, 0, 0, 2, 1, 1, 1, 0},
                    {0, 0, 0, 1, 0, 0, 0, 0},
                    {0, 0, 0, 2, 1, 1, 2, 0},
                    {0, 0, 0, 0, 0, 0, 1, 1},
                    {0, 1, 1, 1, 1, 1, 2, 0}
            }
    );

    // int[] size is to get the actual board size in px
    // default height and width ( scales 1 ) is 1365 x 785 with the train 124x124
    // a square default size is 165 x 124
    public static final int BASE_SCREEN_WIDTH = 1365;
    public static final int BASE_SCREEN_HEIGHTS = 785;
    public static final int BASE_TRAIN_SIZE = 124;
    public static final int BASE_SQUARE_WIDTH = 165; //square height/width is not just div by 8 or 6, there is a small border around board
    public static final int BASE_SQUARE_HEIGHTS = 124;
    public static final int BASE_BORDER_WIDTH = 26;
    public static final int BASE_BORDER_HEIGHTS = 16;

    private int boardResouceId;
    private Direction orientation;
    private int[] startingPosition;
    private int[] endPosition;
    private int[][] map;

    private BoardConfiguration(){

    }

    public BoardConfiguration (int boardResouceId, Direction orientation, int[] startingPosition, int[] endPosition, int[][] map) {
        this.boardResouceId = boardResouceId;
        this.orientation = orientation;
        this.startingPosition = startingPosition;
        this.endPosition = endPosition;
        this.map = map;
    }

    public int getBoardResouceId() {
        return boardResouceId;
    }

    public void setBoardResouceId(int boardResouceId) {
        this.boardResouceId = boardResouceId;
    }

    public Direction getOrientation() {
        return orientation;
    }

    public void setOrientation(Direction orientation) {
        this.orientation = orientation;
    }

    public int[] getStartingPosition() {
        return startingPosition;
    }

    public void setStartingPosition(int[] startingPosition) {
        this.startingPosition = startingPosition;
    }

    public int[] getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(int[] endPosition) {
        this.endPosition = endPosition;
    }

    public int[][] getMap() {
        return map;
    }

    public void setMap(int[][] map) {
        this.map = map;
    }
}
