package com.example.puma.assigment3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class WorkingArea extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_working_area);

        ImageView train = (ImageView) findViewById( R.id.train );
        LinearLayout board = (LinearLayout) findViewById( R.id.activity_working_area );
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics( dm );
        int statusBarOffset = dm.heightPixels - board.getMeasuredHeight();

        int originalPos[] = new int[2];
        train.getLocationOnScreen( originalPos );

        int xDest = dm.widthPixels/4;
        xDest -= (train.getMeasuredWidth()/2);

        TranslateAnimation anim = new TranslateAnimation( 0, xDest - originalPos[0] , 0, originalPos[1] );
        anim.setDuration(5000);
        anim.setFillAfter( true );
        train.startAnimation(anim);
    }
}
