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

    int pScore=0;
    int cScore=0;
    String score;

    static int pWinner=0;
    static int cWinner=0;

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
        dNum.setTextSize(20);

        dNumPick = (EditText) findViewById(R.id.dPick);
        dNumPick.setTextSize(20);

        pScore=0;
        cScore=0;
    }

    public void onClick(View v){
        dValue.clear();
        cValue.clear();
        dSpace.setText("");
        highest=0;
        computerRoll="";
        score=String.valueOf(pScore)+" : "+String.valueOf(cScore);

        try{
            rolled=Integer.parseInt(dNum.getText().toString());
            if(rolled<2){
                rolled=10;
                dNum.setText("10");
            }
            if(rolled>20){
                rolled=20;
                dNum.setText("20");
            }
        }
        catch(NumberFormatException e){

        }

        try{
            picked = Integer.parseInt(dNumPick.getText().toString());
            if(picked<1){
                picked=1;
                dNumPick.setText("1");
            }
            if(picked>19){
                picked=19;
                dNumPick.setText("19");
            }
            if(picked>rolled){
                picked=rolled-1;
                dNumPick.setText(String.valueOf(picked));
            }
        }
        catch(NumberFormatException e){

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
                pWinner=highest;
                dSpace.setText("Players Values:\n"+dSpace.getText()+"\n\n"+groupP+": "+String.valueOf(highest));
            }
            else{
                genPossiblities(cValue, picked, 0, empty);
                cWinner=highest;
                dSpace.setText(dSpace.getText()+"\n\n"+"Computer's Values:"+"\n"+computerRoll+"\n\n"+groupP+": "+String.valueOf(highest));
            }
        }
        if(pWinner>cWinner){
            dSpace.setText(dSpace.getText()+"\n\nThe Player Won!");
            pScore++;
        }
        else if(pWinner==cWinner){
            dSpace.setText(dSpace.getText()+"\n\nThe Player and The Computer Tied!");
        }
        else{
            dSpace.setText(dSpace.getText()+"\n\nThe Computer Won!");
            cScore++;
        }
        dSpace.setText("The score is "+score+"\n\n"+dSpace.getText());
    }

}
