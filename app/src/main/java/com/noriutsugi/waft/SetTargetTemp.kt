package com.noriutsugi.waft

import retrofit2.Call
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface SetTargetTemp {
    @Headers("Content-Type: application/json")
    @POST("climate/air_conditioner/set")
    fun setData(@Query("target_temperature") id: Int): Call<EmptyPost>

}