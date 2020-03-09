package com.example.architecturestudy.data.source.remote

import com.example.architecturestudy.network.Api
import com.example.architecturestudy.network.response.BlogData
import com.example.architecturestudy.network.response.ImageData
import com.example.architecturestudy.network.response.KinData
import com.example.architecturestudy.network.response.MovieData
import io.reactivex.Single

class NaverSearchRemoteDataSourceImpl(private val restClient: Api) : NaverSearchRemoteDataSource {

    override fun getMovie(keyword: String): Single<MovieData> {
        return restClient.requestMovies(keyword)
    }

    override fun getBlog(keyword: String): Single<BlogData> {
        return restClient.requestBlog(keyword)
    }

    override fun getKin(keyword: String): Single<KinData> {
        return restClient.requestKin(keyword)
    }

    override fun getImage(keyword: String): Single<ImageData> {
        return restClient.requestImage(keyword)
    }
}