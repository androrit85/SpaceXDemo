package com.spacexapidemo.di.modules.rockets

import com.spacexapidemo.data.APIService
import com.spacexapidemo.data.datasource.remote.launches.LaunchesRemoteDataSource
import com.spacexapidemo.data.datasource.remote.launches.LaunchesRemoteDataSourceImpl
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
class LaunchModule {

    @Provides
    fun provideLaunchDataSource(api: APIService): LaunchesRemoteDataSource =
        LaunchesRemoteDataSourceImpl(api)

    @Provides
    fun provideLaunchRepository(remoteDataSource: LaunchesRemoteDataSource): LaunchesRepository =
        LaunchesRepositoryImpl(remoteDataSource)

    @Provides
    fun provideLaunchUseCaseImpl(repository: LaunchesRepository): LaunchesUseCase =
        LaunchesUseCaseImpl(repository)
}