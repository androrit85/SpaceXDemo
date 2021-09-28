package com.spacexapidemo.presentation.ui.viewmodel

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.Glide
import com.spacexapidemo.R
import com.spacexapidemo.domain.common.ResultState
import com.spacexapidemo.domain.entity.RocketEntity
import com.spacexapidemo.domain.usecase.rockets.RocketsUseCase
import com.spacexapidemo.presentation.ui.base.BaseViewModel
import javax.inject.Inject

class RocketListViewModel @Inject constructor(
    private val mRocketsUseCase: RocketsUseCase
) : BaseViewModel() {

    private val resultStateLiveData: MutableLiveData<ResultState<List<RocketEntity>>> = MutableLiveData()
    val resultState: LiveData<ResultState<List<RocketEntity>>> = resultStateLiveData
    private val welcomeDialogLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val welcomeDialog: LiveData<Boolean> = welcomeDialogLiveData
    private var filteredListLiveData: MutableLiveData<List<RocketEntity>> = MutableLiveData()
    var filteredList: LiveData<List<RocketEntity>> = filteredListLiveData

    init {
        fetchAllRockets()
    }

    fun fetchAllRockets() {
        addDisposable(
            mRocketsUseCase.getAllRockets()
                .subscribe({ t ->
                    resultStateLiveData.value = t
                }, {
                    Log.e(
                        RocketListViewModel::class::simpleName.toString(),
                        "Error fetching rockets"
                    )
                })
        )
    }

    companion object {
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadFlickerImage(image: ImageView, url: String?) {
            Glide.with(image.context)
                .load(url)
                .error(R.drawable.ic_report_problem)
                .fallback(R.drawable.ic_report_problem)
                .centerCrop()
                .into(image)
        }
    }

    fun checkIfFirstTimeUser() {
        if (mRocketsUseCase.isWelcomeDialogShownFirstTime()) {
             welcomeDialogLiveData.value = true
        }
    }

    fun filterActiveResults(checked: Boolean, rocketList: List<RocketEntity>) {
        if (checked) {
            filteredListLiveData.value = rocketList.filter { rocketEntity ->
                rocketEntity.active
            }
        } else {
            filteredListLiveData.value = rocketList
        }
    }
}