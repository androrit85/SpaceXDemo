package com.spacexapidemo.data.datasource.remote.launches

import com.spacexapidemo.data.datasource.BaseDataSource
import com.spacexapidemo.domain.entity.LaunchEntity
import io.reactivex.Single

interface LaunchesRemoteDataSource: BaseDataSource {
    fun getLaunches(id: String): Single<List<LaunchEntity.Launches>>
}