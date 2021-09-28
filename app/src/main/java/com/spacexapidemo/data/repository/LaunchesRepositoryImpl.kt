package com.spacexapidemo.data.repository

import com.spacexapidemo.data.common.applyIoScheduler
import com.spacexapidemo.data.datasource.remote.launches.LaunchesRemoteDataSource
import com.spacexapidemo.domain.common.ResultState
import com.spacexapidemo.domain.entity.LaunchEntity
import com.spacexapidemo.domain.repository.LaunchesRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Repository class to fetch all launches for particular rocket
 */
class LaunchesRepositoryImpl @Inject constructor(private val remoteDataSource: LaunchesRemoteDataSource) :
    LaunchesRepository {
    override fun getLaunches(id: String): Single<ResultState<List<LaunchEntity.Launches>>> {
        return remoteDataSource.getLaunches(id)
            .applyIoScheduler()
            .map {
                ResultState.Success(it.filter { launches ->
                    launches.rocketId == id
                }) as ResultState<List<LaunchEntity.Launches>>
            }
            .onErrorReturn { e ->
                ResultState.Error(e, null)
            }
    }
}