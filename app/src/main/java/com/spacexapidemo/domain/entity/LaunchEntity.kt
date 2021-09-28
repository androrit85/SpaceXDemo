package com.spacexapidemo.domain.entity

sealed class LaunchEntity {
    data class Launches(
        val missionName: String,
        var dateUnix: Int,
        val launchDate: String?,
        val launchStatus: Boolean,
        val launchImages: List<String>,
        val patchImages: String?,
        val rocketId: String
    ): LaunchEntity()
}
