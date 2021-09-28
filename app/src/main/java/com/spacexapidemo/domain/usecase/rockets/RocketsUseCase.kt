package com.spacexapidemo.domain.usecase.rockets

import com.spacexapidemo.domain.common.ResultState
import com.spacexapidemo.domain.entity.RocketEntity
import com.spacexapidemo.domain.usecase.BaseUseCase
import io.reactivex.Single

interface RocketsUseCase: BaseUseCase {
    fun getAllRockets(): Single<ResultState<List<RocketEntity>>>
    fun isWelcomeDialogShownFirstTime(): Boolean
}