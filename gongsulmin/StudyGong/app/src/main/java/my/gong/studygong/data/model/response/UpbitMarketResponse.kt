package my.gong.studygong.data.model.response

import com.google.gson.annotations.SerializedName


data class UpbitMarketResponse(
    @SerializedName("market") val market: String,
    @SerializedName("korean_name") val koreanName: String,
    @SerializedName("english_name") val englishName: String
)