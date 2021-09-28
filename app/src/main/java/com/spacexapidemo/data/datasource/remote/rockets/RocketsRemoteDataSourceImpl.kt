package com.spacexapidemo.data.datasource.remote.rockets

import com.spacexapidemo.data.APIService
import com.spacexapidemo.data.common.applyIoScheduler
import com.spacexapidemo.data.mapper.map
import com.spacexapidemo.domain.entity.LaunchEntity
import com.spacexapidemo.domain.entity.RocketEntity
import io.reactivex.Single

class RocketsRemoteDataSourceImpl(private val api: APIService) : RocketsRemoteDataSource {

    override fun getRockets(): Single<List<RocketEntity>> = api.getRockets()
        .applyIoScheduler()
        .map { item ->
            item.map {
                it.map()
            }
        }
}