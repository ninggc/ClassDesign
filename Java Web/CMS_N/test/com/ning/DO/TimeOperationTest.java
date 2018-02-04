package com.ning.DO;

import com.ning.DAO.ClassroomEntity;
import com.ning.DAO.HireEntity;
import com.ning.DAO.TeacherEntity;
import com.ning.DAO.TimeEntity;
import com.ning.factory.IGson;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by ning on 2017/7/11.
 */
public class TimeOperationTest implements IGson {
    TimeOperation to = new TimeOperation();

    @Test
    public void delete() throws Exception {
    }

    @Test
    public void insert() throws Exception {
        TimeEntity time = new TimeEntity();
        time.setDay(new Date());
        time.setTime(gson.toJson(new String[]{"2", "5"}));
        System.out.println(to.save(time));
        System.out.println(time.getId());
    }

    @Test
    public void selectById() throws Exception {
        TimeEntity timeEntity = to.selectById(2);
        System.out.println(timeEntity.toJSON());
//        System.out.println(timeEntity.getHire().getId());
    }

    @Test
    public void selectAll() throws Exception {
    }

    @Test
    public void selectMaxId() {
        System.out.println(to.selectMaxId());
    }

    @Test
    public void splitPage() {
        Operation<TimeEntity> o = new Operation<>();
        List<TimeEntity> list = o.query(new InSession<List<TimeEntity>>() {
            @Override
            public List<TimeEntity> onQuery(Session session) {
                String hql = "from TimeEntity ";
                Query<TimeEntity> query = session.createQuery(hql, TimeEntity.class);
                query.setFirstResult(0);
                query.setMaxResults(2);
                return query.list();
            }
        });

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toJSON());
        }
    }
}