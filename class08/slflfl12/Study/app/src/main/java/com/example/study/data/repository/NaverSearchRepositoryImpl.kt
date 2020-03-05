package com.example.study.data.repository

import com.example.study.data.model.Movie
import com.example.study.data.source.remote.NaverSearchRemoteDataSource
import com.example.study.data.model.NaverSearchResponse
import com.example.study.data.source.local.NaverSearchLocalDataSource
import com.example.study.data.source.local.SearchResultDatabase
import com.example.study.data.source.local.model.SearchResult
import com.google.gson.Gson
import io.reactivex.Single

class NaverSearchRepositoryImpl (
    private val naverSearchLocalDataSource: NaverSearchLocalDataSource,
    private val naverSearchRemoteDataSource: NaverSearchRemoteDataSource
) : NaverSearchRepository {


    override fun getMovies(query: String): Single<NaverSearchResponse> {
        return naverSearchRemoteDataSource.getMovies(query).doAfterSuccess {
            addSearchResult(SearchResult(Gson().toJson(it.items)))
        }
    }

    override fun addSearchResult(searchResult: SearchResult) =
        naverSearchLocalDataSource.addSearchResult(searchResult)

    override fun getRecentSearchResult(): List<Movie>? {
        var list: List<Movie>? = null
        naverSearchLocalDataSource.getRecentSearchResult()?.let {
            list = Gson().fromJson(it.results, Array<Movie>::class.java).asList()
        }
        return list
    }

}