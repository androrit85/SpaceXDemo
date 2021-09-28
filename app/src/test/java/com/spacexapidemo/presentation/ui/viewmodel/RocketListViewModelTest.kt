package com.spacexapidemo.presentation.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.spacexapidemo.domain.common.ResultState
import com.spacexapidemo.domain.entity.RocketEntity
import com.spacexapidemo.domain.repository.RocketsRepository
import com.spacexapidemo.domain.usecase.rockets.RocketsUseCase
import com.spacexapidemo.domain.usecase.rockets.RocketsUseCaseImpl
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RocketListViewModelTest {
    @Mock
    lateinit var rocketsUseCase: RocketsUseCase

    @Mock
    lateinit var rocketsRepository: RocketsRepository

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: RocketListViewModel

    @Before
    fun setup() {
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
        rocketsUseCase = RocketsUseCaseImpl(rocketsRepository)
        Mockito.`when`(rocketsUseCase.getAllRockets()).thenReturn(Single.just(ResultState.Success(list)))
        viewModel = RocketListViewModel(rocketsUseCase)
    }

    @Test
    fun fetch_all_rockets_when_success() {
        viewModel.fetchAllRockets()
        val liveData = viewModel.resultState
        Assert.assertNotNull(liveData.value)
    }

    @Test
    fun checkIfFirstTimeUser_update_livedata_to_show_welcomeDialog() {
        Mockito.`when`(rocketsRepository.isWelcomeDialogShownFirstTime()).thenReturn(true)
        //Assert.assertTrue(viewModel.isFirstTimeOpened())

        viewModel.checkIfFirstTimeUser()
        val liveData = viewModel.welcomeDialog
        Assert.assertNotNull(liveData.value)
        Assert.assertTrue(liveData.value == true)
    }

    @Test
    fun checkIfNotFirstTimeUser_to_show_welcomeDialog_livedata_null() {
        Mockito.`when`(rocketsRepository.isWelcomeDialogShownFirstTime()).thenReturn(false)

        viewModel.checkIfFirstTimeUser()
        val liveData = viewModel.welcomeDialog
        Assert.assertFalse(liveData.value == false)
    }

    @Test
    fun filter_active_results_when_active() {
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

        viewModel.filterActiveResults(true, list)

        Assert.assertTrue(viewModel.filteredList.value!![0].active == list[0].active)
    }

    @Test
    fun filter_active_results_when_no_filter() {
        viewModel.filterActiveResults(false, listOf())

        Assert.assertNotNull(viewModel.filteredList.value)
    }
}