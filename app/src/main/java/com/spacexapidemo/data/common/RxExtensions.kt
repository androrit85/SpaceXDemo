package com.spacexapidemo.data.common

import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

//Single
fun <T> Single<T>.applyIoScheduler() = applyScheduler(Schedulers.io())
private fun <T> Single<T>.applyScheduler(scheduler: Scheduler) =
    subscribeOn(scheduler).observeOn(AndroidSchedulers.mainThread())

//Completable
fun Completable.applyIoScheduler() = applyScheduler(Schedulers.io())
private fun Completable.applyScheduler(scheduler: Scheduler) =
    subscribeOn(scheduler).observeOn(AndroidSchedulers.mainThread())