package com.spacexapidemo.data

import com.spacexapidemo.data.dto.RocketDto
import com.spacexapidemo.data.dto.RocketLaunchesDto
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("v4/rockets")
    fun getRockets() : Single<List<RocketDto.Rocket>>

    @GET("v4/launches")
    fun getLaunches(@Query("rocket")id: String) : Single<List<RocketLaunchesDto.LaunchItem>>
}