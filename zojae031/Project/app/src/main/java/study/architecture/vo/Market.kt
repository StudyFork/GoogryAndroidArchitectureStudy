package study.architecture.vo

import com.google.gson.annotations.SerializedName

data class Market(
    @SerializedName("english_name") val english_name: String,
    @SerializedName("korean_name") val korean_name: String,
    @SerializedName("market") val market: String
)