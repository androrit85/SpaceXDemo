package com.spacexapidemo.domain.usecase.launches

import com.spacexapidemo.domain.common.ResultState
import com.spacexapidemo.domain.entity.LaunchEntity
import com.spacexapidemo.domain.usecase.BaseUseCase
import io.reactivex.Single

interface LaunchesUseCase: BaseUseCase {
    fun getLaunches(id: String): Single<ResultState<List<LaunchEntity.Launches>>>
}