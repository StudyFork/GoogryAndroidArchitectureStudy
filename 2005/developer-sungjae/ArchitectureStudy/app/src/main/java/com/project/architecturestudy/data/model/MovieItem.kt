package com.project.architecturestudy.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieItem(
    var title: String,
    var link: String,
    var image: String,
    var subtitle: String,
    var pubDate: String,
    var director: String,
    var actor: String,
    var userRating: Double
) : Parcelable {
    constructor() : this("", "", "", "", "", "", "", 0.0)
}