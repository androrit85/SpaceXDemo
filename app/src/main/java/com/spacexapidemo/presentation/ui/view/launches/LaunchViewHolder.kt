package com.spacexapidemo.presentation.ui.view.launches

import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.spacexapidemo.R
import com.spacexapidemo.databinding.RocketLaunchDetailsListItemBinding
import com.spacexapidemo.domain.entity.LaunchEntity
import com.spacexapidemo.presentation.common.extension.toReadableDate

class LaunchViewHolder(private val binding: RocketLaunchDetailsListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(param: LaunchEntity.Launches) {
        binding.launchDate.text = param.dateUnix.toReadableDate()
        binding.launchStatus.text = param.launchStatus.toString()
        binding.missionName.text = param.missionName

        if (param.patchImages?.isNotBlank() == true)
            displayImage(binding.launchImage, param.patchImages)
    }

    private fun displayImage(target: ImageView, image: String) {
        Glide.with(target)
            .load(image)
            .placeholder(R.mipmap.ic_launcher)
            .into(target)
    }
}