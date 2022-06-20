package com.example.video;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Flappy extends Activity {
    private Button btn_enter, leader_board;
    private EditText userName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.flappy);



        leader_board = findViewById(R.id.leader_board);
        btn_enter = (Button) findViewById(R.id.btn_submit);
        userName = (EditText) findViewById(R.id.user_name);
        //userNumber = (EditText) findViewById(R.id.user_number);



        btn_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = userName.getText().toString().trim();
                //int number = Integer.parseInt(String.valueOf(userNumber.getText()));

                if(!TextUtils.isEmpty(userName.getText())) {
                    Intent i = new Intent(getApplicationContext(), com.example.video.GameActivity.class);
                    i.putExtra("keypseudo",name);
                    startActivity(i);
                    finish();
                }
            }
        });

        leader_board.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),OtherActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
}