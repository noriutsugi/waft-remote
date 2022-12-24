package com.noriutsugi.waft

import retrofit2.Call
import retrofit2.http.GET

interface Get_Motion {
    @GET("binary_sensor/motion")
    fun getData(): Call<Motion>
}