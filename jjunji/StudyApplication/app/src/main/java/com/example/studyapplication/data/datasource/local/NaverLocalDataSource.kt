package com.example.studyapplication.data.datasource.local

import com.example.studyapplication.data.model.BlogInfo
import com.example.studyapplication.data.model.ImageInfo
import com.example.studyapplication.data.model.KinInfo
import com.example.studyapplication.data.model.MovieInfo

interface NaverLocalDataSource {
    fun saveMovieList(arrItem: ArrayList<MovieInfo>)
    fun saveImageList(arrItem: ArrayList<ImageInfo>)
    fun saveBlogList(arrItem: ArrayList<BlogInfo>)
    fun saveKinList(arrItem: ArrayList<KinInfo>)

    fun getCacheMovieList(success : (ArrayList<MovieInfo>) -> Unit, failed : (Throwable) -> Unit)
    fun getCacheImageList(success : (ArrayList<ImageInfo>) -> Unit, failed : (Throwable) -> Unit)
    fun getCacheBlogList(success : (ArrayList<BlogInfo>) -> Unit, failed : (Throwable) -> Unit)
    fun getCacheKinList(success : (ArrayList<KinInfo>) -> Unit, failed : (Throwable) -> Unit)

    fun deleteMovieList()
    fun deleteImageList()
    fun deleteBlogList()
    fun deleteKinList()
}