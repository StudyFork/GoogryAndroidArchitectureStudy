package com.example.architecturestudy.data.source.local

import com.example.architecturestudy.data.local.Entity.BlogEntity
import com.example.architecturestudy.data.local.Entity.MovieEntity

interface NaverSearchLocalDataSource {

    fun saveMovieItems(items: List<MovieEntity>)

    fun getMovieItems(
        success: (items: List<MovieEntity>) -> Unit,
        fail: (Throwable) -> Unit
    )

    fun deleteMovie(items: List<MovieEntity>)

    fun saveBlogItems(items: List<BlogEntity>)

    fun getBlogItems(
        success: (items: List<BlogEntity>) -> Unit,
        fail: (Throwable) -> Unit
    )

    fun deleteBlog(items: List<BlogEntity>)
}