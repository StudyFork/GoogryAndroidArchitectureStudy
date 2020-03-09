package com.example.architecturestudy.data.source.local

import com.example.architecturestudy.data.local.entity.BlogEntity
import com.example.architecturestudy.data.local.entity.ImageEntity
import com.example.architecturestudy.data.local.entity.KinEntity
import com.example.architecturestudy.data.local.entity.MovieEntity
import io.reactivex.Completable
import io.reactivex.Single

interface NaverSearchLocalDataSource {

    fun saveMovieItems(items: List<MovieEntity>): Completable
    fun saveBlogItems(items: List<BlogEntity>): Completable
    fun saveKinItems(items: List<KinEntity>): Completable
    fun saveImageItems(items: List<ImageEntity>): Completable

    fun deleteMovie(): Completable
    fun deleteBlog(): Completable
    fun deleteKin(): Completable
    fun deleteImage(): Completable

    fun getMovieItems(): Single<List<MovieEntity>>
    fun getBlogItems(): Single<List<BlogEntity>>
    fun getKiItems(): Single<List<KinEntity>>
    fun getImageItems(): Single<List<ImageEntity>>
}