package com.noriutsugi.waft

import retrofit2.Call
import retrofit2.http.GET

interface Get_Brightness {
    @GET("sensor/brightness")
    fun getData(): Call<Brightness>
}