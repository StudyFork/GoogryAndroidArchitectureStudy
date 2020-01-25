package com.example.architecturestudy.data.source.local

import com.example.architecturestudy.data.local.Entity.BlogEntity
import com.example.architecturestudy.data.local.Entity.ImageEntity
import com.example.architecturestudy.data.local.Entity.KinEntity
import com.example.architecturestudy.data.local.Entity.MovieEntity
import com.example.architecturestudy.data.model.KinItem

interface NaverSearchLocalDataSource {

    fun saveMovieItems(items: List<MovieEntity>)
    fun saveBlogItems(items: List<BlogEntity>)
    fun saveKinItems(items: List<KinEntity>)
    fun saveImageItems(items: List<ImageEntity>)

    fun deleteMovie(items: List<MovieEntity>)
    fun deleteBlog(items: List<BlogEntity>)
    fun deleteKin(items: List<KinEntity>)
    fun deleteImage(items: List<ImageEntity>)

    fun getMovieItems(
        success: (items: List<MovieEntity>) -> Unit,
        fail: (Throwable) -> Unit
    )
    fun getBlogItems(
        success: (items: List<BlogEntity>) -> Unit,
        fail: (Throwable) -> Unit
    )
    fun getKiItems(
        success: (items: List<KinEntity>) -> Unit,
        fail: (Throwable) -> Unit
    )
    fun getImageItems(
        success: (items: List<ImageEntity>) -> Unit,
        fail: (Throwable) -> Unit
    )
}