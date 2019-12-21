package com.example.studyapplication.data.model

import com.google.gson.annotations.SerializedName

class SearchKinResult {
    @SerializedName("items")
    val arrKinInfo = emptyArray<KinInfo>()

    data class KinInfo (
        var title : String,
        var link : String,
        var description : String
    )
}