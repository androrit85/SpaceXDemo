package com.spacexapidemo.di.modules

import com.spacexapidemo.di.modules.rockets.FragmentModule
import com.spacexapidemo.presentation.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeMainActivity(): MainActivity
}