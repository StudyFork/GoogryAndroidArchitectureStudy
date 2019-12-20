package com.example.androidarchitecture.data.repository

import com.example.androidarchitecture.data.datasource.remote.NaverRemoteDs
import com.example.androidarchitecture.data.response.BlogData
import com.example.androidarchitecture.data.response.ImageData
import com.example.androidarchitecture.data.response.KinData
import com.example.androidarchitecture.data.response.MovieData

class NaverRepo : NaverRepoInterface {
    private val naverRemoteDs = NaverRemoteDs()


    override fun getMovie(
        query: String,
        start: Int,
        display: Int,
        success: (List<MovieData>) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        naverRemoteDs.getMovie(query, start, display, success, fail)
    }

    override fun getBlog(
        query: String,
        start: Int,
        display: Int,
        success: (List<BlogData>) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        naverRemoteDs.getBlog(query, start, display, success, fail)
    }

    override fun getImage(
        query: String,
        start: Int,
        display: Int,
        success: (List<ImageData>) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        naverRemoteDs.getImage(query, start, display, success, fail)
    }

    override fun getKin(
        query: String,
        start: Int,
        display: Int,
        success: (List<KinData>) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        naverRemoteDs.getKin(query, start, display, success, fail)
    }


}