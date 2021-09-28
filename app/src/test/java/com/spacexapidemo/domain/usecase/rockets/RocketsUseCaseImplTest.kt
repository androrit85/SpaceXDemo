package com.spacexapidemo.domain.usecase.rockets

import com.spacexapidemo.domain.common.ResultState
import com.spacexapidemo.domain.entity.RocketEntity
import com.spacexapidemo.domain.repository.RocketsRepository
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

@RunWith(MockitoJUnitRunner::class)
class RocketsUseCaseImplTest {
    @Mock
    lateinit var mRocketsRepository: RocketsRepository
    @Rule
    @JvmField var testSchedulerRule = RxSchedulerRule()

    private lateinit var mRocketsUseCase: RocketsUseCaseImpl

    @Before
    fun setup() {
        mRocketsUseCase = RocketsUseCaseImpl(mRocketsRepository)
    }

    @Test
    fun getAllRockets_test() {
        val rockets = RocketEntity(
            "id",
            "name",
            "India",
            true,
            1001,
            listOf("String1", "String2"),
            "Desc"
        )
        val list = listOf(rockets)
        Mockito.`when`(mRocketsRepository.getRockets()).thenReturn(Single.just(ResultState.Success(list)))

        mRocketsUseCase = RocketsUseCaseImpl(mRocketsRepository)
        mRocketsUseCase.getAllRockets()
            .test()
            .assertComplete()
            .assertNoErrors()
    }

    @Test
    fun isWelcomeDialogShownFirstTime_true_test() {
        Mockito.`when`(mRocketsRepository.isWelcomeDialogShownFirstTime()).thenReturn(true)

        Assert.assertTrue(mRocketsUseCase.isWelcomeDialogShownFirstTime())
    }

    @Test
    fun isWelcomeDialogShownFirstTime_false_test() {
        Mockito.`when`(mRocketsRepository.isWelcomeDialogShownFirstTime()).thenReturn(false)

        Assert.assertFalse(mRocketsUseCase.isWelcomeDialogShownFirstTime())
    }
}