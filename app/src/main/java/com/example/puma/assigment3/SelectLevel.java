package com.example.puma.assigment3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SelectLevel extends AppCompatActivity {
    private Button btnLev1s1;
    private Button btnLev1s2;
    private Button btnLev1s3;
    private Button exit;
    String mapName="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_level);
        btnLev1s1 = (Button)findViewById(R.id.btn_lev1s1);
        btnLev1s2 = (Button)findViewById(R.id.btn_lev1s2);
        btnLev1s3 = (Button)findViewById(R.id.btn_lev1s3);
        exit = (Button) findViewById(R.id.exit);
        btnLev1s1.setOnClickListener(new SelectLevelLstr());
        btnLev1s2.setOnClickListener(new SelectLevelLstr());
        btnLev1s3.setOnClickListener(new SelectLevelLstr());
        exit.setOnClickListener(new SelectLevelLstr());

    }

    class SelectLevelLstr implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.btn_lev1s1) {
                mapName="lev1s1";
                Intent intent = new Intent(SelectLevel.this, ManageActivity.class);
                intent.putExtra("mapToLoad",mapName);
                startActivity(intent);
            }
            else if (view.getId() == R.id.btn_lev1s2){
                mapName="lev1s2";
                Intent intent = new Intent(SelectLevel.this, ManageActivity.class);
                intent.putExtra("mapToLoad",mapName);
                startActivity(intent);
            } else if (view.getId() == R.id.btn_lev1s3){
                mapName="lev1s3";
                Intent intent = new Intent(SelectLevel.this, ManageActivity.class);
                intent.putExtra("mapToLoad",mapName);
                startActivity(intent);
            } else if (view.getId() == R.id.exit){
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        }
    }
}
