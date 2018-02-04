package com.ning.DO;

import org.hibernate.Session;

/**
 * Created by ning on 2017/7/4.
 */
public interface DoInDO<T, V> {
    /**
     *
     * @param session 用于操作的session
     * @param t 传递的参数key
     * @return
     * @throws Exception catch by IIOperation
     */
    public V dosomething(Session session, T t) throws Exception;
}
