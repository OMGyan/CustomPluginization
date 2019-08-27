package com.yx.custompluginization;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Author by YX, Date on 2019/8/27.
 */
public class BaseActivity extends Activity implements IPluginRule{

    protected Activity that;

    @Override
    public void attach(Activity mActivity) {
        this.that = mActivity;
    }

    public void setContentView(View view){
        if(that == null){
            super.setContentView(view);
        }else {
            that.setContentView(view);
        }
    }

    public void setContentView(int Reslayout){
        that.setContentView(Reslayout);
    }

    @Override
    public <T extends View> T findViewById(int id) {
        return that.findViewById(id);
    }

    @Override
    public Intent getIntent() {
        return that.getIntent();
    }

    @Override
    public ClassLoader getClassLoader() {
        return that.getClassLoader();
    }

    @NonNull
    @Override
    public LayoutInflater getLayoutInflater() {
        return that.getLayoutInflater();
    }

    @Override
    public void startActivity(Intent intent) {
        Intent mIntent = new Intent();
        mIntent.putExtra("activityName",intent.getComponent().getClassName());
        that.startActivity(mIntent);
    }

    @Override
    public ApplicationInfo getApplicationInfo() {
        return that.getApplicationInfo();
    }

    @Override
    public Window getWindow() {
        return that.getWindow();
    }

    @Override
    public WindowManager getWindowManager() {
        return that.getWindowManager();
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onResume() {

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onPause() {

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onDestroy() {

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onStart() {

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onStop() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onCreate(Bundle savedInstanceState) {

    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }
}
