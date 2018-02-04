package com.ning.util;

import com.ning.factory.Constant;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateDemo {
    SimpleDateFormat simpleDateFormat = Constant.simpleDateFormat;

    @Test
    public void test() {

        String format = simpleDateFormat.format(Calendar.getInstance().getTime());
        System.out.println(format);

        Date date = null;
        try {
            date = simpleDateFormat.parse(format);
            System.out.println(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(simpleDateFormat.format(date).equals(simpleDateFormat.format(new Date())));
    }

    @Test
    public void testString() {
        System.out.println("123".equals(new String("123")));
        System.out.println(simpleDateFormat.format(new Date()).equals("2017-12-19"));
    }

}
