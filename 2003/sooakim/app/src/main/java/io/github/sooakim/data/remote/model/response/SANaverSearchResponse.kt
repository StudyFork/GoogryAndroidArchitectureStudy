package io.github.sooakim.data.remote.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import io.github.sooakim.data.remote.model.SAModel
import java.util.*

data class SANaverSearchResponse<Model : SAModel>(
    @SerializedName("total")
    @Expose
    val total: Int,

    @SerializedName("start")
    @Expose
    val start: Int,

    @SerializedName("display")
    @Expose
    val display: Int,

    @SerializedName("lastBuildDate")
    @Expose
    val lastBuildDate: Date,

    @SerializedName("items")
    @Expose
    val items: List<Model>
) : SAResponse