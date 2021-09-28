package com.spacexapidemo.data.datasource.remote.rockets

import com.spacexapidemo.data.datasource.BaseDataSource
import com.spacexapidemo.domain.entity.LaunchEntity
import com.spacexapidemo.domain.entity.RocketEntity
import io.reactivex.Single

interface RocketsRemoteDataSource: BaseDataSource {
    fun getRockets(): Single<List<RocketEntity>>
}