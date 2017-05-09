package com.madchen.testsamsunglive;

import android.content.ContentValues;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.PermissionRequest;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    private CompoundButton mCompoundButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        ContentValues cv = new ContentValues();
//        cv.put("autoRun",true);
//        Uri a = Uri.parse("content://com.samsung.android.sm");
//        getContentResolver().update(Uri.withAppendedPath(a,"AppFreezer"),
//                cv,"isAppOptTarget=? AND package_name=?", new String[] { "1",getApplicationInfo().packageName});
       mCompoundButton = (CompoundButton) findViewById(R.id.btn);
        smClassLoader();

    }

    private void smClassLoader() {
        try {
            Context context = createPackageContext("com.samsung.android.sm", CONTEXT_INCLUDE_CODE | CONTEXT_IGNORE_SECURITY);
            Class<?> mResolverClass = Class.forName("com.samsung.android.sm.ui.ram.i", true, context.getClassLoader());
            Class<?> fClass = Class.forName("com.samsung.android.sm.ui.ram.f", true, context.getClassLoader());
            Constructor<?> constructor = mResolverClass.getDeclaredConstructor(fClass);
            constructor.setAccessible(true);
            Method[] methods = mResolverClass.getDeclaredMethods();
            Log.d("MainActivity", "-----className---- " + mResolverClass.getName());
            for (Method method : methods) {
                Log.d("MainActivity", "---name---" + method.getName());
            }
            Method method = mResolverClass.getDeclaredMethod("a", ApplicationInfo.class, boolean.class);
            method.setAccessible(true);
            method.invoke(mResolverClass.newInstance(), getApplicationInfo(), true);

//            Method anotherMethod = mResolverClass.getDeclaredMethod("onCheckedChanged", CompoundButton.class,boolean.class);
//            anotherMethod.setAccessible(true);
//            anotherMethod.invoke(mResolverClass.newInstance(), mCompoundButton,true);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
