package com.example.puma.assigment3;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.graphics.Path;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.List;

public class Board {

    public static final Board MAP_1 = new Board(
            R.drawable.s101,
            new int[]{1, 1},
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

    private final int[] size = new int[2];
    private RelativeLayout workingArea;

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


    public boolean run(ImageView train, List<String> instructions) {
        if (workingArea == null)
            throw new RuntimeException("Screen is not set");

        // show train at starting position
        showStartingPosition(train);
        moveRight(train);

        return true;
    }

    private void moveRight(final ImageView train){
        ObjectAnimator anim = ObjectAnimator.ofFloat( train, View.X, train.getX(), train.getX() + 172 );
        anim.setInterpolator(new LinearInterpolator());
        anim.setDuration(1500);
        anim.addListener(new AnimationListenerAdapter() {
                             @Override
                             public void onAnimationEnd(Animator animation) {
                                 moveRight2(train);
                             }
                         }
        );
        anim.start();
    }

    private void moveRight2(final ImageView train){
        ObjectAnimator anim = ObjectAnimator.ofFloat( train, View.X, train.getX(), train.getX() + 172 );
        anim.setInterpolator(new LinearInterpolator());
        anim.setDuration(1500);
        anim.addListener(new AnimationListenerAdapter() {
                             @Override
                             public void onAnimationEnd(Animator animation) {
                                 turn(train);
                             }
                         }
        );
        anim.start();
    }

    private void moveRight3(final ImageView train){
        ObjectAnimator anim = ObjectAnimator.ofFloat( train, View.X, train.getX(), train.getX() + 172 );
        anim.setInterpolator(new LinearInterpolator());
        anim.setDuration(1500);
        anim.addListener(new AnimationListenerAdapter() {
                             @Override
                             public void onAnimationEnd(Animator animation) {
                                 moveRight3(train);
                             }
                         }
        );
        anim.start();
    }

    private void moveDown(final ImageView train){
        ObjectAnimator anim = ObjectAnimator.ofFloat( train, View.Y, train.getY(), train.getY() + 128 );
        anim.setInterpolator(new LinearInterpolator());
        anim.setDuration(1500);
        anim.addListener(new AnimationListenerAdapter() {
                             @Override
                             public void onAnimationEnd(Animator animation) {
                                 moveDown2(train);
                             }
                         }
        );
        anim.start();
    }

    private void moveDown2(final ImageView train){
        ObjectAnimator anim = ObjectAnimator.ofFloat( train, View.Y, train.getY(), train.getY() + 128 );
        anim.setInterpolator(new LinearInterpolator());
        anim.setDuration(1500);
        anim.addListener(new AnimationListenerAdapter() {
                             @Override
                             public void onAnimationEnd(Animator animation) {
                                 turnRight(train);
                             }
                         }
        );
        anim.start();
    }

    private void turn(final ImageView train) {

        AnimatorSet animationSet = new AnimatorSet();
        animationSet.addListener(new AnimationListenerAdapter(){
            @Override
            public void onAnimationEnd(Animator animation) {
                moveDown(train);
            }
        });

        float x = train.getX();
        float y = train.getY();

        float[] valuesX = {x, x + 10, x + 20, x + 30, x + 40, x + 40, x + 40};
        ObjectAnimator ax = ObjectAnimator.ofFloat(train, View.X, valuesX);
        ax.setDuration(1500);

        float[] valuesY = {y, y + 10, y + 20, y + 30, y + 45, y + 70, y + 90};
        ObjectAnimator ay = ObjectAnimator.ofFloat(train, View.Y, valuesY);
        ay.setDuration(1500);


        ObjectAnimator ar = ObjectAnimator.ofFloat(train, "rotation", 0, 90f);
        ar.setDuration(1400);

        animationSet.play(ax).with(ay).with(ar);
        animationSet.setInterpolator(new LinearInterpolator());
        animationSet.start();
    }

    private void turnRight(final ImageView train) {

        AnimatorSet animationSet = new AnimatorSet();
        animationSet.addListener(new AnimationListenerAdapter(){
            @Override
            public void onAnimationEnd(Animator animation) {
                moveRight3(train);
            }
        });

        float x = train.getX();
        float y = train.getY();

        float[] valuesX = {x, x + 10, x + 20, x + 30, x + 65, x + 90, x + 120};

        ObjectAnimator ax = ObjectAnimator.ofFloat(train, View.X, valuesX);
        ax.setDuration(1500);

        float[] valuesY = {y, y + 10, y + 15, y + 20};
        ObjectAnimator ay = ObjectAnimator.ofFloat(train, View.Y, valuesY);
        ay.setDuration(1500);

        ObjectAnimator ar = ObjectAnimator.ofFloat(train, "rotation", 90f, 0f);
        ar.setDuration(1200);

        animationSet.play(ax).with(ay).with(ar);
        animationSet.setInterpolator(new LinearInterpolator());
        animationSet.start();
    }

    private void showStartingPosition(ImageView train) {
        //coordinates + size + orientation

        workingArea.removeView(train);

        train.getLayoutParams().height = 100;
        train.getLayoutParams().width = 100;
        train.setScaleType(ImageView.ScaleType.FIT_XY);

        train.setX(172);
        train.setY(128 + 28);

        train.setVisibility(View.VISIBLE);

        train.requestLayout();
        workingArea.addView(train);
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

        workingAreaView.setBackgroundResource(boardResouceId);
    }
}
