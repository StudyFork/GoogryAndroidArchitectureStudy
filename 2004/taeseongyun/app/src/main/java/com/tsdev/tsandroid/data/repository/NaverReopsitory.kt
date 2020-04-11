package com.tsdev.tsandroid.data.repository

import com.tsdev.tsandroid.Item
import io.reactivex.rxjava3.core.Single

interface NaverReopsitory {
    fun getMovieList(query: String): Single<List<Item>>
}