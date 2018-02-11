package com.ning.enterprise.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ning.enterprise.info.User;
import com.ning.enterprise.factory.TransferUser;
import com.tencent.connect.UserInfo;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;


/**
 * Created by server on 2017/4/9.
 */
public class LoginAty extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();
    enum Code {
        REGISTER
    }

    private static final String APPID = "1106006239";
    private Tencent mTencent;
//    private IWXAPI mWeixinAPI;

    private TransferUser transferUser = new TransferUser();
    private User user;
    //用户账号标识，唯一
    private String uniqueMark;

    Button btn_QQlogin;
    Button btn_register;
    TextView tv_register;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_login);

        init();
    }

    void init() {
        mTencent = Tencent.createInstance(APPID, this.getApplicationContext());

        btn_QQlogin = (Button) findViewById(R.id.btn_QQlogin);
        btn_register = (Button) findViewById(R.id.btn_register);
        tv_register = (TextView) findViewById(R.id.tv_register);

        btn_QQlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginListener myListener = new LoginListener();

                if (!mTencent.isSessionValid()){
                    mTencent.login(LoginAty.this, "all", myListener);
                } else {
                    mTencent.login(LoginAty.this, "all", myListener);
                }
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivityForResult(new Intent(LoginAty.this, RegisterAty.class), Code.REGISTER.ordinal());
                Toast.makeText(LoginAty.this, "Has not been finished.", Toast.LENGTH_SHORT).show();;
            }
        });

        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                SMSSDK.initSDK(LoginAty.this, "187677ab046b6", "7250667a4b917316544f131dbb80b3de");
//
//
//                RegisterPage registerPage = new RegisterPage();
//                registerPage.setRegisterCallback(new EventHandler() {
//                    public void afterEvent(int event, int result, Object data) {
//                        // 解析注册结果
//                        if (result == SMSSDK.RESULT_COMPLETE) {
//                            @SuppressWarnings("unchecked")
//                            HashMap<String,Object> phoneMap = (HashMap<String, Object>) data;
//                            String country = (String) phoneMap.get("country");
//                            String phone = (String) phoneMap.get("phone");
//
//                            // 提交用户信息（此方法可以不调用）
//                            //registerUser(country, phone);
//                        }
//                    }
//                });
//                registerPage.show(LoginAty.this);
            }
        });
    }

