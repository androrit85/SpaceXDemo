package com.spacexapidemo.presentation.ui

import android.os.Bundle
import com.spacexapidemo.R
import com.spacexapidemo.presentation.ui.base.BaseActivity
import dagger.android.AndroidInjection

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidInjection.inject(this)
    }

    override fun getNavigationControllerId(): Int = R.id.fragmentContainerView
}