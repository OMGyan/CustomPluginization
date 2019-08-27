package com.yx.custompluginization;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

/**
 * Author by YX, Date on 2019/8/27.
 * 所有插件apk的页面必须实现的接口类(规范插件apk的activity标准) 面向接口开发
 */
public interface IPluginRule {
    /**
     * 注入上下文
     * @param mActivity
     */
    public void attach(Activity mActivity);

    public void onResume();

    public void onPause();

    public void onDestroy();

    public void onStart();

    public void onStop();

    public void onSaveInstanceState(Bundle outState);

    public void onCreate(Bundle savedInstanceState);

    public void onBackPressed();

    public boolean onTouchEvent(MotionEvent event);

}
