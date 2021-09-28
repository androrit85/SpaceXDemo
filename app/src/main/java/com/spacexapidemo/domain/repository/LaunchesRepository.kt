package com.spacexapidemo.domain.repository

import com.spacexapidemo.domain.common.ResultState
import com.spacexapidemo.domain.entity.LaunchEntity
import io.reactivex.Single

interface LaunchesRepository: BaseRepository {
    fun getLaunches(id: String): Single<ResultState<List<LaunchEntity.Launches>>>
}