package com.jay.remote

import com.jay.remote.model.ResponseBlog
import com.jay.remote.model.ResponseImage
import com.jay.remote.model.ResponseKin
import com.jay.remote.model.ResponseMovie
import com.jay.remote.network.Api
import io.reactivex.Single

internal class NaverSearchRemoteDataSourceImpl(
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