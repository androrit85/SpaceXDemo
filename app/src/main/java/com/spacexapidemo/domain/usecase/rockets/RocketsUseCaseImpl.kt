package com.spacexapidemo.domain.usecase.rockets

import com.spacexapidemo.domain.common.ResultState
import com.spacexapidemo.domain.entity.RocketEntity
import com.spacexapidemo.domain.repository.RocketsRepository
import io.reactivex.Single

class RocketsUseCaseImpl(private val repository: RocketsRepository):RocketsUseCase {
    override fun getAllRockets(): Single<ResultState<List<RocketEntity>>> = repository.getRockets()
    override fun isWelcomeDialogShownFirstTime(): Boolean = repository.isWelcomeDialogShownFirstTime()
}