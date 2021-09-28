package com.spacexapidemo.data.common.prefs

interface SharedPreferencesHelper {

    fun storeIsFirstTime(isFirstTime: Boolean)
    fun getIsFirstTime(): Boolean
}