package study.architecture.model.vo

import com.google.gson.annotations.SerializedName

data class Market(
    @SerializedName("english_name") val englishName: String,
    @SerializedName("korean_name") val koreanName: String,
    @SerializedName("market") val market: String
)