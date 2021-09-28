package com.spacexapidemo.presentation.ui.view.rocket

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.spacexapidemo.R
import com.spacexapidemo.databinding.RocketlistFragmentBinding
import com.spacexapidemo.domain.common.ResultState
import com.spacexapidemo.domain.entity.RocketEntity
import com.spacexapidemo.presentation.ui.base.BaseFragment
import com.spacexapidemo.presentation.ui.viewmodel.RocketListViewModel
import javax.inject.Inject

class RocketListFragment: BaseFragment<RocketlistFragmentBinding, RocketListViewModel>(),
    SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var rocketListAdapter: RocketListAdapter? = null
    private var rocketList = listOf<RocketEntity>()

    private val viewModel: RocketListViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(RocketListViewModel::class.java)
    }

    override fun getLayoutId() = R.layout.rocketlist_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.checkIfFirstTimeUser()
        initView()
        viewModel.let {
            it.resultState.observe(viewLifecycleOwner, { it -> showRockets(it) })
            it.welcomeDialog.observe(viewLifecycleOwner, {showDialogIfFirstTimeUser()})
            it.filteredList.observe(viewLifecycleOwner, { list -> updateFilteredList(list)})
        }
        getViewBinding().rocketsActiveFilterSwitch.setOnCheckedChangeListener{
                _, isChecked -> viewModel.filterActiveResults(isChecked, rocketList)
        }
    }

    private fun showRockets(result: ResultState<List<RocketEntity>>) {
        when(result){
            is ResultState.Success -> {
                getViewBinding().rocketListRefresh.isRefreshing = false
                rocketList = result.data
                rocketListAdapter?.addItemsToRocketList(result.data)
            }
            is ResultState.Error -> {
                //show error message
            }
            is ResultState.Loading -> {
                getViewBinding().rocketListRefresh.isRefreshing = false
            }
        }
    }

    private fun initView() {
        getViewBinding().rocketListRefresh.isRefreshing = true
        getViewBinding().rocketListRefresh.setOnRefreshListener(this)
        rocketListAdapter = RocketListAdapter()
        getViewBinding().rocketList.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = rocketListAdapter
        }
    }

    /**
     * Called when a swipe gesture triggers a refresh.
     */
    override fun onRefresh() {
        getViewBinding().rocketsActiveFilterSwitch.isChecked = false
        viewModel.fetchAllRockets()
    }

    private fun showDialogIfFirstTimeUser() {
            val builder = context?.let { AlertDialog.Builder(it) }
        builder?.apply {
            setTitle(getString(R.string.app_name))
            setMessage(getString(R.string.welcome_message))
            setPositiveButton(getString(R.string.done), DialogInterface.OnClickListener(function = positiveButtonClick))
            show()
        }
    }

    private val positiveButtonClick = { dialog: DialogInterface, _: Int ->
        dialog.dismiss()
    }

    private fun updateFilteredList(list: List<RocketEntity>) {
        rocketListAdapter?.addItemsToRocketList(list)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        rocketListAdapter = null
    }
}