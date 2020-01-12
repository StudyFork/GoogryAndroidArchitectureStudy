package com.example.androidarchitecture.data.datasource.local

import com.example.androidarchitecture.data.response.BlogData
import com.example.androidarchitecture.data.response.ImageData
import com.example.androidarchitecture.data.response.KinData
import com.example.androidarchitecture.data.response.MovieData

interface NaverLocalDataSourceInterface {

    fun saveBlogHist(blog: List<BlogData>)
    fun saveMovieHist(movie: List<MovieData>)
    fun saveImageHist(image: List<ImageData>)
    fun saveKinHist(kin: List<KinData>)


    suspend fun getBlogHist(): List<BlogData>
    suspend fun getMovieHist(): List<MovieData>
    suspend fun getImageHist(): List<ImageData>
    suspend fun getKinHist(): List<KinData>


    fun saveBlogKeyword(keyword: String)
    fun saveMovieKeyword(keyword: String)
    fun saveImageKeyword(keyword: String)
    fun saveKinKeyword(keyword: String)

    fun getBlogKeyword(): String
    fun getMovieKeyword(): String
    fun getImageKeyword(): String
    fun getKinKeyword(): String
}