package com.spacexapidemo.data.common.prefs

import android.content.Context

class SharedPreferenceHelperImpl(private val context: Context) : SharedPreferencesHelper {

    override fun storeIsFirstTime(isFirstTime: Boolean) =
        context.getSharedPreferences(Constants.SharedPrefs.DEFAULT, Context.MODE_PRIVATE)
            .edit()
            .putBoolean(Constants.SharedPrefs.KEY_FIRST_TIME, isFirstTime)
            .apply()

    override fun getIsFirstTime() =
        context.getSharedPreferences(Constants.SharedPrefs.DEFAULT, Context.MODE_PRIVATE)
            .getBoolean(Constants.SharedPrefs.KEY_FIRST_TIME, true)
}