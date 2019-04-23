package com.example.hjk;

import java.util.Calendar;

/**
 * @author linmr
 * @description: xx
 * @date 2019/1/9
 */
public class Test {
    public static void main(String[] args){
        Calendar calendar = Calendar.getInstance();
        int mouth = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DATE);
        System.out.println(mouth);
        System.out.println(day);
    }
}
