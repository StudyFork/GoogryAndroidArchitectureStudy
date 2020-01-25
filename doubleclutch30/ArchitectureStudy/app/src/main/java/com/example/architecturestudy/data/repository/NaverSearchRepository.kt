package com.example.architecturestudy.data.repository

import com.example.architecturestudy.data.local.Entity.BlogEntity
import com.example.architecturestudy.data.local.Entity.MovieEntity
import com.example.architecturestudy.data.model.BlogItem
import com.example.architecturestudy.data.model.ImageItem
import com.example.architecturestudy.data.model.KinItem
import com.example.architecturestudy.data.model.MovieItem

interface NaverSearchRepository  {
    fun getMovie(isNetwork: Boolean, keyword: String, success: (List<MovieItem>) -> Unit, fail: (Throwable) -> Unit)
    fun getBlog(isNetwork: Boolean, keyword: String, success: (List<BlogItem>) -> Unit, fail: (Throwable) -> Unit)
    fun getKin(isNetwork: Boolean, keyword: String, success: (List<KinItem>) -> Unit, fail: (Throwable) -> Unit)
    fun getImage(isNetwork: Boolean, keyword: String, success: (List<ImageItem>) -> Unit, fail: (Throwable) -> Unit)

    fun getLastMovie(success: (List<MovieItem>) -> Unit, fail: (Throwable) -> Unit)
    fun getLastBlog(success: (List<BlogItem>) -> Unit, fail: (Throwable) -> Unit)
    fun getLastKin(success: (List<KinItem>) -> Unit, fail: (Throwable) -> Unit)
    fun getLastImage(success: (List<ImageItem>) -> Unit, fail: (Throwable) -> Unit)
}
