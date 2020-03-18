package com.sujit.zomatoapidemo.di.component;

import android.app.Application;

import com.sujit.zomatoapidemo.ZomatoDemoApplication;
import com.sujit.zomatoapidemo.di.module.ActivityModule;
import com.sujit.zomatoapidemo.di.module.ApiModule;
import com.sujit.zomatoapidemo.di.module.ViewModelModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;


@Component(modules = {
        ViewModelModule.class,
        ActivityModule.class,
        ApiModule.class,
        AndroidSupportInjectionModule.class,
        AndroidInjectionModule.class
})
@Singleton
public interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();

    }

    void inject(ZomatoDemoApplication zomatoDemoApplication);
}
