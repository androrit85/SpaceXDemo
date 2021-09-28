package com.spacexapidemo.data.dto

import com.google.gson.annotations.SerializedName

sealed class RocketDto {
    data class Rocket(
        @SerializedName("engines") val engines : Engines,
        @SerializedName("flickr_images") val flickr_images : List<String>,
        @SerializedName("name") val name : String,
        @SerializedName("active") val active : Boolean,
        @SerializedName("country") val country : String,
        @SerializedName("description") val description : String,
        @SerializedName("id") val id : String
    ): RocketDto()

    data class Engines(
        @SerializedName("number") val number : Int
    ): RocketDto()
}
