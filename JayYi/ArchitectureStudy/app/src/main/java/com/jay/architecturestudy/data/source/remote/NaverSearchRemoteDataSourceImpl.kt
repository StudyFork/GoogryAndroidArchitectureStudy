package com.jay.architecturestudy.data.source.remote

import com.jay.architecturestudy.data.model.Movie

class NaverSearchRemoteDataSourceImpl : NaverSearchRemoteDataSource {
    override fun getMovie(keyword: String, success: (Movie) -> Unit, fail: (Throwable) -> Unit) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getImage(keyword: String, success: (Movie) -> Unit, fail: (Throwable) -> Unit) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBlog(keyword: String, success: (Movie) -> Unit, fail: (Throwable) -> Unit) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getKin(keyword: String, success: (Movie) -> Unit, fail: (Throwable) -> Unit) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}