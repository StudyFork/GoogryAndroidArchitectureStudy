package com.example.androidarchitecture.data.repository

import com.example.androidarchitecture.data.response.BlogData
import com.example.androidarchitecture.data.response.ImageData
import com.example.androidarchitecture.data.response.KinData
import com.example.androidarchitecture.data.response.MovieData

interface NaverRepoInterface {

    fun getBlog(
        query: String,
        start: Int,
        display: Int,
        success: (result: List<BlogData>) -> Unit,
        fail: (Throwable) -> Unit
    )

    fun getMovie(
        query: String,
        start: Int,
        display: Int,
        success: (result: List<MovieData>) -> Unit,
        fail: (Throwable) -> Unit
    )


    fun getImage(
        query: String,
        start: Int,
        display: Int,
        success: (result: List<ImageData>) -> Unit,
        fail: (Throwable) -> Unit
    )

    fun getKin(
        query: String,
        start: Int,
        display: Int,
        success: (result: List<KinData>) -> Unit,
        fail: (Throwable) -> Unit
    )

    suspend fun getBlogHist(): List<BlogData>
    suspend fun getMovieHist(): List<MovieData>
    suspend fun getImageHist(): List<ImageData>
    suspend fun getKinHist(): List<KinData>


    fun saveBlogKeyword(text: String)
    fun getBlogKeyword(): String
    fun saveMovieKeyword(text: String)
    fun getMoiveKeyword(): String
    fun saveImageKeyword(text: String)
    fun getImageKeyword(): String
    fun saveKinKeyword(text: String)
    fun getKinKeyword(): String

}