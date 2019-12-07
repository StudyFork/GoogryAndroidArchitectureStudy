package com.jay.architecturestudy.data.source.remote

import com.jay.architecturestudy.data.model.Blog
import com.jay.architecturestudy.data.model.Image
import com.jay.architecturestudy.data.model.Kin
import com.jay.architecturestudy.data.model.Movie

class NaverSearchRemoteDataSourceImpl : NaverSearchRemoteDataSource {
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