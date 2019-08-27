package com.yx.custompluginization;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.File;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * Author by YX, Date on 2019/8/27.
 * 动态加载第三方插件apk的资源对象以及类加载器
 */
public class PluginManager {

    //插件apk的资源对象
    private Resources resources;
    //插件apk的类加载器
    private DexClassLoader dexClassLoader;
    //上下文
    private Context context;
    //插件apk的包信息类，用于获取所需activity的名字
    private PackageInfo packageInfo;

    public static PluginManager getInstance(){
        return SingleClass.pm;
    }

    private static class SingleClass{
        private static final PluginManager pm = new PluginManager();
    }

    private PluginManager() {

    }

    //首先设置上下文
    public void setContext(Context context) {
        this.context = context;
    }

    /**
     * 根据path动态加载第三方插件apk的资源对象及类加载器
     * @param path
     */
    public void loadPath(String path){
        //获取当前应用的私有存储路径
        File dir = context.getDir("dex",Context.MODE_PRIVATE);
        /**
         *获取到path下dex文件的类加载器
         *param1 就是apk的路径
         *param2 当前应用的私有存储路径
         *param3 library的路径
         *param4 父类classloader
         */
         dexClassLoader = new DexClassLoader(path,dir.getAbsolutePath(),null,context.getClassLoader());
         //获取包管理器
         PackageManager packageManager = context.getPackageManager();
        //通过包管理器获取path下dex文件的包信息类
         packageInfo = packageManager.getPackageArchiveInfo(path,PackageManager.GET_ACTIVITIES);
        try {
            //创建assetmanager
            AssetManager assetManager = AssetManager.class.newInstance();
            //通过反射获取方法
            Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);
            //执行方法
            addAssetPath.invoke(assetManager,path);

            resources = new Resources(assetManager,context.getResources().getDisplayMetrics(),context.getResources().getConfiguration());
        } catch (Exception e) {

        }
    }

    public Resources getResources() {
        return resources;
    }

    public DexClassLoader getDexClassLoader() {
        return dexClassLoader;
    }

    public PackageInfo getPackageInfo() {
        return packageInfo;
    }
}
