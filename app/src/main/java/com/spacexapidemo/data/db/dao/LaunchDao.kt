package com.spacexapidemo.data.db.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.spacexapidemo.data.entity.LaunchesEntity
import io.reactivex.Single

@Dao
interface LaunchDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllLaunches(launches: List<LaunchesEntity>)

    @Transaction
    fun replaceAllLaunches(launches: List<LaunchesEntity>) {
        deleteAllLaunches()
        insertAllLaunches(launches)
    }

    @Query("DELETE FROM launches_table")
    fun deleteAllLaunches()

    @Query("SELECT * FROM launches_table")
    fun getAllLaunches(rocketId: String): Single<List<LaunchesEntity>>
}