package com.example.androidarchitecture.data.repository

import com.example.androidarchitecture.data.datasource.local.NaverLocalDataSourceInterface
import com.example.androidarchitecture.data.datasource.remote.NaverRemoteDsInterface
import com.example.androidarchitecture.data.response.BlogData
import com.example.androidarchitecture.data.response.ImageData
import com.example.androidarchitecture.data.response.KinData
import com.example.androidarchitecture.data.response.MovieData

class NaverRepoImpl(
    private val naverRemoteDs: NaverRemoteDsInterface,
    private val naverLocalDs: NaverLocalDataSourceInterface
) : NaverRepoInterface {

    override suspend fun getBlogHist(): List<BlogData> = naverLocalDs.getBlogHist()



    override fun getBlog(
        query: String,
        start: Int,
        display: Int,
        success: (result: List<BlogData>) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        naverRemoteDs.getBlog(query, start, display, {
            naverLocalDs.saveBlogHist(it) // 로컬 디비에 저장.
            success(it)
        }, fail)

    }

    override fun getMovie(
        query: String,
        start: Int,
        display: Int,
        success: (List<MovieData>) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        naverRemoteDs.getMovie(query, start, display, success, fail)
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

    override fun saveBlogKeyword(text: String) {
        naverLocalDs.saveBlogKeyword(text)
    }

    override fun getBlogKeyword(): String =
        naverLocalDs.getBlogKeyword()



}