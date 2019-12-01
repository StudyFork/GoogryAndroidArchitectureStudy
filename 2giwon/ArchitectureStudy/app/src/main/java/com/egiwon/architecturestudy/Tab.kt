package com.egiwon.architecturestudy

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class Tab : Parcelable {
    BLOG,
    NEWS,
    MOVIE,
    BOOK;
}