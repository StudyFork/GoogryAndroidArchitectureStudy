package com.example.androidarchitecture.data.repository

import com.example.androidarchitecture.data.datasource.remote.NaverRemoteDsInterface
import com.example.androidarchitecture.data.response.BlogData
import com.example.androidarchitecture.data.response.ImageData
import com.example.androidarchitecture.data.response.KinData
import com.example.androidarchitecture.data.response.MovieData

interface NaverRepoInterface {
    val naverRemoteDsInterface: NaverRemoteDsInterface

    fun getMovie(
        query: String,
        start: Int,
        display: Int,
        success: (List<MovieData>) -> Unit,
        fail: (Throwable) -> Unit
    )

    fun getBlog(
        query: String,
        start: Int,
        display: Int,
        success: (List<BlogData>) -> Unit,
        fail: (Throwable) -> Unit
    )

    fun getImage(
        query: String,
        start: Int,
        display: Int,
        success: (List<ImageData>) -> Unit,
        fail: (Throwable) -> Unit
    )

    fun getKin(
        query: String,
        start: Int,
        display: Int,
        success: (List<KinData>) -> Unit,
        fail: (Throwable) -> Unit
    )
}