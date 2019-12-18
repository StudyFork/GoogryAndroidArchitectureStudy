package com.example.androidarchitecture.data.repository

import com.example.androidarchitecture.data.datasource.remote.NaverRemoteDs
import com.example.androidarchitecture.data.datasource.remote.NaverRemoteDsInterface
import com.example.androidarchitecture.data.response.BlogData
import com.example.androidarchitecture.data.response.ImageData
import com.example.androidarchitecture.data.response.KinData
import com.example.androidarchitecture.data.response.MovieData

class NaverRepo : NaverRepoInterface {
    override val naverRemoteDsInterface: NaverRemoteDsInterface by lazy {
        NaverRemoteDs()
    }

    override fun getMovie(
        query: String,
        start: Int,
        display: Int,
        success: (List<MovieData>) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        naverRemoteDsInterface.getMovie(query, start, display, success, fail)
    }

    override fun getBlog(
        query: String,
        start: Int,
        display: Int,
        success: (List<BlogData>) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        naverRemoteDsInterface.getBlog(query, start, display, success, fail)
    }

    override fun getImage(
        query: String,
        start: Int,
        display: Int,
        success: (List<ImageData>) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        naverRemoteDsInterface.getImage(query, start, display, success, fail)
    }

    override fun getKin(
        query: String,
        start: Int,
        display: Int,
        success: (List<KinData>) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        naverRemoteDsInterface.getKin(query, start, display, success, fail)
    }


}