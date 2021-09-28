package com.spacexapidemo.data.datasource.cached

import com.spacexapidemo.data.db.RocketDao
import com.spacexapidemo.data.entity.RocketsEntity
import com.spacexapidemo.domain.entity.RocketEntity
import io.reactivex.Single

class RocketsLocalDataSourceImpl(val rocketDao: RocketDao): RocketsLocalDataSource {
    //Todo: data source for caching

//    override fun getRockets(): Single<List<RocketsEntity>> {
//        return rocketDao.getAllRockets()
//    }
//
//    override fun saveToDatabase(list: List<RocketEntity>) {
//       rocketDao.replaceAllRockets()
//    }
//
//    override fun deleteAllRockets() {
//    }
}