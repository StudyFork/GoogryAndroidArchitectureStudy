package me.hoyuo.myapplication.model.upbit

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Market(
    @SerializedName("english_name") val englishName: String,
    @SerializedName("korean_name") val koreanName: String,
    @SerializedName("market") val market: String
) : Parcelable