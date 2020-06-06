package com.tsdev.tsandroid.data.repository

import com.tsdev.tsandroid.data.Item
import com.tsdev.tsandroid.data.source.NaverRemoteDataSource
import com.tsdev.tsandroid.util.AbstractMapConverter
import io.reactivex.rxjava3.core.Single

class NaverRepositoryImpl(
    private val movieMapConverter: AbstractMapConverter<List<Item>, List<Item>>,
    private val naverRemoteDataSourceImpl: NaverRemoteDataSource
) :
    NaverReopsitory {

    override fun getMovieList(query: String): Single<List<Item>> =
        naverRemoteDataSourceImpl.getMovieList(query).map {
            movieMapConverter.toMap(it.items)
        }
}