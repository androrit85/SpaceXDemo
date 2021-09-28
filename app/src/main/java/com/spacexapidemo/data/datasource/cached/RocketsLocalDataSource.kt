package com.spacexapidemo.data.datasource.cached

import com.spacexapidemo.data.datasource.BaseDataSource
import com.spacexapidemo.data.entity.RocketsEntity
import com.spacexapidemo.domain.entity.RocketEntity
import io.reactivex.Single

interface RocketsLocalDataSource: BaseDataSource {
//Todo: data source for caching

//    fun getRockets(): Single<List<RocketEntity>>
//
//    fun saveToDatabase(list: List<RocketEntity>)
//
//    fun deleteAllRockets()
}