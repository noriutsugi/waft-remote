package com.noriutsugi.waft

import retrofit2.Call
import retrofit2.http.GET

interface Get_Humidity {
    @GET("sensor/humidity")
    fun getData(): Call<Humidity>
}