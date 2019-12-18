package com.shevchenko.space_x.network

import com.shevchenko.space_x.data.LaunchModel
import com.shevchenko.space_x.data.RocketModel
import retrofit2.Call
import retrofit2.http.GET

interface Api {
    @GET("/v3/rockets")
    fun getRockets(): Call<List<RocketModel>>

    @GET("/v3/launches")
    fun getLaunches(): Call<List<LaunchModel>>
}