package com.jstampfli.dierolegame;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.jstampfli.dierolegame.DArray.sum;
import static com.jstampfli.dierolegame.UserPick.CalcDieTotal;
import static com.jstampfli.dierolegame.UserPick.genPossiblities;
import static com.jstampfli.dierolegame.UserPick.groupP;
import static com.jstampfli.dierolegame.UserPick.highest;

public class DGame extends AppCompatActivity {

    static int rolled=10;
    static int picked=5;
    String computerRoll="";
    TextView dSpace;
    EditText dNum;
    EditText dNumPick;
    static List<Integer> dValue = new ArrayList<>(rolled);
    static List<Integer> cValue = new ArrayList<>(rolled);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dgame);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        dSpace= (TextView) findViewById(R.id.textView);
        dSpace.setTextSize(20);

        dNum = (EditText) findViewById(R.id.dice);
        dNum.setTextSize(30);

        dNumPick = (EditText) findViewById(R.id.dPick);
        dNumPick.setTextSize(30);
    }

    public void onClick(View v){
        dValue.clear();
        cValue.clear();
        dSpace.setText("");
        highest=0;
        computerRoll="";

        try{
            rolled=Integer.parseInt(dNum.getText().toString());
            if(rolled<2){
                rolled=10;
            }
        }
        catch(NumberFormatException e){
            rolled=10;
        }

        try{
            picked = Integer.parseInt(dNumPick.getText().toString());
        }
        catch(NumberFormatException e){
            picked=5;
        }

        for(int y=0;y<rolled; y++){
            int random = (int)(Math.random()*6+1);
            dSpace.setText(dSpace.getText()+String.valueOf(random)+" ");
            dValue.add(random);
        }
        for(int y=0; y<rolled; y++){
            int random=(int)(Math.random()*6+1);
            computerRoll=computerRoll+String.valueOf(random)+" ";
            cValue.add(random);
        }

        for(int i=0; i<2; i++){
            highest=0;
            groupP="";

            List<Integer> empty = Arrays.asList(new Integer[picked]);

            if(i==0){
                genPossiblities(dValue, picked, 0, empty);
                dSpace.setText("Players Values:\n"+dSpace.getText()+"\n\n"+groupP+": "+String.valueOf(highest));
            }
            else{
                genPossiblities(cValue, picked, 0, empty);
                dSpace.setText(dSpace.getText()+"\n\n"+"Computer's Values:"+"\n"+computerRoll+"\n\n"+groupP+": "+String.valueOf(highest));
            }
        }
    }

}
