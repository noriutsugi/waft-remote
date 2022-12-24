package com.noriutsugi.waft

import retrofit2.Call
import retrofit2.http.GET

interface GetClimate {
    @GET("climate/air_conditioner")
    fun getData(): Call<Climate>
}