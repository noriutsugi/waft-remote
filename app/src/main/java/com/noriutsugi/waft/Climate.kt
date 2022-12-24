package com.noriutsugi.waft

data class Climate(
    val current_temperature: String,
    val fan_mode: String,
    val id: String,
    val max_temp: String,
    val min_temp: String,
    val mode: String,
    val state: String,
    val step: Int,
    val swing_mode: String,
    val target_temperature: String
)