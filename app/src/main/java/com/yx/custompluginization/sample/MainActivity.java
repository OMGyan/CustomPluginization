package com.yx.custompluginization.sample;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yx.custompluginization.PluginManager;
import com.yx.custompluginization.ProxyActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE},100);
    }

    public void goActivity(View view) {
        //设置框架上下文
        PluginManager.getInstance().setContext(getApplicationContext());
        //动态加载插件apk
        PluginManager.getInstance().loadPath(Environment.getExternalStorageDirectory().getAbsolutePath()+"/login.apk");
        Intent intent = new Intent(this, ProxyActivity.class);
        //拿到插件apk的入口activity的名字
        String activityName = PluginManager.getInstance().getPackageInfo().activities[0].name;
        intent.putExtra("activityName",activityName);
        startActivity(intent);
    }


}
