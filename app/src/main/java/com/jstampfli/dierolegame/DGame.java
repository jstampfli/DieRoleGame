package com.jstampfli.dierolegame;

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
import static com.jstampfli.dierolegame.UserPick.highest;

public class DGame extends AppCompatActivity {

    int rolled=10;
    int picked=5;
    TextView dSpace;
    EditText dNum;
    EditText dNumPick;
    List<Integer> dValue = new ArrayList<>(rolled);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dgame);

        dSpace= (TextView) findViewById(R.id.textView);
        dNum = (EditText) findViewById(R.id.dice);
        dNumPick = (EditText) findViewById(R.id.dPick);
    }

    public void onClick(View v){
        dValue.clear();
        dSpace.setText("");
        highest=0;

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
        
        List<Integer> empty = Arrays.asList(new Integer[picked]);
        genPossiblities(dValue, picked, 0, empty);
        dSpace.setText(dSpace.getText()+": "+String.valueOf(highest));
    }

}
