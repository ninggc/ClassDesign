package com.ning.enterprise.factory;

import android.os.Handler;
import android.os.Message;

import com.google.gson.Gson;
import com.ning.enterprise.info.User;

/**
 * Created by server on 2017/4/15.
 */
public class TransferUser {
    class OkhttpFactory extends com.ning.enterprise.factory.OkhttpFactory {

        @Override
        public void onResponse(Message msg) {

        }
    }

    private OkhttpFactory okhttpFactory = new OkhttpFactory();
    private Gson gson = new Gson();

    public void login(Handler handler, String json) {
        okhttpFactory.transfer(handler, json, "login");
    }

    public void searchByMark(Handler handler, String mark) {
        okhttpFactory.transfer(handler, mark, "searchUserByMark");
    }

    public void register(Handler handler, User user) {
        okhttpFactory.transfer(handler, gson.toJson(user), "addUser");
    }

    public void searchByAccount(Handler handler, String account) {
        okhttpFactory.transfer(handler, account, "searchUserByAccount");
    }
}
