package com.ning.DO;

import com.ning.DAO.IEntity;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by ning on 2017/7/3.
 */
public interface IOperation<T extends IEntity> {
    Operation o = new Operation<>();
    /**
     *
     * @return true|false
     */
    public default boolean insert(T t) {
        return o.insert(t, new DoInDO() {
            @Override
            public Object dosomething(Session session, Object o) throws Exception {
                session.save(o);
                return true;
            }
        });
    }

    /**
     *
     * @return true|false
     */
    public boolean delete(int id);

    /**
     * 匹配相同id项进行更新数据
     * @return true|false
     */
    public default boolean update(T t) {
        return o.update(t, new DoInDO<T, Boolean>() {
            @Override
            public Boolean dosomething(Session session, T t) throws Exception {
                session.update(t);
                return true;
            }
        });
    };

    /**
     *
     * @return T|null
     */
    public T selectById(int id);

    /**
     *
     * @return List|null
     */
    public List<T> selectAll();
}
