package com.spacexapidemo.di.component

import android.app.Application
import com.spacexapidemo.di.modules.ActivityModule
import com.spacexapidemo.di.modules.AppModule
import com.spacexapidemo.di.modules.ViewModelModule
import com.spacexapidemo.di.modules.rockets.LaunchModule
import com.spacexapidemo.di.modules.rockets.RocketsModule
import com.spacexapidemo.presentation.SpaceXApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityModule::class,
        ViewModelModule::class,
        AppModule::class,
        RocketsModule::class,
        LaunchModule::class
    ]
)
interface AppComponent : AndroidInjector<SpaceXApplication> {
    //    @Component.Builder
//    abstract class Builder : AndroidInjector.Builder<SpaceXApplication>()
    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun application(application: Application): Builder
    }

    fun inject(application: Application)
}
