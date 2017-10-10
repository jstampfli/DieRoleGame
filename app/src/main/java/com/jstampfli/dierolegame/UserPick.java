package com.jstampfli.dierolegame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.jstampfli.dierolegame.DGame.dSpace;

/**
 * Created by User1 on 10/8/2017.
 */

public class UserPick {

    static String groupP;
    static int highest=0;

    public static void CalcDieTotal(List<Integer> sList){
        int sum=0;
        List<Integer> num = Arrays.asList(new Integer[7]);
        for(int i=0; i<num.size(); i++){
            num.set(i,0);
        }
        for(int i=0; i<sList.size(); i++){
            num.set(sList.get(i), num.get(sList.get(i))+1);
        }
        for(int i=0; i<num.size(); i++){
            sum+=num.get(i)*num.get(i)*i;
        }
        if(sum>highest){
            highest=sum;
            groupP=String.valueOf(sList);
        }

    }

    public static void genPossiblities(List<Integer> vRoll, int length, int pos, List<Integer> result){
        if(length==0){
            CalcDieTotal(result);
            return;
        }
        for(int i=pos; i<=vRoll.size()-length; i++){
            result.set(result.size()-length, vRoll.get(i));
            genPossiblities(vRoll, length-1, i+1, result);
        }
    }
}
