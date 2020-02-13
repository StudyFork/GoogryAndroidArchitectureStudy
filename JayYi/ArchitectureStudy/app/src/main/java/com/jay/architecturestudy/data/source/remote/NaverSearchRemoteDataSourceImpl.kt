package com.jay.architecturestudy.data.source.remote

import com.jay.architecturestudy.data.model.ResponseBlog
import com.jay.architecturestudy.data.model.ResponseImage
import com.jay.architecturestudy.data.model.ResponseKin
import com.jay.architecturestudy.data.model.ResponseMovie
import com.jay.architecturestudy.network.Api
import io.reactivex.Single

class NaverSearchRemoteDataSourceImpl(
    private val api: Api
) : NaverSearchRemoteDataSource {

    override fun getMovie(
        keyword: String
    ): Single<ResponseMovie> =
        api.getMovies(keyword)

    override fun getImage(
        keyword: String
    ): Single<ResponseImage> =
        api.getImages(keyword)

    override fun getBlog(
        keyword: String
    ): Single<ResponseBlog> =
        api.getBlog(keyword)

    override fun getKin(
        keyword: String
    ): Single<ResponseKin> =
        api.getKin(keyword)

}