package com.example.puma.assigment3;

import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class ManageActivity extends AppCompatActivity {
    StartDraggingLsntr myStartDraggingLsnr;
    EndDraggingLsntr myEndDraggingLsntr;
    private Button btn_Play;
    private Board board = null;
    private boolean result;
    private String currentBoard="";
    private LinearLayout workingArea;
    MediaPlayer backgroundMusic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);

        workingArea = (LinearLayout) findViewById(R.id.ll_working_area);
        Intent selectLevel= getIntent();
        Bundle bundle = selectLevel.getExtras();
        backgroundMusic = MediaPlayer.create(ManageActivity.this, R.raw.oceanwavescrushing);
        backgroundMusic.setLooping(true);
        //backgroundMusic.setVolume(10.0f, 3.0f);
        backgroundMusic.start();

        myStartDraggingLsnr=new StartDraggingLsntr();
        myEndDraggingLsntr=new EndDraggingLsntr();
        if(bundle!=null)
        {
            String tmpMap =(String) bundle.get("mapToLoad");
            if (tmpMap.equals("lev1s1")){
                 board = Board.MAP_1;
                currentBoard = tmpMap;
            }
            else if (tmpMap.equals("lev1s2")){
                 board = Board.MAP_2;
                currentBoard = tmpMap;
            } else
            if (tmpMap.equals("lev1s3")){
                 board = Board.MAP_3;
                currentBoard = tmpMap;
            }
            board.setWorkingArea(workingArea);
        }

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

                ArrayList sequence = new ArrayList<String>();
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
/*                sequence.add(0,findViewById(R.id.Btn1).getContentDescription().toString());
                sequence.add(1,findViewById(R.id.Btn2).getContentDescription().toString());
                sequence.add(2,findViewById(R.id.Btn3).getContentDescription().toString());
                sequence.add(4,findViewById(R.id.Btn4).getContentDescription().toString());
                sequence.add(5,findViewById(R.id.Btn5).getContentDescription().toString());
                sequence.add(6,findViewById(R.id.Btn6).getContentDescription().toString());
                sequence.add(7,findViewById(R.id.Btn7).getContentDescription().toString());
                sequence.add(8,findViewById(R.id.Btn8).getContentDescription().toString());
                sequence.add(9,findViewById(R.id.Btn9).getContentDescription().toString());*/
                //Toast.makeText(ManageActivity.this, description, Toast.LENGTH_SHORT).show();
                //win window
                result=true;
                CustomDialogClass cdd=new CustomDialogClass(ManageActivity.this);
                cdd.show();
                //run the train
                //boolean result = board.run((ImageView) findViewById(R.id.train), Arrays.asList(Direction.RIGHT.name(), Direction.DOWN.name(), Direction.RIGHT.name()));
                //System.out.println("Finished: " + result);
                //Toast.makeText(ManageActivity.this, description, Toast.LENGTH_SHORT).show();
            }
        }
    }
    public class CustomDialogClass extends Dialog implements
            android.view.View.OnClickListener {

        public Activity currentActivity;
        public Dialog d;
        public Button menu, next, replay;

        public CustomDialogClass(Activity a) {
            super(a);
            // TODO Auto-generated constructor stub
            this.currentActivity = a;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            if (result == true) {
                setContentView(R.layout.custom_win);
                menu = (Button) findViewById(R.id.btn_menu);
                next = (Button) findViewById(R.id.btn_next);
                menu.setOnClickListener(this);
                next.setOnClickListener(this);
            }
            else {
                setContentView(R.layout.custom_fail);
                menu = (Button) findViewById(R.id.btn_menu);
                replay = (Button) findViewById(R.id.btn_replay);
                replay.setOnClickListener(this);
                menu.setOnClickListener(this);
            }

        }

        @Override
        public void onClick(View view) {
            Intent intent ;
            switch (view.getId()) {
                case R.id.btn_menu:
                    currentActivity.finish();
                    intent = new Intent(ManageActivity.this, SelectLevel.class);
                    startActivity(intent);
                    break;
                case R.id.btn_replay:
                    //result = false;
                    dismiss();
                    currentActivity.recreate();
                    break;
                case R.id.btn_next:

                    if (currentBoard.equals("lev1s1")){

                        intent = new Intent(ManageActivity.this, ManageActivity.class);
                        intent.putExtra("mapToLoad","lev1s2");
                        startActivity(intent);
                    }
                    else if (currentBoard.equals("lev1s2")){
                        intent = new Intent(ManageActivity.this, ManageActivity.class);
                        intent.putExtra("mapToLoad","lev1s3");
                        startActivity(intent);
                    }
                    else if (currentBoard.equals("lev1s3")){
                        Toast.makeText(ManageActivity.this, "Game Over", Toast.LENGTH_SHORT).show();
                    }

                    break;
                default:
                    break;
            }
            dismiss();
        }
    }
}
