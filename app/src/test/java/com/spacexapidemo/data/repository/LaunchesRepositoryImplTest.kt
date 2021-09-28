package com.spacexapidemo.data.repository

import com.spacexapidemo.data.datasource.remote.launches.LaunchesRemoteDataSource
import com.spacexapidemo.domain.common.ResultState
import com.spacexapidemo.domain.entity.LaunchEntity
import com.spacexapidemo.util.RxSchedulerRule
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import java.lang.Exception

@RunWith(MockitoJUnitRunner::class)
class LaunchesRepositoryImplTest {
    @Mock
    lateinit var remoteDataSource: LaunchesRemoteDataSource
    @Rule
    @JvmField var testSchedulerRule = RxSchedulerRule()

    private lateinit var launchesRespository: LaunchesRepositoryImpl

    @Before
    fun setup() {
        launchesRespository = LaunchesRepositoryImpl(remoteDataSource)
    }

    @Test
    fun getLaunches_api_onSuccess() {
        val missionName = "Test Mission1"
        val date = 1234567
        val id = "9999"
        val image = "image"
        val launch1 = LaunchEntity.Launches(
            missionName,
            date,
            "",
            true,
            listOf(image),
            image,
            id
        )
        val list = listOf(launch1)

        Mockito.`when`(remoteDataSource.getLaunches(id)).thenReturn(Single.just(list))
        launchesRespository.getLaunches(id).test()
            .assertComplete()
            .assertNoErrors()
            .assertValue{list[0].missionName == missionName}
            .assertValue{list[0].dateUnix == date}
            .assertValue{ list[0].launchStatus }
            .assertValue{list[0].patchImages == image}
            .assertValue{list[0].rocketId == id}
    }

    @Test
    fun getLaunches_api_onFailure() {
        //val id = "1"
        val response = Throwable("Error response")
        Mockito.`when`(remoteDataSource.getLaunches("id")).thenReturn(Single.error(response))
        launchesRespository.getLaunches("id").test()
            .assertComplete()
            .assertValue { true }
    }
}