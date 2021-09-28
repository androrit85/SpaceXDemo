package com.spacexapidemo.presentation.ui.base

import androidx.navigation.NavController
import androidx.navigation.findNavController
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity: DaggerAppCompatActivity() {

    private val navigationController : NavController by lazy {
        findNavController(getNavigationControllerId())
    }

    override fun onSupportNavigateUp(): Boolean {
        return navigationController.navigateUp()
    }

    abstract fun getNavigationControllerId(): Int
}