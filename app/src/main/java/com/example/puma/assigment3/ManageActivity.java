package com.example.puma.assigment3;

import android.content.ClipData;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class ManageActivity extends AppCompatActivity {
    StartDraggingLsntr myStartDraggingLsnr;
    EndDraggingLsntr myEndDraggingLsntr;
    private Button btn_Play;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);
        myStartDraggingLsnr=new StartDraggingLsntr();
        myEndDraggingLsntr=new EndDraggingLsntr();
        findViewById(R.id.upBtn).setOnLongClickListener(myStartDraggingLsnr);
        findViewById(R.id.downBtn).setOnLongClickListener(myStartDraggingLsnr);
        findViewById(R.id.leftBtn).setOnLongClickListener(myStartDraggingLsnr);
        findViewById(R.id.rightBtn).setOnLongClickListener(myStartDraggingLsnr);

        findViewById(R.id.Btn1).setOnDragListener(myEndDraggingLsntr);
        findViewById(R.id.Btn2).setOnDragListener(myEndDraggingLsntr);
        findViewById(R.id.Btn3).setOnDragListener(myEndDraggingLsntr);
        findViewById(R.id.Btn4).setOnDragListener(myEndDraggingLsntr);
        findViewById(R.id.Btn5).setOnDragListener(myEndDraggingLsntr);
        findViewById(R.id.Btn6).setOnDragListener(myEndDraggingLsntr);
        findViewById(R.id.Btn7).setOnDragListener(myEndDraggingLsntr);
        findViewById(R.id.Btn8).setOnDragListener(myEndDraggingLsntr);

        btn_Play = (Button)findViewById(R.id.btnPlay);
        btn_Play.setOnClickListener(new PlayLstr());
    }


    private class EndDraggingLsntr implements View.OnDragListener{
        @Override
        public boolean onDrag(View view, DragEvent event) {
            if (event.getAction()==DragEvent.ACTION_DROP)
            {
                ((Button) view).setBackground( ((Button) event.getLocalState()).getBackground());
                ((Button) view).setContentDescription( ((Button) event.getLocalState()).getContentDescription());
            }

            return true;
        }
    }

    private class StartDraggingLsntr implements View.OnLongClickListener{
        @Override
        public boolean onLongClick(View view) {
            WithDraggingShadow shadow = new WithDraggingShadow(view);
            ClipData data=ClipData.newPlainText("","");
            view.startDrag( data, shadow, view, 0);
            return false;
        }
    }

    //Bitmap image;
    private class WithDraggingShadow extends View.DragShadowBuilder{
        //public WithDraggingShadow(View view, Bitmap draggingPicture){
        public WithDraggingShadow(View view){
            super(view);
            //image=draggingPicture;
        }

        @Override
        public void onDrawShadow(Canvas canvas) {
            super.onDrawShadow(canvas);
        }

        @Override
        public void onProvideShadowMetrics(Point shadowSize, Point shadowTouchPoint) {
            super.onProvideShadowMetrics(shadowSize, shadowTouchPoint);
        }
    }

    class PlayLstr implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btnPlay) {
// collect instructions
                ArrayList<String> sequence;
                String description;
                description = "sequence: " + findViewById(R.id.Btn1).getContentDescription() + ", "
                        + findViewById(R.id.Btn2).getContentDescription() + ", "
                        + findViewById(R.id.Btn3).getContentDescription() + ", "
                        + findViewById(R.id.Btn4).getContentDescription()+ ", "
                        + findViewById(R.id.Btn5).getContentDescription()+ ", "
                        + findViewById(R.id.Btn6).getContentDescription()+ ", "
                        + findViewById(R.id.Btn7).getContentDescription()+ ", "
                        + findViewById(R.id.Btn8).getContentDescription()+ ", "
                        + findViewById(R.id.Btn9).getContentDescription();
                Toast.makeText(ManageActivity.this, description, Toast.LENGTH_SHORT).show();

            }
        }
    }
}
