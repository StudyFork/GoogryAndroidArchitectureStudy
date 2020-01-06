package com.example.kotlinapplication.data.repository

import com.example.kotlinapplication.data.model.*
import com.example.kotlinapplication.data.source.local.LocalDataSourceImpl
import com.example.kotlinapplication.data.source.remote.RemoteDataSourceImpl
import io.reactivex.Single

class DataRepositoryImpl : DataRepository {
    private val remote: RemoteDataSourceImpl = RemoteDataSourceImpl()
    private val local: LocalDataSourceImpl = LocalDataSourceImpl()

    override fun callImageResources(query: String): Single<ResponseItems<ImageItem>> {
        return remote.getImageCall(query).doOnSuccess {
            local.setImageCall(it.items)
        }
    }

    override fun callBlogResources(query: String): Single<ResponseItems<BlogItem>> {
        return remote.getBlogCall(query).doOnSuccess {
            local.setBlogCall(it.items)
        }
    }

    override fun callKinResources(query: String): Single<ResponseItems<KinItem>> {
        return remote.getKinCall(query).doOnSuccess {
            local.setKinCall(it.items)
        }
    }

    override fun callMovieResources(query: String): Single<ResponseItems<MovieItem>> {
        return remote.getMovieCall(query).doOnSuccess {
            local.setMovieCall(it.items)
        }
    }

}