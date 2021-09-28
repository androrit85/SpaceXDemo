package com.spacexapidemo.di.modules.rockets

import com.spacexapidemo.presentation.ui.view.launches.LaunchDetailsFragment
import com.spacexapidemo.presentation.ui.view.rocket.RocketListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun rocketListFragment(): RocketListFragment

    @ContributesAndroidInjector
    abstract fun launchDetailsFragment(): LaunchDetailsFragment
}