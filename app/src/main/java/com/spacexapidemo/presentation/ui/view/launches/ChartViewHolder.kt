package com.spacexapidemo.presentation.ui.view.launches

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.spacexapidemo.R
import com.spacexapidemo.databinding.LaunchesChartItemBinding

class ChartViewHolder(private val binding: LaunchesChartItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(chartEntries: MutableList<Entry>) {
        val lineChart = binding.launchesChart
        val lineDataSet = LineDataSet(chartEntries, binding.root.context.getString(R.string.number_of_launches))
        lineDataSet.axisDependency = YAxis.AxisDependency.LEFT
        lineDataSet.isHighlightEnabled = true
        lineDataSet.lineWidth = 2f
        lineDataSet.color = Color.RED
        lineDataSet.setCircleColor(Color.CYAN)
        lineDataSet.circleRadius = 6f
        lineDataSet.circleHoleRadius = 3f
        lineDataSet.setDrawHighlightIndicators(true)
        lineDataSet.highLightColor = Color.RED
        lineDataSet.valueTextSize = 12f
        lineDataSet.valueTextColor = Color.WHITE

        val lineData = LineData(lineDataSet)
        lineChart.setTouchEnabled(false)
        lineChart.setDrawMarkers(true)
        lineChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        lineChart.animateY(1000)
        lineChart.xAxis.isGranularityEnabled = true
        lineChart.xAxis.granularity = 1.0f
        lineChart.data = lineData
    }
}