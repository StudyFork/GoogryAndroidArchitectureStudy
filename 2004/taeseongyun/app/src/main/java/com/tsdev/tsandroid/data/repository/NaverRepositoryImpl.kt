package com.tsdev.tsandroid.data.repository

import com.tsdev.tsandroid.data.Item
import com.tsdev.tsandroid.network.NaverMovieInterface
import com.tsdev.tsandroid.data.MovieResponse
import com.tsdev.tsandroid.data.source.NaverRemoteDataSource
import com.tsdev.tsandroid.data.source.NaverRemoteDataSourceImpl
import com.tsdev.tsandroid.util.htmlConvert
import io.reactivex.rxjava3.core.Single

class NaverRepositoryImpl(private val naverAPI: NaverMovieInterface) : NaverReopsitory {

    private val naverRemoteDataSourceImpl: NaverRemoteDataSource by lazy {
        NaverRemoteDataSourceImpl(naverAPI)
    }

    override fun getMovieList(query: String): Single<List<Item>> =
        naverRemoteDataSourceImpl.getMovieList(query).concatMap {
            convertRemoteData(it)
        }

    private fun convertRemoteData(movieResponse: MovieResponse): Single<List<Item>> {

        val convertHtml = movieResponse.items.map { item ->
            Item(
                item.actor.htmlConvert().split("|").map { it.trim() }
                    .filter { it != "" }
                    .joinToString { it },
                item.director.split("|").map { it.trim() }
                    .filter { it != "" }
                    .joinToString { it },
                item.image,
                item.link,
                item.pubDate,
                item.subtitle.htmlConvert().toString(),
                item.title.htmlConvert().toString(),
                item.userRating
            )
        }

        return Single.just(convertHtml)
    }
}