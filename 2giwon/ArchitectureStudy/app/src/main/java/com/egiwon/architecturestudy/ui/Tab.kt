package com.egiwon.architecturestudy.ui

import android.os.Parcelable
import com.egiwon.architecturestudy.R
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class Tab(val stringResId: Int) : Parcelable {
    BLOG(R.string.blog_tab),
    NEWS(R.string.news_tab),
    MOVIE(R.string.movie_tab),
    BOOK(R.string.book_tab);
}