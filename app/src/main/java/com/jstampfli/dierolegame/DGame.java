package com.jstampfli.dierolegame;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.jstampfli.dierolegame.UserPick.genPossiblities;
import static com.jstampfli.dierolegame.UserPick.groupP;
import static com.jstampfli.dierolegame.UserPick.highest;

public class DGame extends AppCompatActivity {

    static int rolled=10;
    static int picked=5;
    TextView dSpace;
    EditText dNum;
    EditText dNumPick;

    int pScore=0;
    int cScore=0;
    int tie=0;
    String score;

    View help;

    static int pWinner=0;
    static int cWinner=0;

    static List<Integer> dValue = new ArrayList<>(rolled);
    static List<Integer> cValue = new ArrayList<>(rolled);
    static List<Integer> tPlayerValues = new ArrayList<>();
    static List<Integer> tComputerValues = new ArrayList<>();

    Spinner spinner;
    ArrayAdapter spinnerAdapter;

    static int selectedDieValue=6;

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

        spinner = (Spinner) findViewById(R.id.spinner);
        spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.spinner_dice, R.layout.spinner_layout);
        spinnerAdapter.setDropDownViewResource(R.layout.spinner_layout);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String temp = String.valueOf(parent.getItemAtPosition(position));
                selectedDieValue = Integer.parseInt(temp.substring(1));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                selectedDieValue=6;
            }
        });
    }

    public void onClickFifty(View view){
        try{
            picked=Integer.parseInt(dNumPick.getText().toString());
            rolled=Integer.parseInt(dNum.getText().toString());
            if(rolled>10){
                rolled=10;
                dNum.setText(String.valueOf(rolled));
                if(picked>=rolled){
                    picked=rolled-1;
                    dNumPick.setText(String.valueOf(picked));
                }
            }
        }
        catch(NumberFormatException e){
            rolled=10;
            dNum.setText(String.valueOf(rolled));
            picked=rolled-1;
            dNumPick.setText(String.valueOf(picked));
        }

        for(int i=0; i<50; i++){
            onClick(help);
        }
    }

    public void reset(View view){
        finish();
        startActivity(new Intent(DGame.this, DGame.class));
    }

    public void onClick(View v){
        dValue.clear();
        cValue.clear();
        tComputerValues.clear();
        tPlayerValues.clear();
        dSpace.setText("");
        highest=0;

        try{
            rolled=Integer.parseInt(dNum.getText().toString());
            if(rolled<2){
                rolled=10;
                dNum.setText(String.valueOf(rolled));
            }
            if(rolled>20){
                rolled=20;
                dNum.setText(String.valueOf(rolled));
            }
        }
        catch(NumberFormatException e){
            rolled=10;
            dNum.setText(String.valueOf(rolled));
        }

        try{
            picked = Integer.parseInt(dNumPick.getText().toString());
            if(picked<1){
                picked=1;
                dNumPick.setText(String.valueOf(picked));
            }
            if(picked>19){
                picked=19;
                dNumPick.setText(String.valueOf(picked));
            }
            if(picked>=rolled){
                picked=rolled-1;
                dNumPick.setText(String.valueOf(picked));
            }
        }
        catch(NumberFormatException e){
            picked=5;
            dNumPick.setText(String.valueOf(picked));
        }

        for(int y=0;y<rolled; y++){
            int random = (int)(Math.random()*selectedDieValue+1);
            //dSpace.setText(dSpace.getText()+String.valueOf(random)+" ");
            tPlayerValues.add(random);
            dValue.add(random);
        }
        Collections.sort(tPlayerValues);
        Collections.sort(dValue);

        for(int y=0; y<rolled; y++){
            int random=(int)(Math.random()*selectedDieValue+1);
            tComputerValues.add(random);
            cValue.add(random);
        }
        Collections.sort(cValue);
        Collections.sort(tComputerValues);

        for(int i=0; i<2; i++){
            highest=0;
            groupP="";

            List<Integer> empty = Arrays.asList(new Integer[picked]);

            if(i==0){
                genPossiblities(dValue, picked, 0, empty);
                pWinner=highest;
                dSpace.setText("Players Values: "+String.valueOf(tPlayerValues)+"\n\n"+groupP+": "+String.valueOf(highest));
            }
            else{
                genPossiblities(cValue, picked, 0, empty);
                cWinner=highest;
                dSpace.setText(dSpace.getText()+"\n\n"+"Computer's Values: "+String.valueOf(tComputerValues)+"\n\n"+groupP+": "+String.valueOf(highest));
            }
        }
        if(pWinner>cWinner){
            dSpace.setText(dSpace.getText()+"\n\nThe Player Won!");
            pScore++;
        }
        else if(pWinner==cWinner){
            dSpace.setText(dSpace.getText()+"\n\nThe Player and The Computer Tied!");
            tie++;
        }
        else{
            dSpace.setText(dSpace.getText()+"\n\nThe Computer Won!");
            cScore++;
        }
        score=String.valueOf(pScore)+" : "+String.valueOf(cScore)+" : "+String.valueOf(tie);
        dSpace.setText("The score is "+score+"\n\n"+dSpace.getText());
    }

}
