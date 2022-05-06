package com.kellerrush.example.test;

import com.kellerrush.example.javacore.ListNature;
import org.junit.Test;


public class ListNatureTest {

    @Test
    public void testAdd(){
        ListNature listNature = new ListNature();
        int[] testCount = {10000, 50000, 100000};
        for (int i: testCount){
            listNature.testAddFirstAndLast(i);
        }
    }

    @Test
    public void testInsert(){
        ListNature listNature = new ListNature();
        int[] listSize = {10000, 50000, 100000};
        int[] testCount = {10000, 50000, 100000};
        for (int i: testCount){
            for (int j: listSize){
                listNature.testInsertRandom(j, i);
            }
        }
    }

    @Test
    public void testReplace(){
        ListNature listNature = new ListNature();
        int[] testCount = {10000, 50000, 100000};
        for (int i: testCount){
            listNature.testReplaceRandom(i);
        }
    }

    @Test
    public void testRemove(){
        ListNature listNature = new ListNature();
        int[] testCount = {100000, 500000};
        int[] listSize = {1000000,2000000};

        for (int i: testCount){
            for (int j : listSize) {
                listNature.testRemoveRandom(j, i);

            }
        }
    }

    @Test
    public void testGet(){
        ListNature listNature = new ListNature();
        int[] listSize = {10000, 50000, 100000};
        int[] testCount = {10000, 50000, 100000};
        for (int i: testCount){
            for (int j: listSize){
                listNature.testGetRandom(j, i);
            }
        }
    }


}
