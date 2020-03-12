package com.byiryu.study.model.source.remote

import com.byiryu.study.model.data.MovieResponse
import com.byiryu.study.network.NetworkService.retrofit
import io.reactivex.Single

class RemoteDataSource {


    fun getMoveList(query: String): Single<MovieResponse> {

        return retrofit.getSearchMovie(query)

    }


}