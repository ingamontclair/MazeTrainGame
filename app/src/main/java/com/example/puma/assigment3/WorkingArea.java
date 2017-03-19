package com.example.puma.assigment3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.Arrays;

public class WorkingArea extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_working_area);

        ImageView train = (ImageView) findViewById(R.id.train);
        final LinearLayout workingAreaView = (LinearLayout) findViewById(R.id.activity_working_area);
        final int[] size = new int[2];

        ViewTreeObserver vto = workingAreaView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                workingAreaView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                size[0] = workingAreaView.getMeasuredWidth();
                size[1] = workingAreaView.getMeasuredHeight();
            }
        });

        Board b = Board.MAP_1;
        b.setScreenDimensions(workingAreaView.getTop(), workingAreaView.getLeft(), size[0], size[1]);
        boolean result = b.run(train, Arrays.asList(Direction.RIGHT, Direction.DOWN, Direction.RIGHT));

        System.out.println("Finished: " + result);

    }
}
