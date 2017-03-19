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

        Board b = Board.MAP_1;
        b.setWorkingArea(workingAreaView);

        boolean result = b.run(train, Arrays.asList(Direction.RIGHT.name(), Direction.DOWN.name(), Direction.RIGHT.name()));
        System.out.println("Finished: " + result);

    }
}
