package com.example.architecturestudy.data.source.remote

import com.example.architecturestudy.data.model.BlogItems
import com.example.architecturestudy.data.model.ImageItems
import com.example.architecturestudy.data.model.KinItems
import com.example.architecturestudy.data.model.MovieItems

interface NaverSearchRemoteDataSource {

    fun getMovie(keyword: String, success: (List<MovieItems>) -> Unit, fail: (Throwable) -> Unit)
    fun getBlog(keyword: String, success: (List<BlogItems>) -> Unit, fail: (Throwable) -> Unit)
    fun getKin(keyword: String, success: (List<KinItems>) -> Unit, fail: (Throwable) -> Unit)
    fun getImage(keyword: String, success: (List<ImageItems>) -> Unit, fail: (Throwable) -> Unit)
}