package com.spacexapidemo.data.mapper

import com.spacexapidemo.data.dto.RocketDto
import com.spacexapidemo.domain.entity.RocketEntity
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class RocketDtoToEntityExtensionKtTest {

    companion object {
        const val ACTIVE = true
        const val COUNTRY = "India"
        const val DESCRIPTION = "Desc"
        const val ROCKET_ID = "id"
        const val ROCKET_NAME = "name"
        const val NUMBER = 1001
        val IMAGE = listOf("String1", "String2")
    }
    lateinit var rocketEntity: RocketEntity

    @Before
    fun init() {
        val enginesDto = RocketDto.Engines(NUMBER)

        val rocketDto = RocketDto.Rocket(
            enginesDto,
            IMAGE,
            ROCKET_NAME,
            ACTIVE,
            COUNTRY,
            DESCRIPTION,
            ROCKET_ID
        )
        rocketEntity = rocketDto.map()
    }

    @Test
    fun mapped_rocketId() {
        Assert.assertEquals(ROCKET_ID, rocketEntity.id)
    }

    @Test
    fun mapped_Active() {
        Assert.assertTrue(rocketEntity.active)
    }

    @Test
    fun mapped_country() {
        Assert.assertEquals(COUNTRY, rocketEntity.country)
    }

    @Test
    fun mapped_description() {
        Assert.assertEquals(DESCRIPTION, rocketEntity.description)
    }

    @Test
    fun mapped_rocketName() {
        Assert.assertEquals(ROCKET_NAME, rocketEntity.name)
    }

    @Test
    fun mapped_Rocket_EnginesCount() {
        Assert.assertEquals(NUMBER, rocketEntity.engineCount)
    }

    @Test
    fun mapped_Flickr() {
        Assert.assertEquals(IMAGE, rocketEntity.flickrImages)
    }
}