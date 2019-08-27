package com.yx.custompluginization;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;

/**
 * Author by YX, Date on 2019/8/27.
 * 代理activity
 *
 * 作用:当我们要跳转到插件apk的activity的时候，会先跳转到这里，
 * 再由当前代理activity动态加载目标activity的类对象
 * 最后去调用这个activity的相对应的生命周期方法
 */
public class ProxyActivity extends Activity {
    IPluginRule iPluginRule;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //真正的目的地activity的名字
        String activityName = getIntent().getStringExtra("activityName");
        try {
            //插件apk中目标类对象
            Class<?> aClass = PluginManager.getInstance().getDexClassLoader().loadClass(activityName);
            //实例化对象
            Object object = aClass.newInstance();
            if(object instanceof IPluginRule){
                iPluginRule = (IPluginRule) object;
                iPluginRule.attach(this);
                iPluginRule.onCreate(new Bundle());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Resources getResources() {
        return PluginManager.getInstance().getResources();
    }

    @Override
    public ClassLoader getClassLoader() {
        return PluginManager.getInstance().getDexClassLoader();
    }

    @Override
    public void startActivity(Intent intent) {
        String activityName = intent.getStringExtra("activityName");
        Intent intent1 = new Intent(this, ProxyActivity.class);
        intent1.putExtra("activityName",activityName);
        super.startActivity(intent1);
    }

    @Override
    protected void onStop() {
        super.onStop();
        iPluginRule.onStop();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        iPluginRule.onBackPressed();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        iPluginRule.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    protected void onStart() {
        super.onStart();
        iPluginRule.onStart();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        iPluginRule.onSaveInstanceState(outState);
    }



    @Override
    protected void onPause() {
        super.onPause();
        iPluginRule.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        iPluginRule.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iPluginRule.onDestroy();
    }
}
