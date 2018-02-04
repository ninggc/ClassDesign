package com.ning.DO;

import com.ning.DAO.TeacherEntity;
import com.ning.DAO.TimeEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by ning on 2017/7/4.
 */
public class TimeOperation implements IOperation<TimeEntity> {
    private Operation<TimeEntity> o = new Operation<>();

    public boolean save(TimeEntity time) {
        return o.query(new DoInDO<String, Boolean>() {
            @Override
            public Boolean dosomething(Session session, String s) throws Exception {
                session.save(time);
                return true;
            }
        });
    }

    @Override
    public boolean delete(int id) {
        return o.delete(id, (session, integer) -> {
            TimeEntity c = new TimeEntity();
            c.setId(id);
            session.delete(c);
            return true;
        });
    }

    @Override
    public TimeEntity selectById(int id) {
        return o.selectById(id, (session, integer) -> {
            String hql = "from TimeEntity where id = :id";
            Query<TimeEntity> query = session.createQuery(hql);
            query.setParameter("id", id);
            TimeEntity t = query.uniqueResult();
            return t;
        });
    }

    @Override
    public List<TimeEntity> selectAll() {
        return o.selectAll((session, timeEntity) -> {
            String hql = "from TimeEntity ";
            Query<TimeEntity> query = session.createQuery(hql, TimeEntity.class);
            return query.list();
        });
    }

    public int selectMaxId() {
        return o.query(new DoInDO<String, Integer>() {
            @Override
            public Integer dosomething(Session session, String s) throws Exception {
                String hql = "select max(id) from TimeEntity";
                Query query = session.createQuery(hql);
                return (Integer) query.uniqueResult();
            }
        });
    }

}
