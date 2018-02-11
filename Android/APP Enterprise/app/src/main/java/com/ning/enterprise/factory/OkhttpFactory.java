package com.ning.enterprise.factory;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import com.ning.enterprise.info.GlobalVar;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Mr.Ning on 2017/4/8.
 * 继承该工厂类以使用okhttp
 */
public abstract class OkhttpFactory {
    private final String TAG = this.getClass().getName();

    private final static int CONNECT_TIMEOUT  = 10;
    private final static int READ_TIMEOUT  = 5;
    private final static int WRITE_TIMEOUT  = 5;

    private static int serverLoadTimes = 1;
    private final int maxLoadTime = 2;

    public static String url = GlobalVar.url;

    private MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private OkHttpClient client;    //创建okhttp实例
    private Request request;
    private RequestBody body;

    public static final int SUCCESS = 1;
    public static final int FAILED = 0;

    private Message msg;

    public OkhttpFactory() {
        this.client = new OkHttpClient.Builder()
                .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
                .build();
    }

    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            onResponse(msg);
        }
    };

    private void resetServerLoadTimes() {
        this.serverLoadTimes = 1;
    }

    //重写函数以进行UI操作

    /**
     * 请求回应UI操作
     * @param msg 请求UI的Message
     */
    public abstract void onResponse(Message msg);

    public void send(String url, String json) {
        msg = new Message();
        body = RequestBody.create(JSON, json);
        request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //如果超时并未超过指定次数，则重新连接
                if(serverLoadTimes < maxLoadTime) {
                    ++serverLoadTimes;
                    Log.e(TAG, "网络重连中+" + serverLoadTimes);
                    client.newCall(call.request()).enqueue(this);
                } else {
                    resetServerLoadTimes();
                    msg.what = FAILED;
                    msg.obj = e.getMessage();
                    handler.sendMessage(msg);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                resetServerLoadTimes();

                if (response.isSuccessful()) {
                    String json = response.body().string();
                    msg.what = SUCCESS;
                    msg.obj = json;
                    handler.sendMessage(msg);
                } else {
                    msg.what = FAILED;
                    msg.obj = "Not success!";
                    handler.sendMessage(msg);
                }
            }
        });
    }

    @Deprecated
    public void transfer(final Handler handle, String json, final String type) {
        final Message msg = new Message();
        final Bundle data = new Bundle();

        body = RequestBody.create(JSON, json);
        request = new Request.Builder()
                .url(url + type)
                .post(body)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                data.putString("key", type);
                //SocketTimeoutException.class.equals(e.getCause()) &&

                if(serverLoadTimes < maxLoadTime)//如果超时并未超过指定次数，则重新连接
                {
                    ++serverLoadTimes;
                    Log.e(TAG, "网络重连中+" + serverLoadTimes);
                    client.newCall(call.request()).enqueue(this);
                }else {
                    serverLoadTimes = 1;
                    Log.e(TAG, e.getMessage()+e.getCause());
                    data.putString("key", "netError");
                    Log.e(TAG, "netError");
                    data.putString("value", "没有网络或服务器故障！");
                    msg.setData(data);
                    handle.sendMessage(msg);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                data.putString("key", type);
                serverLoadTimes = 1;

                if (response.isSuccessful()) {

                    String json = response.body().string();

                    Log.e("tag", json);
                    data.putString("value", json);
                    msg.setData(data);
                    handle.sendMessage(msg);
                } else {
                    data.putString("value", "失败了");
                }
            }
        });
    }
}
