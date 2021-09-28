package com.spacexapidemo.data.mapper

import com.spacexapidemo.data.dto.RocketLaunchesDto
import com.spacexapidemo.domain.entity.LaunchEntity

fun RocketLaunchesDto.LaunchItem.map() = LaunchEntity.Launches (
    missionName = name,
    launchDate = date_utc,
    launchStatus = success,
    launchImages = links.flickr.original,
    patchImages = links.patch?.small,
    dateUnix = date_unix,
    rocketId = rocket_id
)