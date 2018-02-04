package com.ning.DO;

import com.ning.DAO.ClassroomEntity;
import com.ning.DAO.HireEntity;
import com.ning.DAO.TeacherEntity;
import com.ning.DAO.TimeEntity;
import com.ning.factory.MySessionFactory;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by ning on 2017/7/4.
 */
public class HireOperation implements IOperation<HireEntity> {
    Operation<HireEntity> o = new Operation<>();

    @Override
    public boolean delete(int id) {
        return o.delete(id, (session, integer) -> {
            HireEntity c = new HireEntity();
            c.setId(id);
            session.delete(c);
            return true;
        });
    }

    @Override
    public HireEntity selectById(int id) {
        return o.selectById(id, (session, integer) -> {
            String hql = "from HireEntity where id = :id";
            Query<HireEntity> query = session.createQuery(hql);
            query.setParameter("id", id);
            List<HireEntity> c = query.list();
            return c.get(0);
        });
    }

    public List<HireEntity> selectAll() {
        return o.selectAll((session, hireEntity) -> {
            String hql = "from HireEntity ";
            Query<HireEntity> query = session.createQuery(hql, HireEntity.class);
            return query.list();
        });
    }

    @Deprecated
    public int selectByForeignKey(int classroom_id, int teacher_id) {
        int result = -1;
        String sql = "select * from hire where classroom_id=" + classroom_id + " and teacher_id=" + teacher_id;
        SQLQuery query = MySessionFactory.getSession().createSQLQuery(sql);
        query.addEntity("id", HireEntity.class);
        try {
            result = ((HireEntity) query.uniqueResult()).getId();
        } catch (Exception e) {
            result = ((HireEntity) query.list().get(0)).getId();
        }
        return result;
    }

    public List<TimeEntity> selectWhichBeHired(int teacher_id, int classroom_id) {
        return o.query(new DoInDO<String, List<TimeEntity>>() {
            @Override
            public List<TimeEntity> dosomething(Session session, String s) throws Exception {
                String hql = "select time from HireEntity where classroom_id = :classroom_id and teacher_id = :teacher_id";
                Query<TimeEntity> query = session.createQuery(hql, TimeEntity.class);
                query.setParameter("teacher_id", teacher_id);
                query.setParameter("classroom_id", classroom_id);
                return query.list();
            }
        });
    }

    public boolean hireByTeacher(TeacherEntity teacher, ClassroomEntity classroom, TimeEntity time) {
        if (teacher == null || classroom == null || time == null) {
            return false;
        }

        HireEntity hire = new HireEntity();
        hire.setClassroom(classroom);
        hire.setTeacher(teacher);
        hire.setTime(time);
        return insert(hire);
    }

    public boolean hireByTeacher(int teacher_id, int classroom_id, TimeEntity time) {
        return o.query(new DoInDO<String, Boolean>() {
            @Override
            public Boolean dosomething(Session session, String s) throws Exception {
                Query<TeacherEntity> queryTeacher = session.createQuery("from TeacherEntity where id = :id", TeacherEntity.class);
                queryTeacher.setParameter("id", teacher_id);
                Query<ClassroomEntity> queryClassroom = session.createQuery("from ClassroomEntity where id = :id", ClassroomEntity.class);
                queryClassroom.setParameter("id", classroom_id);

                HireEntity hire = new HireEntity();
                hire.setClassroom(queryClassroom.uniqueResult());
                hire.setTeacher(queryTeacher.uniqueResult());

                String hql = "select max(id) from TimeEntity";
                Query query = session.createQuery(hql);
                if (time.getId() == 0) {
                    time.setId((Integer) query.uniqueResult() + 1);
                }
                hire.setTime(time);

                session.save(time);
                session.save(hire);
                return true;
            }
        });
    }


    public List<HireEntity> selectByClassroom(int classroom_id) {
        return o.selectListBy(String.valueOf(classroom_id), new DoInDO<String, List<HireEntity>>() {
            @Override
            public List<HireEntity> dosomething(Session session, String s) throws Exception {
                String hql = "from HireEntity where classroom.id = :classroom_id";
                Query<HireEntity> query = session.createQuery(hql, HireEntity.class);
                query.setParameter("classroom_id", classroom_id);
                List<HireEntity> list = query.list();
                return list;
            }
        });
    }

    public List<HireEntity> selectByTeacher(int teacher_id) {
        return o.selectListBy(String.valueOf(teacher_id), new DoInDO<String, List<HireEntity>>() {
            @Override
            public List<HireEntity> dosomething(Session session, String s) throws Exception {
                String hql = "from HireEntity where teacher.id = :id";
                Query<HireEntity> query = session.createQuery(hql, HireEntity.class);
                query.setParameter("id", teacher_id);
                List<HireEntity> list = query.list();
                return list;
            }
        });
    }
}
