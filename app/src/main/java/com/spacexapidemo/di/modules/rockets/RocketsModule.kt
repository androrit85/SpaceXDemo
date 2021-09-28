package com.spacexapidemo.di.modules.rockets

import com.spacexapidemo.data.APIService
import com.spacexapidemo.data.common.prefs.SharedPreferencesHelper
import com.spacexapidemo.data.datasource.remote.rockets.RocketsRemoteDataSource
import com.spacexapidemo.data.datasource.remote.rockets.RocketsRemoteDataSourceImpl
import com.spacexapidemo.data.repository.LaunchesRepositoryImpl
import com.spacexapidemo.data.repository.RocketsRepositoryImpl
import com.spacexapidemo.domain.repository.LaunchesRepository
import com.spacexapidemo.domain.repository.RocketsRepository
import com.spacexapidemo.domain.usecase.launches.LaunchesUseCase
import com.spacexapidemo.domain.usecase.launches.LaunchesUseCaseImpl
import com.spacexapidemo.domain.usecase.rockets.RocketsUseCase
import com.spacexapidemo.domain.usecase.rockets.RocketsUseCaseImpl
import dagger.Module
import dagger.Provides

@Module
class RocketsModule {

    @Provides
    fun provideRemoteDataSource(api: APIService): RocketsRemoteDataSource =
        RocketsRemoteDataSourceImpl(api)

    @Provides
    fun provideRepository(remoteDataSource: RocketsRemoteDataSource, prefsHelper: SharedPreferencesHelper): RocketsRepository =
        RocketsRepositoryImpl(remoteDataSource, prefsHelper)

    @Provides
    fun provideRocketsUseCaseImpl(repository: RocketsRepository): RocketsUseCase =
        RocketsUseCaseImpl(repository)
}