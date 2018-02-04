package com.ning.DO;

import com.ning.DAO.IEntity;
import com.ning.factory.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by ning on 2017/7/4.
 * 抽象画的描述了对数据库的增删改查操作
 * 通过DoInDo接口实现用户自定义操作
 */
public class Operation<T extends IEntity> implements IIOperation<T> {

    Session session;
    Transaction transaction;
    T t;

    @Override
    public boolean insert(T t, DoInDO<T, Boolean> doInDO) {
        boolean flag = false;
        try {
            session = MySessionFactory.getSession();
            transaction = session.beginTransaction();
            flag = doInDO.dosomething(session, t);
            transaction.commit();
        } catch (Exception e) {
            flag = false;
            e.printStackTrace();
            transaction.rollback();
        } finally {
            if (session != null && session.isConnected()) {
                session.close();
            }
        }
        return flag;
    }

    @Override
    public boolean delete(int id, DoInDO<Integer, Boolean> doInDO) {
        boolean flag = false;
        try {
            session = MySessionFactory.getSession();
            transaction = session.beginTransaction();
            flag = doInDO.dosomething(session, id);
            transaction.commit();
        } catch (Exception e) {
            flag = false;
            System.out.println(e.getMessage());
            transaction.rollback();
        } finally {
            if (session != null && session.isConnected()) {
                session.close();
            }
        }
        return flag;
    }

    @Override
    public boolean update(T t, DoInDO<T, Boolean> doInDO) {
        boolean flag = false;
        try {
            session = MySessionFactory.getSession();
            transaction = session.beginTransaction();
            flag = doInDO.dosomething(session, t);
            transaction.commit();
        } catch (Exception e) {
            flag = false;
            System.out.println(e.getMessage());
            transaction.rollback();
        } finally {
            if (session != null && session.isConnected()) {
                session.close();
            }
        }
        return flag;
    }

    @Override
    public T selectById(int id, DoInDO<Integer, T> doInDO) {
        T t = null;
        try {
            session = MySessionFactory.getSession();
            transaction = session.beginTransaction();
            t = doInDO.dosomething(session, id);
            transaction.commit();
        } catch (Exception e) {
            t = null;
            e.printStackTrace();
            transaction.rollback();
        } finally {
            if (session != null && session.isConnected()) {
                session.close();
            }
        }
        return t;
    }

    public T selectBy(String key, DoInDO<String, T> doInDO) {
        T t = null;
        try {
            session = MySessionFactory.getSession();
            transaction = session.beginTransaction();
            t = doInDO.dosomething(session, key);
            transaction.commit();
        } catch (Exception e) {
            t = null;
            System.out.println(e);
            transaction.rollback();
        } finally {
            if (session != null && session.isConnected()) {
                session.close();
            }
        }
        return t;
    }

    /**
     * 获取session后自定义操作吧
     * @param doInDO
     * @param <R>
     * @return
     */
    public <R> R query(DoInDO<String, R> doInDO) {
        R r = null;
        try {
            session = MySessionFactory.getSession();
            transaction = session.beginTransaction();
            r = doInDO.dosomething(session, "");
            transaction.commit();
        } catch (Exception e) {
            r = null;
            e.printStackTrace();
            transaction.rollback();
        } finally {
            if (session != null && session.isConnected()) {
                session.close();
            }
        }
        return r;
    }

    /**
     * 获取session后自定义操作吧
     * @param inSession
     * @param <R> 返回值
     * @return
     */
    public <R> R query(InSession<R> inSession) {
        R r = null;
        try {
            session = MySessionFactory.getSession();
            transaction = session.beginTransaction();
            r = inSession.onQuery(session);
            transaction.commit();
        } catch (Exception e) {
            r = null;
            e.printStackTrace();
            transaction.rollback();
        } finally {
            if (session != null && session.isConnected()) {
                session.close();
            }
        }
        return r;
    }

    public List<T> selectListBy(String key, DoInDO<String, List<T>> doInDO) {
        List<T> list = null;
        try {
            session = MySessionFactory.getSession();
            transaction = session.beginTransaction();
            list = doInDO.dosomething(session, key);
            transaction.commit();
        } catch (Exception e) {
            t = null;
            e.printStackTrace();
            transaction.rollback();
        } finally {
            if (session != null && session.isConnected()) {
                session.close();
            }
        }
        return list;
    }

    @Override
    public List<T> selectAll(DoInDO<T, List<T>> doInDO) {
        List<T> list = null;
        try {
            session = MySessionFactory.getSession();
            transaction = session.beginTransaction();
            list = doInDO.dosomething(session, null);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            if(session  != null && session.isConnected()) {
                session.close();
            }
        }
        return list;
  }
}
