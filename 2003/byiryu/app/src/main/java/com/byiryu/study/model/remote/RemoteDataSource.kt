package com.byiryu.study.model.remote

import com.byiryu.study.model.Apis
import com.byiryu.study.model.data.MovieResponse
import io.reactivex.Single

class RemoteDataSource(private val apis: Apis) {


    fun getMoveList(query: String): Single<MovieResponse> {

        return apis.getSearchMovie(query)

    }


}