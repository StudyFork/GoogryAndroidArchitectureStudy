package com.example.architecturestudy.data.repository

import com.example.architecturestudy.network.response.BlogData
import com.example.architecturestudy.network.response.ImageData
import com.example.architecturestudy.network.response.KinData
import com.example.architecturestudy.network.response.MovieData

interface NaverSearchRepository  {
    fun getMovie(keyword : String, success : (MovieData) -> Unit, fail : (Throwable) -> Unit)
    fun getBlog(keyword: String, success: (BlogData) -> Unit, fail: (Throwable) -> Unit)
    fun getKin(keyword: String, success: (KinData) -> Unit, fail: (Throwable) -> Unit)
    fun getImage(keyword: String, success: (ImageData) -> Unit, fail: (Throwable) -> Unit)

}