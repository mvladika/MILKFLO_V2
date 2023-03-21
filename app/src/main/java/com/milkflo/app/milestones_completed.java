package com.milkflo.app;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class milestones_completed extends AppCompatActivity {

    @SuppressLint("ResourceAsColor")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_milestones);



        CheckBox[] checkBox;
        TextView[] textView;
        checkBox = new CheckBox[20];
        textView = new TextView[20];


        checkBox[0] = findViewById(R.id.m1checkbox);
        checkBox[1] = findViewById(R.id.m2checkbox);
        checkBox[2] = findViewById(R.id.m3checkbox);
        checkBox[3] = findViewById(R.id.m4checkbox);
        checkBox[4] = findViewById(R.id.m5checkbox);
        checkBox[5] = findViewById(R.id.m6checkbox);
        checkBox[6] = findViewById(R.id.m7checkbox);
        checkBox[7] = findViewById(R.id.m8checkbox);
        checkBox[8] = findViewById(R.id.m9checkbox);
        checkBox[9] = findViewById(R.id.m10checkbox);
        checkBox[10] = findViewById(R.id.m11checkbox);
        checkBox[11] = findViewById(R.id.m12checkbox);
        checkBox[12] = findViewById(R.id.m13checkbox);
        checkBox[13] = findViewById(R.id.m14checkbox);
        checkBox[14] = findViewById(R.id.m15checkbox);
        checkBox[15] = findViewById(R.id.m16checkbox);
        checkBox[16] = findViewById(R.id.m17checkbox);
        checkBox[17] = findViewById(R.id.m18checkbox);
        checkBox[18] = findViewById(R.id.m19checkbox);
        checkBox[19] = findViewById(R.id.m20checkbox);

        textView[0] = findViewById(R.id.m1comment);


        final TextView[] comments = new TextView[19];
        final TextView[] mName = new TextView[19];

        final LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout);

        for (int i = 0; i < 20; i++) {
                
            if(checkBox[i] != null){
                if(checkBox[i].isChecked()){

                    mName[i] = new TextView(this);
                    mName[i].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                            LayoutParams.WRAP_CONTENT));
                    mName[i].setText(checkBox[i].getText());
                    mName[i].setTextColor(R.color.white);
                    mName[i].setPadding(20, 20, 20, 20);
                    if(i>0){
                        int increment = 20 * i;
                        mName[i].setPadding(20, increment, 20, 20);
                    }
                    layout.addView(mName[i]);



                    comments[i] = new TextView(this);
                    comments[i].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                            LayoutParams.WRAP_CONTENT));
                    comments[i].setText(textView[i].getText());
                    comments[i].setTextColor(R.color.white);
                    mName[i].setPadding(20, 30, 20, 20);

                    if(i>0){
                        int increment = 30 * i;
                        mName[i].setPadding(20, increment, 20, 20);
                    }
                    layout.addView(comments[i]);

                }
            }
            
        }

    }


}