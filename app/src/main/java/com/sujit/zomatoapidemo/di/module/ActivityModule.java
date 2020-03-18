package com.sujit.zomatoapidemo.di.module;



import com.sujit.zomatoapidemo.ui.home.HomeActivity;
import com.sujit.zomatoapidemo.ui.location.LocationActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {

    @ContributesAndroidInjector()
    abstract HomeActivity contributeHomeActivity();

    @ContributesAndroidInjector()
    abstract LocationActivity contributeLocationActivity();
}