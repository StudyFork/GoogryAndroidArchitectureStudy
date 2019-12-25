package com.example.architecturestudy.data.repository

import com.example.architecturestudy.data.model.BlogItem
import com.example.architecturestudy.data.model.ImageItem
import com.example.architecturestudy.data.model.KinItem
import com.example.architecturestudy.data.model.MovieItem

interface NaverSearchRepository  {
    fun getMovie(keyword: String, success: (List<MovieItem>) -> Unit, fail: (Throwable) -> Unit)
    fun getBlog(keyword: String, success: (List<BlogItem>) -> Unit, fail: (Throwable) -> Unit)
    fun getKin(keyword: String, success: (List<KinItem>) -> Unit, fail: (Throwable) -> Unit)
    fun getImage(keyword: String, success: (List<ImageItem>) -> Unit, fail: (Throwable) -> Unit)
}