package com.tsdev.data.source

import com.tsdev.data.model.EntryItem
import io.reactivex.rxjava3.core.Single

interface NaverMovieRemoteSourceData {
    fun getMovieList(query: String): Single<EntryItem>
}