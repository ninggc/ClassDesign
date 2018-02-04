package com.ning.DO;


import com.ning.DAO.TeacherEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by ning on 2017/7/4.
 */
public class TeacherOperation implements IOperation<TeacherEntity> {
    Operation<TeacherEntity> o = new Operation<>();

    @Override
    public boolean delete(int id) {
        return o.delete(id, (session, integer) -> {
            TeacherEntity c = new TeacherEntity();
            c.setId(id);
            session.delete(c);
            return true;
        });
    }

    @Override
    public TeacherEntity selectById(int id) {
        return o.selectById(id, (session, integer) -> {
            String hql = "from TeacherEntity where id = :id";
            Query<TeacherEntity> query = session.createQuery(hql);
            query.setParameter("id", id);
            TeacherEntity t = query.uniqueResult();
            return t;
        });
    }

    @Override
    public List<TeacherEntity> selectAll() {
        return o.selectAll((session, teacherEntity) -> {
            String hql = "from TeacherEntity";
            Query<TeacherEntity> query = session.createQuery(hql, TeacherEntity.class);
            return query.list();
        });
    }

    public TeacherEntity selectByName(String name) {
        return o.selectBy(name, new DoInDO<String, TeacherEntity>() {
            @Override
            public TeacherEntity dosomething(Session session, String s) throws Exception {
                String hql = "from TeacherEntity where name = :name";
                Query<TeacherEntity> query = session.createQuery(hql);
                query.setParameter("name", name);
                TeacherEntity t = query.uniqueResult();
                return t;
            }
        });
    }

    /**
     * 登录验证的同时将密码修改为结果回传
     * @param name
     * @param password
     * @return
     */
    public TeacherEntity verify(String name, String password) {
        TeacherEntity t = null;
        t = selectByName(name);
        if (t != null) {
            if (!password.equals(t.getPassword())) {
                t.setPassword("FAILED");
            } else if (password.equals(t.getPassword())) {
                t.setPassword("SUCCESS");
            }
        }
        return t;
    }
}
