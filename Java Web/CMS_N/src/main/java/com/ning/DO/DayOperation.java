package com.ning.DO;

import com.ning.DAO.DayEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by ning on 2017/7/11.
 */
@Deprecated
public class DayOperation implements IOperation<DayEntity> {
    Operation<DayEntity> o = new Operation<>();

    @Override
    public boolean delete(int id) {
        return o.delete(id, new DoInDO<Integer, Boolean>() {
            @Override
            public Boolean dosomething(Session session, Integer integer) throws Exception {
                DayEntity d = new DayEntity();
                d.setId(id);
                session.delete(d);
                return true;
            }
        });
    }

    @Override
    public DayEntity selectById(int id) {
        return o.selectById(id, new DoInDO<Integer, DayEntity>() {
            @Override
            public DayEntity dosomething(Session session, Integer integer) throws Exception {
                String hql = "from DayEntity where id = :id";
                Query<DayEntity> query = session.createQuery(hql);
                query.setParameter("id", id);
                DayEntity d = query.uniqueResult();
                return d;

            }
        });
    }

    @Override
    public List<DayEntity> selectAll() {
        return o.selectAll(new DoInDO<DayEntity, List<DayEntity>>() {
            @Override
            public List<DayEntity> dosomething(Session session, DayEntity dayEntity) throws Exception {
                String hql = "from DayEntity ";
                Query<DayEntity> query = session.createQuery(hql, DayEntity.class);
                return query.list();
            }
        });
    }
}
