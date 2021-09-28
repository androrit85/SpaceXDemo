package com.spacexapidemo.data.repository

import com.spacexapidemo.data.common.prefs.SharedPreferencesHelper
import com.spacexapidemo.data.datasource.remote.rockets.RocketsRemoteDataSource
import com.spacexapidemo.data.dto.RocketDto
import com.spacexapidemo.domain.entity.RocketEntity
import com.spacexapidemo.util.RxSchedulerRule
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RocketsRepositoryImplTest {
    @Mock
    lateinit var remoteDataSource: RocketsRemoteDataSource

    @Mock
    lateinit var prefsHelper: SharedPreferencesHelper

    @Rule
    @JvmField var testSchedulerRule = RxSchedulerRule()

    private lateinit var rocketsRepository: RocketsRepositoryImpl

    @Before
    fun setup() {
        rocketsRepository = RocketsRepositoryImpl(remoteDataSource, prefsHelper)
    }

    @Test
    fun getRockets_api_onSuccess() {
        val active = true
        val country = "India"
        val description = "Desc"
        val rocketId = "id"
        val rocketName = "name"
        val number = 1001
        val image = listOf("String1", "String2")

        val rockets = RocketEntity(
            rocketId,
            rocketName,
            country,
            active,
            number,
            image,
            description
        )
        val list = listOf(rockets)
        Mockito.`when`(remoteDataSource.getRockets()).thenReturn(Single.just(list))
        rocketsRepository.getRockets().test()
            .assertComplete()
            .assertNoErrors()
            .assertValue{list[0].id == rocketId}
            .assertValue{list[0].name == rocketName}
            .assertValue{list[0].active}
            .assertValue{list[0].country == country}
            .assertValue{list[0].description == description}
            .assertValue{list[0].engineCount == number}
            .assertValue{list[0].flickrImages == image}
    }

    @Test
    fun is_welcome_dialog_shown_first_time_true() {
        Mockito.`when`(prefsHelper.getIsFirstTime()).thenReturn(true)
        Assert.assertTrue(rocketsRepository.isWelcomeDialogShownFirstTime())
        Mockito.verify(prefsHelper, Mockito.times(1)).storeIsFirstTime(ArgumentMatchers.anyBoolean())
    }

    @Test
    fun is_First_time_when_app_opened_false() {
        Mockito.`when`(prefsHelper.getIsFirstTime()).thenReturn(false)
        Assert.assertFalse(rocketsRepository.isWelcomeDialogShownFirstTime())
        Mockito.verify(prefsHelper, Mockito.times(0)).storeIsFirstTime(ArgumentMatchers.anyBoolean())
    }
}