package com.spacexapidemo.data.datasource.remote.launches

import com.spacexapidemo.data.APIService
import com.spacexapidemo.data.common.applyIoScheduler
import com.spacexapidemo.data.mapper.map
import com.spacexapidemo.domain.entity.LaunchEntity
import io.reactivex.Single

class LaunchesRemoteDataSourceImpl(private val api: APIService): LaunchesRemoteDataSource {
    override fun getLaunches(id: String): Single<List<LaunchEntity.Launches>> = api.getLaunches(id)
        .applyIoScheduler()
        .map { item ->
            item.map {
                it.map()
            }
        }
}