package com.olaf.nukeolaf.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieItem(
    var title: String,
    val link: String,
    val image: String,
    var subtitle: String,
    val pubDate: String,
    var director: String,
    var actor: String,
    val userRating: Float
) : Parcelable