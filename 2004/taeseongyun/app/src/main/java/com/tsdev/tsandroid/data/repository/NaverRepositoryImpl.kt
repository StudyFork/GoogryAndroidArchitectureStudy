package com.tsdev.tsandroid.data.repository

import com.tsdev.tsandroid.Item
import com.tsdev.tsandroid.NaverMovieInterface
import com.tsdev.tsandroid.MovieResponse
import com.tsdev.tsandroid.data.source.NaverRemoteDataSource
import com.tsdev.tsandroid.data.source.NaverRemoteDataSourceImpl
import com.tsdev.tsandroid.util.htmlConvert
import io.reactivex.rxjava3.core.Single

class NaverRepositoryImpl(private val naverAPI: NaverMovieInterface) : NaverReopsitory {

    private val naverRemoteDataSourceImpl: NaverRemoteDataSource by lazy {
        NaverRemoteDataSourceImpl(naverAPI)
    }

    override fun getMovieList(query: String): Single<List<Item>> =
        naverRemoteDataSourceImpl.getMovieList(query).flatMap {
            convertRemoteData(it)
        }

    private fun convertRemoteData(movieResponse: MovieResponse): Single<List<Item>> {

        val convertHtml = movieResponse.items.map { item ->
            item.actor.htmlConvert().split("|").map { it.trim() }
                .filter { it != "" }
                .joinToString { it }
            item.title.htmlConvert()
            item.subtitle.htmlConvert()
            item.director.htmlConvert().split("|").map { it.trim() }
                .filter { it != "" }
                .joinToString { it }
            item
        }

        return Single.just(convertHtml)
    }
}