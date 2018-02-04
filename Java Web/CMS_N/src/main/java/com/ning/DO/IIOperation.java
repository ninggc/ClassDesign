package com.ning.DO;

import java.util.List;

/**
 * Created by ning on 2017/7/4.
 */
public interface IIOperation<T> {
    /**
     *
     * @return true|false
     */
    public boolean insert(T t, DoInDO<T, Boolean> doInDO);

    /**
     *
     * @return true|false
     */
    public boolean delete(int id, DoInDO<Integer, Boolean> doInDO);

    /**
     * 匹配相同id项进行更新数据
     * @return true|false
     */
    public boolean update(T t, DoInDO<T, Boolean> doInDO);

    /**
     *
     * @return T|null
     */
    public T selectById(int id, DoInDO<Integer, T> doInDO);

    /**
     *
     * @return List|null|length = 0
     */
    public List<T> selectAll(DoInDO<T, List<T>> doInDO);
}
