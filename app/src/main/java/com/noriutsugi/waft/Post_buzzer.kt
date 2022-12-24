package com.noriutsugi.waft

import retrofit2.Call
import retrofit2.http.Headers
import retrofit2.http.POST

interface Post_buzzer {
    @Headers("Content-Type: application/json")
    @POST("switch/play/turn_on")
    fun setData(): Call<EmptyPost>
}