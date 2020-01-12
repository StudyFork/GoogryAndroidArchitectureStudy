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
        success: (result: List<MovieData>) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        naverRemoteDs.getMovie(query, start, display, {
            naverLocalDs.saveMovieHist(it)
            success(it)
        }, fail)
    }

    override fun getImage(
        query: String,
        start: Int,
        display: Int,
        success: (result: List<ImageData>) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        naverRemoteDs.getImage(query, start, display, {
            naverLocalDs.saveImageHist(it)
            success(it)
        }, fail)
    }

    override fun getKin(
        query: String,
        start: Int,
        display: Int,
        success: (result: List<KinData>) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        naverRemoteDs.getKin(query, start, display, {
            naverLocalDs.saveKinHist(it)
            success(it)
        }, fail)
    }

    override suspend fun getBlogHist(): List<BlogData> = naverLocalDs.getBlogHist()
    override suspend fun getMovieHist(): List<MovieData> = naverLocalDs.getMovieHist()
    override suspend fun getImageHist(): List<ImageData> = naverLocalDs.getImageHist()
    override suspend fun getKinHist(): List<KinData> = naverLocalDs.getKinHist()


    override fun saveBlogKeyword(text: String) {
        naverLocalDs.saveBlogKeyword(text)
    }

    override fun saveMovieKeyword(text: String) {
        naverLocalDs.saveMovieKeyword(text)
    }

    override fun saveImageKeyword(text: String) {
        naverLocalDs.saveImageKeyword(text)
    }

    override fun saveKinKeyword(text: String) {
        naverLocalDs.saveKinKeyword(text)
    }


    override fun getBlogKeyword(): String =
        naverLocalDs.getBlogKeyword()


    override fun getMoiveKeyword(): String =
        naverLocalDs.getMovieKeyword()


    override fun getImageKeyword(): String =
        naverLocalDs.getImageKeyword()


    override fun getKinKeyword(): String =
        naverLocalDs.getKinKeyword()

}