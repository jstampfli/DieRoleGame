package com.jstampfli.dierolegame;

import java.util.List;

/**
 * Created by User1 on 10/7/2017.
 */

public class DArray {
    List<Integer> start;
    static int sum=0;

    public DArray(List<Integer> x){
        start=x;
    }
    public int pick(){
        sum=0;
        for(int i=0; i<start.size()-1; i++){
            for(int x=i+1; x<start.size();x++){
                if(start.get(x)==start.get(i)){
                    if(2*(start.get(x)+start.get(i))>sum){
                        sum=2*(start.get(x)+start.get(i));
                    }
                }
                else if(start.get(x)+start.get(i)>sum){
                    sum=start.get(x)+start.get(i);
                }
            }
        }
        return sum;
    }
}
