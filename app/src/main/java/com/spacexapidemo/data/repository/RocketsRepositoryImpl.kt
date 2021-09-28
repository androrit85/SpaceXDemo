package com.spacexapidemo.data.repository

import com.spacexapidemo.data.common.applyIoScheduler
import com.spacexapidemo.data.common.prefs.SharedPreferencesHelper
import com.spacexapidemo.data.datasource.remote.rockets.RocketsRemoteDataSource
import com.spacexapidemo.domain.common.ResultState
import com.spacexapidemo.domain.entity.RocketEntity
import com.spacexapidemo.domain.repository.RocketsRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Repository class for searching all rockets from SpaceXApi
 */
class RocketsRepositoryImpl @Inject constructor(
    private val remoteDataSource: RocketsRemoteDataSource,
    private val prefsHelper: SharedPreferencesHelper
) : RocketsRepository {

    override fun getRockets(): Single<ResultState<List<RocketEntity>>> {
        return remoteDataSource.getRockets()
            .applyIoScheduler()
            .map {
                ResultState.Success(it) as ResultState<List<RocketEntity>>
            }
            .onErrorReturn { e ->
                ResultState.Error(e, null)
            }
    }

    override fun isWelcomeDialogShownFirstTime(): Boolean =
        prefsHelper.getIsFirstTime().also {
            if (it) prefsHelper.storeIsFirstTime(false)
        }

    //Todo: save data to room database
    /*private fun saveToDatabase(list: List<RocketEntity>) {
        disposables.add(Completable.fromAction {
            localDataSource.saveToDatabase(list)
        }.applyIoScheduler().subscribe())
    }*/
}