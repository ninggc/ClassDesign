package com.ning.DAO;

import com.google.gson.Gson;

/**
 * Created by ning on 2017/7/4.
 */
public interface IEntity {
    Gson gson = new Gson();

    String toJSON();

//    public default String toJSON() {
//        if (this == null) {
//            return "null";
//        } else {
//            return new Gson().toJSON(this);
//        }
//    }

}
