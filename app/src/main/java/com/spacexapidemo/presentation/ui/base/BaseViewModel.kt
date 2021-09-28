package com.spacexapidemo.presentation.ui.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class BaseViewModel: ViewModel() {

    private val mDisposables = CompositeDisposable()

    protected fun addDisposable(disposable: Disposable) {
        mDisposables.add(disposable)
    }

    private fun clearDisposables() {
        mDisposables.clear()
    }

    override fun onCleared() {
        clearDisposables()
        super.onCleared()
    }
}