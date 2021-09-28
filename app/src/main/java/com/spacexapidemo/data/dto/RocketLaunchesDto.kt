package com.spacexapidemo.data.dto

import com.google.gson.annotations.SerializedName

sealed class RocketLaunchesDto {
    data class LaunchItem(
        @SerializedName("links") val links : Links,
        @SerializedName("date_utc") val date_utc : String?,
        @SerializedName("date_unix") val date_unix : Int,
        @SerializedName("success") val success : Boolean,
        @SerializedName("name") val name : String,
        @SerializedName("id") val id : String,
        @SerializedName("rocket") val rocket_id : String
    ): RocketLaunchesDto()

    data class Links (
        @SerializedName("patch") val patch : Patch?,
        @SerializedName("flickr") val flickr : Flickr
    ): RocketLaunchesDto()

    data class Flickr (
        @SerializedName("small") val small : List<String>,
        @SerializedName("original") val original : List<String>
    ): RocketLaunchesDto()

    data class Patch (
        @SerializedName("small") val small : String?,
        @SerializedName("large") val large : String
    )
}
