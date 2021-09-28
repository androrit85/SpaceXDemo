package com.spacexapidemo.domain.entity

import android.os.Parcelable
import com.spacexapidemo.data.dto.RocketDto
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class RocketEntity(
    val id: String,
    val name: String,
    val country: String,
    val active: Boolean,
    val engineCount: Int,
    val flickrImages: List<String>,
    val description: String
) : Parcelable

