package com.spacexapidemo.presentation.ui.view.rocket

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.spacexapidemo.R
import com.spacexapidemo.databinding.RocketListItemBinding
import com.spacexapidemo.domain.entity.RocketEntity

class RocketListAdapter : RecyclerView.Adapter<RocketListAdapter.RocketViewHolder>() {

    private var rocketsList: List<RocketEntity> = emptyList()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RocketViewHolder {
        val binding: RocketListItemBinding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            R.layout.rocket_list_item, parent, false
        ) as RocketListItemBinding

        return RocketViewHolder(binding)
    }

    class RocketViewHolder(private val binding: RocketListItemBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(rocket: RocketEntity) {
            binding.rocketItem = rocket
            binding.root.setOnClickListener(this)
            binding.executePendingBindings()
        }

        override fun onClick(view: View?) {
            val action =
                com.spacexapidemo.presentation.ui.view.rocket.RocketListFragmentDirections.actionRocketListFragmentToRocketLaunchDetailsFragment(
                    binding.rocketItem!!
                )
            view?.findNavController()?.navigate(action)
        }
    }

    override fun onBindViewHolder(holder: RocketViewHolder, position: Int) {
        val rocket = rocketsList[position]
        holder.bind(rocket)
    }

    override fun getItemCount(): Int {
        return rocketsList.size
    }

    fun addItemsToRocketList(list: List<RocketEntity>) {
        rocketsList = list
        notifyDataSetChanged()
    }
}