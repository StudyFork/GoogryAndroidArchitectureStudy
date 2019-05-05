package ado.sabgil.studyproject.data.remote.upbit.response

import com.google.gson.annotations.SerializedName

data class UpbitMarketCodeResponse(
    @SerializedName("market") val market: String,
    @SerializedName("korean_name") val koreanName: String,
    @SerializedName("english_name") val englishName: String
)