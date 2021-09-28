package com.spacexapidemo.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.spacexapidemo.data.entity.RocketsEntity
import com.spacexapidemo.domain.entity.RocketEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface RocketDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllRocket(rocket: List<RocketsEntity>)

    @Transaction
    fun replaceAllRockets(rocket: List<RocketsEntity>) {
        deleteAllRockets()
        insertAllRocket(rocket)
    }

    @Query("DELETE FROM rocket_table")
    fun deleteAllRockets()

    @Query("SELECT * FROM rocket_table")
    fun getAllRockets(): Single<List<RocketsEntity>>
}