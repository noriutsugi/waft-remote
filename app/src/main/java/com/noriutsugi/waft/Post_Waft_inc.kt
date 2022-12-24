package com.noriutsugi.waft

import retrofit2.Call
import retrofit2.http.Headers
import retrofit2.http.POST

interface Post_Waft_inc {
    @Headers("Content-Type: application/json")
    @POST("switch/waft_inc/turn_on")
    fun setData(): Call<EmptyPost>
}