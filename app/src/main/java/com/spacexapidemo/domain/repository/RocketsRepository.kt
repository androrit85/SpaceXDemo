package com.spacexapidemo.domain.repository

import com.spacexapidemo.domain.common.ResultState
import com.spacexapidemo.domain.entity.RocketEntity
import io.reactivex.Single

interface RocketsRepository: BaseRepository {
    fun getRockets():Single<ResultState<List<RocketEntity>>>
    fun isWelcomeDialogShownFirstTime():Boolean
}