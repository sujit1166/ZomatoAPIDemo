package com.sujit.zomatoapidemo;

import android.app.Activity;
import android.app.Application;


import com.sujit.zomatoapidemo.di.component.DaggerAppComponent;

import javax.inject.Inject;

import androidx.fragment.app.Fragment;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.HasFragmentInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class ZomatoDemoApplication extends Application implements HasActivityInjector, HasFragmentInjector, HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    @Inject
    DispatchingAndroidInjector<android.app.Fragment> fragmentInjector;

    @Inject
    DispatchingAndroidInjector<Fragment> supportFragmentInjector;


    @Override
    public void onCreate() {
        super.onCreate();
        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this);
    }


    @Override
    public DispatchingAndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }

    @Override
    public AndroidInjector<android.app.Fragment> fragmentInjector() {
        return fragmentInjector;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return supportFragmentInjector;
    }
}