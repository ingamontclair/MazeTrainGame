package com.example.puma.assigment3;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static com.example.puma.assigment3.BoardConfiguration.BASE_BORDER_HEIGHTS;
import static com.example.puma.assigment3.BoardConfiguration.BASE_BORDER_WIDTH;
import static com.example.puma.assigment3.BoardConfiguration.BASE_SQUARE_HEIGHTS;
import static com.example.puma.assigment3.BoardConfiguration.BASE_SQUARE_WIDTH;
import static com.example.puma.assigment3.BoardConfiguration.BASE_TRAIN_SIZE;

public class Board {


    private final int[] size = new int[2];

    private BoardConfiguration conf;
    private RelativeLayout workingArea;
    private int[] currentPosition;


    private Direction movingDirection = null;
    private Queue<Direction> instructions;
    private TrainCrashListener trainCrashListener;

    public Board ( BoardConfiguration conf, RelativeLayout workingAreaView) {
        this.conf = conf;
        setWorkingArea( workingAreaView);
    }


    public void setWorkingArea(final RelativeLayout workingAreaView) {

        this.workingArea = workingAreaView;

        //get play area length and width
        ViewTreeObserver vto = workingAreaView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                workingAreaView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                size[0] = workingAreaView.getMeasuredWidth();
                size[1] = workingAreaView.getMeasuredHeight();
            }
        });

        workingAreaView.setBackgroundResource(conf.getBoardResouceId());
    }

    public void setTrainCrashListener(TrainCrashListener trainCrashListener) {
        this.trainCrashListener = trainCrashListener;
    }

    /*
        each instruction lasts until the next fork or turn
     */

    public void run(ImageView train, List<CharSequence> instructions) {

        if (workingArea == null)
            throw new RuntimeException("Screen is not set");

        if (trainCrashListener == null)
            throw new RuntimeException("Result listener is not set");

        assignInstructions(instructions);

        // show train at its starting position
        showStartingPosition(train);

        // move it
        nextMove(train);

    }

    private void assignInstructions(List<CharSequence> input) {
        this.instructions = new LinkedList<>();

        for (CharSequence c : input) {
            if (c == null)
                continue;

            instructions.add(Direction.valueOf(c.toString().toUpperCase()));
        }
    }

    private void showStartingPosition(ImageView train) {
        //coordinates + size + orientation

        workingArea.removeView(train);

        train.getLayoutParams().width = BASE_TRAIN_SIZE;
        train.getLayoutParams().height = BASE_TRAIN_SIZE;
        train.setScaleType(ImageView.ScaleType.FIT_CENTER);

        currentPosition = Arrays.copyOf(conf.getStartingPosition(), 2);
        train.setX(currentPosition[0] * BASE_SQUARE_WIDTH - BASE_TRAIN_SIZE/2 + BASE_BORDER_WIDTH);
        train.setY(currentPosition[1] * BASE_SQUARE_HEIGHTS + BASE_BORDER_HEIGHTS );

        train.setVisibility(View.VISIBLE);

        train.requestLayout();
        workingArea.addView(train);
    }


    private void nextMove(final ImageView train) {

        if (Arrays.equals(conf.getEndPosition(), currentPosition)) {
            //we have arrived to the finish
            trainCrashListener.onSuccess();
            return;
        }

        //the first move
        if (movingDirection == null) {
            //if our instructions are empty we are done
            if (instructions.isEmpty()) {
                trainCrashListener.onCrash();
            } else {
                Direction d = instructions.remove();
                if (checkDirection(d) > 0) {
                    //next move animation
                    movingDirection = d;
                    move(train, d);
                } else {
                    //todo: crash animation
                    trainCrashListener.onCrash();
                }
            }
        }
        // we are moving
        else {
            //check for turns
            if (conf.getMap()[currentPosition[1]][currentPosition[0]] > 1) {
                //it's a turn
                if (instructions.isEmpty()) {
                    trainCrashListener.onCrash();
                    return;
                }

                Direction d = instructions.remove();
                if (checkDirection(d) > 0) {
                    move(train, d);
                } else {
                    //todo: crash animation
                    trainCrashListener.onCrash();
                }
            } else if (checkDirection(movingDirection) > 0) {
                //keep moving if we have the room
                move(train, movingDirection);
            } else {
                trainCrashListener.onCrash();
            }
        }
    }


    private void move(final ImageView train, final Direction newDirection) {

        updateCurrentPosition(newDirection);
        //movingDirection + newDirection defines a turn
        if (Direction.RIGHT.equals(movingDirection) && Direction.RIGHT.equals(newDirection)) {

            ObjectAnimator anim = ObjectAnimator.ofFloat(train, View.X, train.getX(), train.getX() + BASE_SQUARE_WIDTH);
            anim.setInterpolator(new LinearInterpolator());
            anim.setDuration(1500);
            anim.addListener(new AnimationListenerAdapter() {
                                 @Override
                                 public void onAnimationEnd(Animator animation) {
                                         nextMove(train);
                                 }
                             }
            );
            anim.start();
        }
        else if (Direction.RIGHT.equals(movingDirection) && Direction.DOWN.equals(newDirection)){

            float x = train.getX();
            float y = train.getY();

            float[] valuesX = {x, x + BASE_TRAIN_SIZE/2 + (BASE_SQUARE_WIDTH - BASE_TRAIN_SIZE)/2};
            float[] valuesY = {y, y + BASE_SQUARE_HEIGHTS/2};
            float[] rotation = { 0, 90f };

            turnAnimation (train, newDirection, valuesX, valuesY, rotation);
        }
        else if (Direction.RIGHT.equals(movingDirection) && Direction.UP.equals(newDirection)){

            float x = train.getX();
            float y = train.getY();

            float[] valuesX = {x, x + BASE_TRAIN_SIZE/2 + (BASE_SQUARE_WIDTH - BASE_TRAIN_SIZE)/2};
            float[] valuesY = {y, y - BASE_SQUARE_HEIGHTS/2};
            float[] rotation = { 0, -90f };

            turnAnimation(train, newDirection, valuesX, valuesY, rotation);

        }
        else if (Direction.DOWN.equals(movingDirection) && Direction.RIGHT.equals(newDirection)){

            float x = train.getX();
            float y = train.getY();

            float[] valuesX = {x, x + BASE_TRAIN_SIZE/2 + (BASE_SQUARE_WIDTH - BASE_TRAIN_SIZE)/2};
            float[] valuesY = {y, y + BASE_SQUARE_HEIGHTS/2};
            float[] rotation = { 90f, 0};

            turnAnimation(train, newDirection, valuesX, valuesY, rotation);
        }
        else if (Direction.DOWN.equals(movingDirection) && Direction.DOWN.equals(newDirection)){

            ObjectAnimator anim = ObjectAnimator.ofFloat(train, View.Y, train.getY(), train.getY() + BASE_SQUARE_HEIGHTS);
            anim.setInterpolator(new LinearInterpolator());
            anim.setDuration(1500);
            anim.addListener(new AnimationListenerAdapter() {
                                 @Override
                                 public void onAnimationEnd(Animator animation) {
                                     nextMove(train);
                                 }
                             }
            );
            anim.start();
        }
        else if (Direction.UP.equals(movingDirection) && Direction.LEFT.equals(newDirection)){

            float x = train.getX();
            float y = train.getY();

            float[] valuesX = {x, x - (BASE_TRAIN_SIZE / 2 + (BASE_SQUARE_WIDTH - BASE_TRAIN_SIZE) / 2)};
            float[] valuesY = {y, y - BASE_SQUARE_HEIGHTS / 2};
            float[] rotation = {-90f, -180f};

            turnAnimation(train, newDirection, valuesX, valuesY, rotation);
        }
        else if (Direction.UP.equals(movingDirection) && Direction.UP.equals(newDirection)){

            ObjectAnimator anim = ObjectAnimator.ofFloat(train, View.Y, train.getY(), train.getY() - BASE_SQUARE_HEIGHTS);
            anim.setInterpolator(new LinearInterpolator());
            anim.setDuration(1500);
            anim.addListener(new AnimationListenerAdapter() {
                                 @Override
                                 public void onAnimationEnd(Animator animation) {
                                     nextMove(train);
                                 }
                             }
            );
            anim.start();
        }
        else if (Direction.LEFT.equals(movingDirection) && Direction.LEFT.equals(newDirection)){

            ObjectAnimator anim = ObjectAnimator.ofFloat(train, View.X, train.getX(), train.getX() - BASE_SQUARE_WIDTH);
            anim.setInterpolator(new LinearInterpolator());
            anim.setDuration(1500);
            anim.addListener(new AnimationListenerAdapter() {
                                 @Override
                                 public void onAnimationEnd(Animator animation) {
                                     nextMove(train);
                                 }
                             }
            );
            anim.start();
        }

    }

    private void turnAnimation(final ImageView train, Direction newDirection, float[] valuesX, float[] valuesY, float[] rotation) {

        movingDirection = newDirection;

        AnimatorSet animationSet = new AnimatorSet();
        animationSet.addListener(new AnimationListenerAdapter(){
            @Override
            public void onAnimationEnd(Animator animation) {
                nextMove(train);
            }
        });

        ObjectAnimator ax = ObjectAnimator.ofFloat(train, View.X, valuesX);
        ax.setDuration(1500);

        ObjectAnimator ay = ObjectAnimator.ofFloat(train, View.Y, valuesY);
        ay.setDuration(1500);

        ObjectAnimator ar = ObjectAnimator.ofFloat(train, "rotation", rotation[0], rotation[1]);
        ar.setDuration(1500);

        animationSet.play(ax).with(ay).with(ar);
        animationSet.setInterpolator(new LinearInterpolator());
        animationSet.start();
    }

    private int checkDirection(Direction d) {

        int result = 0;
        switch (d) {
            case RIGHT:
                // x+1, y
                result = (currentPosition[0] + 1 > 7) ? -1
                        : conf.getMap()[currentPosition[1]][currentPosition[0] + 1];
                break;
            case LEFT:
                // x-1, y
                result = ( currentPosition[0] -1 < 0) ? -1
                        : conf.getMap()[currentPosition[1]][currentPosition[0] - 1];
                break;
            case UP:
                // x, y  -1
                result = ( currentPosition[1] -1 < 0)? -1
                        : conf.getMap()[currentPosition[1]-1][currentPosition[0]];
                break;
            case DOWN:
                // x, y + 1
                result = ( currentPosition[1] + 1 > 5) ? -1
                        : conf.getMap()[currentPosition[1] + 1][currentPosition[0]] ;
                break;
        }

        return result;
    }

    private void updateCurrentPosition(Direction d) {

        switch (d) {
            case RIGHT:
                // x+1, y
                currentPosition[0] += 1 ;
                break;
            case LEFT:
                // x-1, y
                currentPosition[0] -= 1 ;
                break;
            case UP:
                // x, y  -1
                currentPosition[1] -= 1 ;
                break;
            case DOWN:
                // x, y + 1
                currentPosition[1] += 1 ;
                break;
        }
    }

    /*
    //temp
    private void moveRight2(final ImageView train){
        ObjectAnimator anim = ObjectAnimator.ofFloat( train, View.X, train.getX(), train.getX() + BASE_SQUARE_WIDTH );
        anim.setInterpolator(new LinearInterpolator());
        anim.setDuration(1500);
        anim.addListener(new AnimationListenerAdapter() {
                             @Override
                             public void onAnimationEnd(Animator animation) {
                                 turn3(train);
                             }
                         }
        );
        anim.start();
    }

    //temp
    private void turn3(final ImageView train) {

        AnimatorSet animationSet = new AnimatorSet();
        animationSet.addListener(new AnimationListenerAdapter(){
            @Override
            public void onAnimationEnd(Animator animation) {
            }
        });

        float x = train.getX();
        float y = train.getY();

        float[] valuesX = {x, x + BASE_TRAIN_SIZE/2 + (BASE_SQUARE_WIDTH - BASE_TRAIN_SIZE)/2};
        ObjectAnimator ax = ObjectAnimator.ofFloat(train, View.X, valuesX);
        ax.setDuration(1500);

        float[] valuesY = {y, y + BASE_SQUARE_HEIGHTS/2};
        ObjectAnimator ay = ObjectAnimator.ofFloat(train, View.Y, valuesY);
        ay.setDuration(1500);

        ObjectAnimator ar = ObjectAnimator.ofFloat(train, "rotation", 0, 90f);
        ar.setDuration(1500);

        animationSet.play(ax).with(ay).with(ar);
        animationSet.setInterpolator(new LinearInterpolator());
        animationSet.start();
    }

    */

}
