package com.tsdev.domain.repository

import com.tsdev.domain.model.DomainItem
import io.reactivex.rxjava3.core.Single

interface NaverMovieRepository  {
    fun getMovieList(query: String): Single<List<DomainItem>>
}