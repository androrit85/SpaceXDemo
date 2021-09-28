package com.spacexapidemo.presentation.ui.base

import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import dagger.android.support.DaggerFragment

abstract class BaseFragment<out T: ViewDataBinding, out V: BaseViewModel>: DaggerFragment() {
    private lateinit var mViewDataBinding: T
    private lateinit var mViewModel: V

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewDataBinding = DataBindingUtil.inflate(inflater,getLayoutId(),container,false)
        return mViewDataBinding.root
    }

    @LayoutRes
    abstract fun getLayoutId(): Int

    internal fun getViewBinding() = mViewDataBinding
}