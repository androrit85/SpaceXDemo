package com.spacexapidemo.domain.cache

import com.spacexapidemo.domain.entity.RocketEntity

interface RocketsCache {
    fun saveRockets(list: List<RocketEntity>)

}