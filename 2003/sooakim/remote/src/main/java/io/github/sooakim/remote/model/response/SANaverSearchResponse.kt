package io.github.sooakim.remote.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.Date

internal data class SANaverSearchResponse<Model : io.github.sooakim.remote.model.SAModel>(
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