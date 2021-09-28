package com.spacexapidemo.presentation.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.github.mikephil.charting.data.Entry
import com.spacexapidemo.domain.common.ResultState
import com.spacexapidemo.domain.entity.LaunchEntity
import com.spacexapidemo.domain.usecase.launches.LaunchesUseCase
import com.spacexapidemo.presentation.common.extension.toYear
import com.spacexapidemo.presentation.ui.base.BaseViewModel
import io.reactivex.functions.Consumer
import javax.inject.Inject

class LaunchDetailsViewModel @Inject constructor(private val launchesUseCase: LaunchesUseCase) :
    BaseViewModel() {

    val result: MutableLiveData<ResultState<List<LaunchEntity.Launches>>> = MutableLiveData()

    fun fetchLaunchDetails(rocketId: String) {
        addDisposable(launchesUseCase.getLaunches(rocketId)
            .subscribe({
                it -> result.value = it
            }, Consumer {
                Log.e(RocketListViewModel::class::simpleName.toString(), "Error fetching launch details")
            })
        )
    }

    fun getLaunchesPerYear(launches: List<LaunchEntity.Launches>): List<Entry> =
        mutableListOf<Entry>().apply {
            val map = launches.groupingBy { it.dateUnix.toYear() }.eachCount()
            for (entry in map) {
                add(Entry(entry.key.toFloat(), entry.value.toFloat()))
            }
        }
}