package com.spacexapidemo.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.spacexapidemo.presentation.ui.viewmodel.LaunchDetailsViewModel
import com.spacexapidemo.presentation.ui.viewmodel.RocketListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(RocketListViewModel::class)
    abstract fun bindRocketViewModel(rocketViewModel: RocketListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LaunchDetailsViewModel::class)
    abstract fun bindLaunchDetailViewModel(launchDetailViewModel: LaunchDetailsViewModel): ViewModel
}