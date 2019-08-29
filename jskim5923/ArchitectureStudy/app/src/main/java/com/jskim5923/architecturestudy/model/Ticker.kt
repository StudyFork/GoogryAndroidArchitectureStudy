package com.jskim5923.architecturestudy.model

import androidx.annotation.ColorRes

data class Ticker(
    val name: String,
    val currentPrice: String,
    val diff: String,
    @ColorRes val diffTextColorId: Int,
    val tradeVolume: String
)