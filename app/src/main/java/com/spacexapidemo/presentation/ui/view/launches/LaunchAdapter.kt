package com.spacexapidemo.presentation.ui.view.launches

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.brandongogetap.stickyheaders.exposed.StickyHeader
import com.brandongogetap.stickyheaders.exposed.StickyHeaderHandler
import com.github.mikephil.charting.data.Entry
import com.spacexapidemo.R
import com.spacexapidemo.databinding.LaunchDateItemBinding
import com.spacexapidemo.databinding.LaunchDescriptionItemBinding
import com.spacexapidemo.databinding.LaunchesChartItemBinding
import com.spacexapidemo.databinding.RocketLaunchDetailsListItemBinding
import com.spacexapidemo.domain.entity.LaunchEntity
import com.spacexapidemo.presentation.common.extension.toYear

class LaunchAdapter(
    private val items: MutableList<Any>,
    private val description: String
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), StickyHeaderHandler {

    private var launchList: MutableList<Any> = mutableListOf()
    private val chartEntries = mutableListOf<Entry>()

    companion object {
        const val CHART = 0
        const val DESCRIPTION = 1
        const val HEADER_DATE = 2
        const val LAUNCH = 3
    }

    private fun inflateLayout(parent: ViewGroup, @LayoutRes layout: Int) =
        DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            layout, parent, false
        )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            CHART -> ChartViewHolder(
                inflateLayout(
                    parent,
                    R.layout.launches_chart_item
                ) as LaunchesChartItemBinding
            )
            DESCRIPTION -> DescriptionViewHolder(
                inflateLayout(
                    parent,
                    R.layout.launch_description_item
                ) as LaunchDescriptionItemBinding
            )

            HEADER_DATE -> HeaderDateViewHolder(
                inflateLayout(
                    parent,
                    R.layout.launch_date_item
                ) as LaunchDateItemBinding
            )
            LAUNCH -> LaunchViewHolder(
                inflateLayout(
                    parent,
                    R.layout.rocket_launch_details_list_item
                ) as RocketLaunchDetailsListItemBinding
            )
            else -> throw RuntimeException("View type not supported")
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ChartViewHolder -> holder.bind(chartEntries)
            is DescriptionViewHolder -> holder.bind(description)
            is HeaderDateViewHolder -> holder.bind(items[position] as YearHeader)
            is LaunchViewHolder -> holder.bind(items[position] as LaunchEntity.Launches)
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int =
        when (items[position]) {
            is ChartItem -> CHART
            is DescriptionItem -> DESCRIPTION
            is YearHeader -> HEADER_DATE
            is LaunchEntity.Launches -> LAUNCH
            else -> throw RuntimeException("Item type not supported")
        }

    class ChartItem
    class DescriptionItem
    data class YearHeader(val year: String) : StickyHeader

    /**
     * @return The dataset supplied to the [RecyclerView.Adapter]
     */
    override fun getAdapterData(): List<*> {
        return items
    }

    private fun updateItemList() {
        items.clear()
        items.add(ChartItem())
        items.add(DescriptionItem())
        items.addAll(launchList)
        notifyDataSetChanged()
    }

    fun addItemsToLaunchList(list: List<LaunchEntity.Launches>) {
        launchList = list.toMutableList()
        updateLaunches(list)
        notifyDataSetChanged()
    }

    private fun updateLaunches(newLaunches: List<LaunchEntity.Launches>) {
        launchList.clear()
        newLaunches.sortedBy { launch -> launch.dateUnix }
        var prevYear = 0
        for (launch in newLaunches) {
            if (launch.dateUnix.toYear() > prevYear) {
                prevYear = launch.dateUnix.toYear()
                launchList.add(YearHeader(prevYear.toString()))
            }
            launchList.add(launch)
        }

        updateItemList()
    }

    fun updateChart(launchesPerYear: List<Entry>) {
        chartEntries.clear()
        chartEntries.addAll(launchesPerYear)
        notifyDataSetChanged()
    }
}