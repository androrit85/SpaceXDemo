package com.spacexapidemo.presentation.ui.view.launches

import androidx.recyclerview.widget.RecyclerView
import com.spacexapidemo.databinding.LaunchDescriptionItemBinding

class DescriptionViewHolder(private val binding: LaunchDescriptionItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(description: String) {
        binding.launchesDescription.text = description
    }
}