package com.architecturestudy.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "upbitTicker")
data class UpbitTicker(
    @PrimaryKey
    @ColumnInfo(name = "market")
    @SerializedName("market")
    val market: String,

    @ColumnInfo(name = "trade_price")
    @SerializedName("trade_price")
    val tradePrice: Double?,

    @ColumnInfo(name = "signed_change_rate")
    @SerializedName("signed_change_rate")
    val signedChangeRate: Double?,

    @ColumnInfo(name = "acc_trade_price_24h")
    @SerializedName("acc_trade_price_24h")
    val accTradePrice24h: Double?
)