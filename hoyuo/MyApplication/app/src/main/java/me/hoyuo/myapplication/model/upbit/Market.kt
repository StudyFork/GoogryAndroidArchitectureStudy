package me.hoyuo.myapplication.model.upbit

import java.io.Serializable

data class Market(
    val english_name: String,
    val korean_name: String,
    val market: String
) : Serializable