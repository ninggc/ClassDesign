package com.ning.DO;

import com.ning.DAO.DayEntity;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ning on 2017/7/11.
 */
public class DayOperationTest {
    DayOperation dop = new DayOperation();

    @Test
    public void delete() throws Exception {
    }

    @Test
    public void selectById() throws Exception {
        DayEntity d = dop.selectById(2);
        System.out.println(d.toJSON());

    }

    @Test
    public void selectAll() throws Exception {
        for (DayEntity d :
                dop.selectAll()) {
            System.out.println(d.toJSON());
        }
    }

}