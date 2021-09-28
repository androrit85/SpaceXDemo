package com.spacexapidemo.domain.usecase.launches

import com.spacexapidemo.domain.common.ResultState
import com.spacexapidemo.domain.entity.LaunchEntity
import com.spacexapidemo.domain.repository.LaunchesRepository
import com.spacexapidemo.util.RxSchedulerRule
import io.reactivex.Single
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LaunchesUseCaseImplTest {

    @Mock
    lateinit var mLaunchesRepository: LaunchesRepository

    @Rule
    @JvmField var testSchedulerRule = RxSchedulerRule()

    private lateinit var mLaunchesUseCase: LaunchesUseCaseImpl

    @Test
    fun getLaunches_Test() {
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
        Mockito.`when`(mLaunchesRepository.getLaunches(id)).thenReturn(Single.just(ResultState.Success(list)))
        mLaunchesUseCase = LaunchesUseCaseImpl(mLaunchesRepository)
        mLaunchesUseCase.getLaunches(id)
            .test()
            .assertComplete()
            .assertNoErrors()

    }


}