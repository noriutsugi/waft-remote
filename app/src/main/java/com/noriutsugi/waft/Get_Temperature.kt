package com.noriutsugi.waft

import retrofit2.Call
import retrofit2.http.GET

interface Get_Temperature {
    @GET("sensor/temperature")
    fun getData(): Call<Temperature>
}