package com.spacexapidemo.data.mapper

import com.spacexapidemo.data.dto.RocketDto
import com.spacexapidemo.domain.entity.RocketEntity

/**
 * Extension to map Rockets DTO to Rockets entity
 */
fun RocketDto.Rocket.map() = RocketEntity(
        id = id,
        name = name,
        country = country,
        engineCount = engines.number,
        flickrImages = flickr_images,
        description = description,
        active = active
    )