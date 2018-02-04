package com.ning.factory;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class UtilTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void handleSerialNumber() throws Exception {
//        ["7","8","9"]
//["11","12","13","14"]
//["9"]
//["6","7","8"]
//["2","5"]
//["7","11", "15"]
        Integer[][] arr = new Integer[5][];
        Integer i = 0;
        arr[i++] = new Integer[]{7, 8, 9};
        arr[i++] = new Integer[]{11, 12, 13, 14};
        arr[i++] = new Integer[]{5};
        arr[i++] = new Integer[]{6, 1, 7};
        arr[i++] = new Integer[]{11, 7, 15};

        for (int i1 = 0; i1 < arr.length; i1++) {
            String s = Util.handleSerialNumber(Arrays.asList(arr[i1]));
            System.out.println(s);
            System.out.println(Arrays.toString(s.split(" ")));
            System.out.println("======");
        }

    }

}