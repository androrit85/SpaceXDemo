package com.spacexapidemo.data.mapper

import com.spacexapidemo.data.dto.RocketLaunchesDto
import com.spacexapidemo.domain.entity.LaunchEntity
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

class LaunchDtoToEntityExtensionKtTest {
    companion object {
        const val MISSION_NAME = "mission"
        const val LAUNCH_DATE_UTC = "2021"
        const val LAUNCH_DATE_UNIX = 1248752012
        const val LAUNCH_SUCCESS = true
        const val MISSION_PATCH_LARGE = "patch big"
        const val MISSION_PATCH_SMALL = "patch small"
        const val ROCKET_ID = "id"
        val IMAGE = listOf("String1", "String2")
        const val ID = "id"
    }
    private lateinit var launchEntity: LaunchEntity.Launches

    @Before
    fun init() {
        val patchDto = RocketLaunchesDto.Patch(
            MISSION_PATCH_SMALL,
            MISSION_PATCH_LARGE
        )

        val flickr = RocketLaunchesDto.Flickr(
            IMAGE,
            IMAGE
        )
        val linksDto = RocketLaunchesDto.Links(patchDto, flickr)

        val launchDto = RocketLaunchesDto.LaunchItem(
            linksDto,
            LAUNCH_DATE_UTC,
            LAUNCH_DATE_UNIX,
            LAUNCH_SUCCESS,
            MISSION_NAME,
            ID,
            ROCKET_ID
        )
        launchEntity = launchDto.map()
    }

    @Test
    fun mapped_launchDate_is_valid() {
        Assert.assertEquals(LAUNCH_DATE_UNIX, launchEntity.dateUnix)
    }

    @Test
    fun mapped_mission_name_is_valid() {
        Assert.assertEquals(MISSION_NAME, launchEntity.missionName)
    }

    @Test
    fun mapped_launch_success_is_valid() {
        Assert.assertTrue(launchEntity.launchStatus)
    }

    @Test
    fun mapped_rocket_id_is_valid() {
        Assert.assertEquals(ROCKET_ID, launchEntity.rocketId)
    }

    @Test
    fun mapped_patch_image_small_is_valid() {
        Assert.assertEquals(MISSION_PATCH_SMALL, launchEntity.patchImages)
    }
}