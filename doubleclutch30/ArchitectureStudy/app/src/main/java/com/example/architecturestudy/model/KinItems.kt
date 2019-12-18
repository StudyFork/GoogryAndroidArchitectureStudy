package com.example.architecturestudy.model

import com.google.gson.annotations.SerializedName

data class KinData (val items : List<KinItems>)

data class KinItems(

    @SerializedName("title")
    val title : String,
    @SerializedName("description")
    val description : String,
    @SerializedName("link")
    val link : String

)