package com.ning.enterprise.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.ning.enterprise.info.User;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static String nickname = "";

    private final String TAG = getClass().getSimpleName();

    //used for startActivityForResult
    public static final int CODE_LOGIN = 0;
    public static final int CODE_CENTER = 1;
    SharedPreferences sp;
    SharedPreferences.Editor editor;


    ImageView iv_user;
    TextView tv_userName;
    TextView tv_welcome;

    User localUser = null;
    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initView();
    }

    void initView() {
        sp = getSharedPreferences("center", MODE_PRIVATE);
        editor = sp.edit();
//        tv_welcome = (TextView) findViewById(R.id.tv_welcome);

        if("".equals(sp.getString("user", ""))) {

        } else {
            String json = sp.getString("user", "");
            localUser = gson.fromJson(json, User.class);
            nickname = localUser.getNickname();
            Toast.makeText(MainActivity.this, "你好" + localUser.getNickname(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        iv_user = (ImageView) findViewById(R.id.iv_user);
        tv_userName = (TextView) findViewById(R.id.tv_userName);
        if(localUser != null) {
            tv_userName.setText(localUser.getNickname());
//            tv_welcome = (TextView) findViewById(R.id.tv_welcome);
//            tv_welcome.setText("你好" + localUser.getNickname());
            nickname = tv_userName.getText().toString();
        }

        iv_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (iv_user.getDrawable() == R.drawable.nouser) {
                if (localUser != null) {
//                    startActivityForResult(new Intent(MainActivity.this, LoginAty.class), CODE_LOGIN);
                    startActivityForResult(new Intent(MainActivity.this, UserCenterAty.class), CODE_CENTER);
                } else {
                    startActivityForResult(new Intent(MainActivity.this, LoginAty.class), CODE_LOGIN);
                }
            }
        });

        tv_userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!"点击登陆".equals(tv_userName.getText())) {
//                    Toast.makeText(MainActivity.this, "此处应转到个人中心界面", Toast.LENGTH_SHORT).show();
                } else {
                    startActivityForResult(new Intent(MainActivity.this, LoginAty.class), CODE_LOGIN);
                }
            }
        });

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

//        if (id == R.id.nav_camera) {
//            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {
//
//        } else if (id == R.id.nav_share) {

        if (id == R.id.nav_show) {
            startActivity(new Intent(MainActivity.this, ShowAty.class));
//        if (id == R.id.nav_share) {
//
//        } else if (id == R.id.nav_send) {

        } else if (id == R.id.nav_release) {
            startActivity(new Intent(MainActivity.this, EmployeeAty.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!(resultCode == RESULT_OK)) {
            Log.e(TAG, "onActivityResult: " + "failed");
        } else {
            Bundle d = null;
            switch (requestCode) {
                case CODE_LOGIN:
                    d = data.getExtras();
                    User user = gson.fromJson(d.getString("value"), User.class);
                    tv_userName.setText(user.getNickname());
//                    tv_welcome = (TextView) findViewById(R.id.tv_welcome);
//                    tv_welcome.setText("你好" + localUser.getNickname());
                    nickname = user.getNickname();
                    localUser = user;
                    break;
                case CODE_CENTER:
                    d = data.getExtras();
                    if ("exit".equals(d.get("key"))) {
                        localUser = null;
                        tv_userName.setText("点击登录");
                    }
                    break;
                default:
                    Log.e(TAG, "onActivityResult: " + "unknown");
                    break;
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
