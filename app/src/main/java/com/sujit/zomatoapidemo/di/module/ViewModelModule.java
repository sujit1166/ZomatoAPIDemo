package com.sujit.zomatoapidemo.di.module;


import com.sujit.zomatoapidemo.di.ViewModelKey;
import com.sujit.zomatoapidemo.di.ZomatoDemoViewModelFactory;
import com.sujit.zomatoapidemo.ui.home.HomeActivityViewModel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ZomatoDemoViewModelFactory zomatoDemoViewModelFactory);

    @Binds
    @IntoMap
    @ViewModelKey(HomeActivityViewModel.class)
    protected abstract ViewModel bindHomeActivityViewModel(HomeActivityViewModel homeActivityViewModel);

}