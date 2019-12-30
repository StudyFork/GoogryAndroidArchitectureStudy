package com.jay.architecturestudy.data.source.remote

import com.jay.architecturestudy.data.model.ResponseBlog
import com.jay.architecturestudy.data.model.ResponseImage
import com.jay.architecturestudy.data.model.ResponseKin
import com.jay.architecturestudy.data.model.ResponseMovie
import com.jay.architecturestudy.network.Api
import io.reactivex.Single

class NaverSearchRemoteDataSourceImpl : NaverSearchRemoteDataSource {

    override fun getMovie(
        keyword: String
    ) : Single<ResponseMovie> =
        Api.getMovies(keyword)

    override fun getImage(
        keyword: String
    ) : Single<ResponseImage> =
        Api.getImages(keyword)

    override fun getBlog(
        keyword: String
    ) : Single<ResponseBlog> =
        Api.getBlog(keyword)

    override fun getKin(
        keyword: String
    ) : Single<ResponseKin> =
        Api.getKin(keyword)

}