package com.jay.architecturestudy.data.source.remote

import com.jay.architecturestudy.data.model.Blog
import com.jay.architecturestudy.data.model.Image
import com.jay.architecturestudy.data.model.Kin
import com.jay.architecturestudy.data.model.Movie

interface NaverSearchRemoteDataSource {

    fun getMovie(keyword: String, success: (Movie) -> Unit, fail: (Throwable) -> Unit)

    fun getImage(keyword: String, success: (Image) -> Unit, fail: (Throwable) -> Unit)

    fun getBlog(keyword: String, success: (Blog) -> Unit, fail: (Throwable) -> Unit)

    fun getKin(keyword: String, success: (Kin) -> Unit, fail: (Throwable) -> Unit)
}