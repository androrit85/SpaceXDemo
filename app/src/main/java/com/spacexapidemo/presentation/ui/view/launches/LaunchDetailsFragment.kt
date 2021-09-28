package com.spacexapidemo.presentation.ui.view.launches

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.brandongogetap.stickyheaders.StickyLayoutManager
import com.github.mikephil.charting.data.Entry
import com.spacexapidemo.databinding.RocketLaunchDetailsFragmentBinding
import com.spacexapidemo.domain.common.ResultState
import com.spacexapidemo.presentation.ui.base.BaseFragment
import com.spacexapidemo.presentation.ui.viewmodel.LaunchDetailsViewModel
import javax.inject.Inject
import com.spacexapidemo.R
import com.spacexapidemo.domain.entity.LaunchEntity

class LaunchDetailsFragment: BaseFragment<RocketLaunchDetailsFragmentBinding,
        LaunchDetailsViewModel>(), SwipeRefreshLayout.OnRefreshListener {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var rocketId: String? = null

    private var launchAdapter: LaunchAdapter? = null

    private val args: LaunchDetailsFragmentArgs by navArgs()

    private val viewModel: LaunchDetailsViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(LaunchDetailsViewModel::class.java)
    }

    override fun getLayoutId(): Int = R.layout.rocket_launch_details_fragment

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rocketId = args.rocketItem.id
        initView()
        viewModel.result.observe(viewLifecycleOwner, { showLaunchDetails(it) })
        rocketId?.let { viewModel.fetchLaunchDetails(it) }
    }

    private fun showLaunchDetails(result: ResultState<List<LaunchEntity.Launches>>) {
        when(result) {
            is ResultState.Success -> {
                getViewBinding().launchesSwipeRefresh.isRefreshing = false
                getViewBinding().launchLoadingProgressbar.visibility = View.GONE
                updateChart(viewModel.getLaunchesPerYear(result.data))
                launchAdapter?.addItemsToLaunchList(result.data)
            }
            is ResultState.Loading -> {
                getViewBinding().launchesSwipeRefresh.isRefreshing = false
            }
            is ResultState.Error -> {
                getViewBinding().launchLoadingProgressbar.visibility = View.GONE
                //show error message
            }
        }
    }

    private fun initView() {
        getViewBinding().launchesSwipeRefresh.isRefreshing = false
        getViewBinding().launchLoadingProgressbar.visibility = View.VISIBLE
        getViewBinding().launchesSwipeRefresh.setOnRefreshListener(this)
        launchAdapter = LaunchAdapter(mutableListOf(), args.rocketItem.description)
        val itemDecorator = DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        itemDecorator.setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.decoration_vertical_space)!!)
        getViewBinding().launchRecycleView.apply {
            addItemDecoration(itemDecorator)
            layoutManager = StickyLayoutManager(requireContext(), RecyclerView.VERTICAL, false, launchAdapter)
            adapter = launchAdapter
        }
    }

    /**
     * Called when a swipe gesture triggers a refresh.
     */
    override fun onRefresh() {
        rocketId?.let { viewModel.fetchLaunchDetails(it) }
    }

    private fun updateChart(launchesPerYear: List<Entry>) = launchAdapter?.updateChart(launchesPerYear)

    override fun onDestroyView() {
        super.onDestroyView()
        launchAdapter = null
    }
}