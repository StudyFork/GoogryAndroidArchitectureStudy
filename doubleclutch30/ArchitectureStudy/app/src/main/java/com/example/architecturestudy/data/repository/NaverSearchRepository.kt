package com.example.architecturestudy.data.repository

import com.example.architecturestudy.data.model.BlogItem
import com.example.architecturestudy.data.model.ImageItem
import com.example.architecturestudy.data.model.KinItem
import com.example.architecturestudy.data.model.MovieItem
import com.example.architecturestudy.network.response.BlogData
import com.example.architecturestudy.network.response.ImageData
import com.example.architecturestudy.network.response.KinData
import com.example.architecturestudy.network.response.MovieData
import io.reactivex.Single

interface NaverSearchRepository  {
    fun getMovie(keyword: String): Single<MovieData>
    fun getBlog(keyword: String): Single<BlogData>
    fun getKin(keyword: String): Single<KinData>
    fun getImage(keyword: String): Single<ImageData>

    fun getLastMovie(): Single<List<MovieItem>>
    fun getLastBlog(): Single<List<BlogItem>>
    fun getLastKin(): Single<List<KinItem>>
    fun getLastImage(): Single<List<ImageItem>>
}
