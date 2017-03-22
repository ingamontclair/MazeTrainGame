package com.example.puma.assigment3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.Arrays;

public class WorkingArea extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_working_area);


        final ImageView train = (ImageView) findViewById(R.id.train);
        final RelativeLayout workingAreaView = (RelativeLayout) findViewById(R.id.board);

        final Board b = new Board(BoardConfiguration.MAP_1, workingAreaView );

        Button play = (Button) findViewById(R.id.btnPlay);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                b.run(train, Arrays.asList(Direction.RIGHT.name(), Direction.DOWN.name(), Direction.RIGHT.name()));
            }
        });

    }
}