//    private void loginWithWeixin() {
//        if (mWeixinAPI == null) {
//            mWeixinAPI = WXAPIFactory.createWXAPI(this, WEIXIN_APP_ID, false);
//        }
//
//        if (!mWeixinAPI.isWXAppInstalled()) {
//            Toast.makeText(LoginAty.this, "请安装微信", Toast.LENGTH_SHORT);
//            return;
//        }
//
//        mWeixinAPI.registerApp(WEIXIN_APP_ID);
//
//        SendAuth.Req req = new SendAuth.Req();
//        req.scope = WEIXIN_SCOPE;
//        req.state = WEIXIN_STATE;
//        mWeixinAPI.sendReq(req);
//    }

    //用户获取执行网络操作后的操作
    Handler loginHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle data = msg.getData();

            String value = data.getString("value");
            Log.e(TAG, data.getString("key") + value);

            switch (data.getString("key")) {
                case "loginSuccess":
                    getUserInfo();
                    break;
                case "getQQReader":
                    Log.e(TAG, user.getMark());
                    transferUser.searchByMark(loginHandler, user.getMark());
                    break;
                case "searchUserByMark":
//                    onServerLogin(value);
                    if ("null".equals(value)) {
                        transferUser.register(loginHandler, user);
                    } else {
                        Log.e(TAG, "handleMessage: " + value);
                        toIndex();
                    }
                    break;
                case "addReader":
                    toIndex();
                    break;
                case "netError":
                    Toast.makeText(LoginAty.this, "服务器故障，请联系客服反映该问题。·", Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };

//    public abstract void onQQLogin(ILogin login);
//    public abstract void onServerLogin(String result);
//
//    public void onResponse(ILogin iLogin) {
//        iLogin.onFailure(new Exception());
//        iLogin.onResponse();
//
//    }

    private void toIndex() {
        SharedPreferences.Editor editor = getSharedPreferences("center", MODE_PRIVATE).edit();
        editor.putString("user", user.toJSON());
        editor.commit();

        Intent i = new Intent(LoginAty.this, MainActivity.class);
        Bundle data = new Bundle();
        data.putString("key", "login");
        data.putString("value", new Gson().toJson(user).toString());
        i.putExtras(data);
        Intent resultIntent = new Intent();
        resultIntent.putExtras(data);
        this.setResult(RESULT_OK, resultIntent);
        this.finish();
    }

    private void getUserInfo() {
        UserInfo userInfo = new UserInfo(LoginAty.this, mTencent.getQQToken());
        userInfo.getUserInfo(new UseInfoListener());
    }

    private class LoginListener implements IUiListener {

        @Override
        public void onCancel() {
            // TODO Auto-generated method stub
            LoginAty.this.toast("登录取消");
        }

        @Override
        public void onComplete(Object value) {
            // TODO Auto-generated method stub
            //登录成功后可以获取到用户openID和token等信息
//            LoginAty.this.toast(value.toString());

            /**
             * 返回json数据样例
             *
             * {"ret":0,"pay_token":"D3D678728DC580FBCDE15722B72E7365",
             * "pf":"desktop_m_qq-10000144-android-2002-",
             * "query_authority_cost":448,
             * "authority_cost":-136792089,
             * "openid":"015A22DED93BD15E0E6B0DDB3E59DE2D",
             * "expires_in":7776000,
             * "pfkey":"6068ea1c4a716d4141bca0ddb3df1bb9",
             * "msg":"",
             * "access_token":"A2455F491478233529D0106D2CE6EB45",
             * "login_cost":499}
             */
            try {
                JSONObject jo = (JSONObject) value;

                int ret = jo.getInt("ret");

                if (ret == 0) {

                    String openID = jo.getString("openid");
                    uniqueMark = openID;
                    String accessToken = jo.getString("access_token");
                    String expires = jo.getString("expires_in");
                    mTencent.setOpenId(openID);
                    mTencent.setAccessToken(accessToken, expires);

//                    Toast.makeText(LoginAty.this, "登录成功", Toast.LENGTH_SHORT).show();
                    Bundle data = new Bundle();
                    data.putString("key", "loginSuccess");
                    data.putString("value", "next");
                    Message msg = new Message();
                    msg.setData(data);
                    loginHandler.sendMessage(msg);

                }

            } catch (Exception e) {
                // TODO: handle exception
            }
        }

        @Override
        public void onError(UiError arg0) {
            // TODO Auto-generated method stub
            LoginAty.this.toast("登录出错");
        }

    }

    private class UseInfoListener implements IUiListener {
        @Override
        public void onComplete(Object o) {

            try {
                /**
                 * 返回用户信息样例
                 *
                 * {"is_yellow_year_vip":"0","ret":0,
                 * "figureurl_qq_1":"http:\/\/q.qlogo.cn\/qqapp\/1104732758\/015A22DED93BD15E0E6B0DDB3E59DE2D\/40",
                 * "figureurl_qq_2":"http:\/\/q.qlogo.cn\/qqapp\/1104732758\/015A22DED93BD15E0E6B0DDB3E59DE2D\/100",
                 * "nickname":"攀爬←蜗牛","yellow_vip_level":"0","is_lost":0,"msg":"",
                 * "city":"黄冈","
                 * figureurl_1":"http:\/\/qzapp.qlogo.cn\/qzapp\/1104732758\/015A22DED93BD15E0E6B0DDB3E59DE2D\/50",
                 * "vip":"0","level":"0",
                 * "figureurl_2":"http:\/\/qzapp.qlogo.cn\/qzapp\/1104732758\/015A22DED93BD15E0E6B0DDB3E59DE2D\/100",
                 * "province":"湖北",
                 * "is_yellow_vip":"0","gender":"男",
                 * "figureurl":"http:\/\/qzapp.qlogo.cn\/qzapp\/1104732758\/015A22DED93BD15E0E6B0DDB3E59DE2D\/30"}
                 */
                JSONObject jo = (JSONObject) o;
                    int ret = jo.getInt("ret");

                    String nickName = jo.getString("nickname");
                    String gender = jo.getString("gender");
                    String city = jo.getString("city");

                    User user = new User();
                    user.setAccount(String.valueOf(System.currentTimeMillis()));
                    user.setNickname(nickName);
                    user.setMark(uniqueMark);
                    user.setIntroduce("这个人很懒什么都没有留下。");

                    LoginAty.this.user = user;

                    Bundle data = new Bundle();
                    data.putString("key", "getQQReader");
                    data.putString("value", "next");
                    Message msg = new Message();
                    msg.setData(data);

                    loginHandler.sendMessage(msg);

                } catch (Exception e) {
                    // TODO: handle exception
                }
        }

        @Override
        public void onError(UiError uiError) {
            LoginAty.this.toast("error");
        }

        @Override
        public void onCancel() {
            LoginAty.this.toast("cancel");
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        LoginListener myListener = new LoginListener();
        Tencent.onActivityResultData(requestCode,resultCode,data,myListener);
    }

    private void toast(String str) {
        Toast.makeText(LoginAty.this, str, Toast.LENGTH_SHORT).show();
    }
}
