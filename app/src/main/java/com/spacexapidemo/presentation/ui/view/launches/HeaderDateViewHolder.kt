package com.spacexapidemo.presentation.ui.view.launches

import androidx.recyclerview.widget.RecyclerView
import com.spacexapidemo.databinding.LaunchDateItemBinding

class HeaderDateViewHolder(private val binding: LaunchDateItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(header: LaunchAdapter.YearHeader) {
        binding.dateHeader.text = header.year
    }
}