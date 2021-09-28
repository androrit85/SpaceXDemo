package com.spacexapidemo.di.modules

import android.app.Application
import android.content.Context
import com.spacexapidemo.data.common.prefs.SharedPreferenceHelperImpl
import com.spacexapidemo.data.common.prefs.SharedPreferencesHelper
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [NetworkModule::class, DbModule::class])
class AppModule {

    @Singleton
    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun provideSharedPreferenceHelper(context: Context): SharedPreferencesHelper {
        return SharedPreferenceHelperImpl(context)
    }
}