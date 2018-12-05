package com.xueyituanchina.xueyituan;

import org.junit.Test;

import java.util.Date;

import top.jplayer.baseprolibrary.utils.DateUtils;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        int l = (int) ((new Date().getTime() - DateUtils.getDateByPattern("2018-12-04 17:23:47", "yyyy-MM-dd " + "HH:mm:ss").getTime()))/1000;
        int dayAll = 3600 * 24 ;
        int h = (dayAll - l) / 3600;
        int m = (dayAll - l) % 3600 / 60;
        System.out.println(h+"......."+m);
    }
}