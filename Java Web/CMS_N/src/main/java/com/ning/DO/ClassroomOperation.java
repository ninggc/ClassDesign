package com.ning.DO;

import com.ning.DAO.ClassroomEntity;
import com.ning.DAO.DayEntity;
import com.ning.DAO.TeacherEntity;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ning on 2017/7/4.
 */
public class ClassroomOperation implements IOperation<ClassroomEntity> {
    Operation<ClassroomEntity> o = new Operation<>();

    @Override
    public boolean delete(int id) {
        return o.delete(id, (session, integer) -> {
            ClassroomEntity c = new ClassroomEntity();
            c.setId(id);
            session.delete(c);
            return true;
        });
    }

    @Override
    public ClassroomEntity selectById(int id) {
        return o.selectById(id, (session, integer) -> {
            String hql = "from ClassroomEntity where id = :id";
            Query<ClassroomEntity> query = session.createQuery(hql);
            query.setParameter("id", id);
            ClassroomEntity c = query.uniqueResult();
            return c;
        });
    }

    public List<ClassroomEntity> selectAll() {
        return o.selectAll((session, classroomEntity) -> {
            String hql = "from ClassroomEntity";
            Query<ClassroomEntity> query = session.createQuery(hql, ClassroomEntity.class);
            return query.list();
        });
    }

    @Deprecated
    public List<Integer> selectHireDayById(int id) {
        ClassroomEntity c = selectById(id);
        ArrayList<Integer> teacherIds = new ArrayList<>();
        ArrayList<Integer> dayIds = new ArrayList<>();
//        if (c == null || c.getTeachers().size() == 0) {
//            return null;
//        }
//        for (TeacherEntity t : c.getTeachers()) {
//            teacherIds.add(t.getId());
//        }
        HireOperation ho = new HireOperation();
        for (int teacher_id : teacherIds) {
            dayIds.add(ho.selectByForeignKey(id, teacher_id));
        }
        return dayIds;
    }
}
