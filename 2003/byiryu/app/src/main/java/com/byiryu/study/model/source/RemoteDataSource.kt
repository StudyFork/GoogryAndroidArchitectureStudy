package com.byiryu.study.model.source

import com.byiryu.study.model.entity.MovieItem
import com.byiryu.study.model.entity.MovieResponse
import com.byiryu.study.network.NetworkService.retrofit
import io.reactivex.Single

class RemoteDataSource {


    fun getMoveList(query: String): Single<MovieResponse> {

        return retrofit.getSearchMovie(query)

    }


}