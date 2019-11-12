package com.egiwon.architecturestudy.tabs

import android.annotation.SuppressLint

enum class Tab {
    BLOG,
    NEWS,
    MOVIE,
    BOOK;

    @SuppressLint("DefaultLocale")
    override fun toString(): String {
        return super.toString().toLowerCase()
    }
}