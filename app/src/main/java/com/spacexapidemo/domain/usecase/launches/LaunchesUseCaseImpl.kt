package com.spacexapidemo.domain.usecase.launches

import com.spacexapidemo.domain.common.ResultState
import com.spacexapidemo.domain.entity.LaunchEntity
import com.spacexapidemo.domain.repository.LaunchesRepository
import io.reactivex.Single

class LaunchesUseCaseImpl(private val launchesRepository: LaunchesRepository) : LaunchesUseCase {
    override fun getLaunches(id: String): Single<ResultState<List<LaunchEntity.Launches>>> =
        launchesRepository.getLaunches(id)
}