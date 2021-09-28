package com.spacexapidemo.presentation.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.spacexapidemo.domain.common.ResultState
import com.spacexapidemo.domain.entity.LaunchEntity
import com.spacexapidemo.domain.repository.LaunchesRepository
import com.spacexapidemo.domain.usecase.launches.LaunchesUseCase
import com.spacexapidemo.domain.usecase.launches.LaunchesUseCaseImpl
import com.spacexapidemo.domain.usecase.rockets.RocketsUseCaseImpl
import com.spacexapidemo.presentation.common.extension.toYear
import io.reactivex.Single
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LaunchDetailsViewModelTest {
    @Mock
    lateinit var mLaunchUseCase: LaunchesUseCase

    @Mock
    lateinit var mLaunchRepository: LaunchesRepository

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var mViewModel: LaunchDetailsViewModel
    private val image = "image"
    private val launch1 = LaunchEntity.Launches(
        "Test Mission1",
        1234567,
        "",
        true,
        listOf(image),
        image,
        "9999"
    )

    @Before
    fun setup() {
        val list = listOf(launch1)
        mLaunchUseCase = LaunchesUseCaseImpl(mLaunchRepository)
        Mockito.`when`(mLaunchUseCase.getLaunches("9999")).thenReturn(Single.just(ResultState.Success(list)))
        mViewModel = LaunchDetailsViewModel(mLaunchUseCase)
    }

    @Test
    fun fetch_launch_details_when_api_Success() {
        mViewModel.fetchLaunchDetails("9999")

        assertNotNull(mViewModel.result.value)
    }

    @Test
    fun get_launches_per_year_test() {
        val launchYears = listOf(2010, 2012, 2013)
        val launches = List(launchYears.size) { index ->
           launch1.apply { this.dateUnix = launchYears[index] }
        }
        val list: List<Entry> = mViewModel.getLaunchesPerYear(launches)
        assertEquals(list.size, 1)
        assertEquals(list[0].y, 3f) // 2010
    }
}