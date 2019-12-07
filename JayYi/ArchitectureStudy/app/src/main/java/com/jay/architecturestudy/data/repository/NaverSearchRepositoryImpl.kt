package com.jay.architecturestudy.data.repository

import com.jay.architecturestudy.data.model.Blog
import com.jay.architecturestudy.data.model.Image
import com.jay.architecturestudy.data.model.Kin
import com.jay.architecturestudy.data.model.Movie
import com.jay.architecturestudy.data.source.remote.NaverSearchRemoteDataSource
import com.jay.architecturestudy.data.source.remote.NaverSearchRemoteDataSourceImpl

class NaverSearchRepositoryImpl: NaverSearchRepository {
    override val naverSearchRemoteDataSource: NaverSearchRemoteDataSource by lazy {
        NaverSearchRemoteDataSourceImpl()
    }

    override fun getMovie(keyword: String, success: (Movie) -> Unit, fail: (Throwable) -> Unit) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getImage(keyword: String, success: (Image) -> Unit, fail: (Throwable) -> Unit) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBlog(keyword: String, success: (Blog) -> Unit, fail: (Throwable) -> Unit) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getKin(keyword: String, success: (Kin) -> Unit, fail: (Throwable) -> Unit) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}