package com.example.studyapplication.data.repository

import com.example.studyapplication.data.datasource.local.NaverLocalDataSource
import com.example.studyapplication.data.datasource.remote.NaverRemoteDataSource
import com.example.studyapplication.data.model.BlogInfo
import com.example.studyapplication.data.model.ImageInfo
import com.example.studyapplication.data.model.KinInfo
import com.example.studyapplication.data.model.MovieInfo
import com.example.studyapplication.network.Conn

class NaverSearchRepositoryImpl(
    private val remoteDataSource: NaverRemoteDataSource,
    private val localDataSource: NaverLocalDataSource
) : NaverSearchRepository {

    override fun getMovieList(query: String, conn: Conn) {
        remoteDataSource.getMovieList(query, conn)
    }

    override fun getBlogList(query: String, conn: Conn) {
        remoteDataSource.getBlogList(query, conn)
    }

    override fun getImageList(query: String, conn: Conn) {
        remoteDataSource.getImageList(query, conn)
    }

    override fun getKinList(title: String, conn: Conn) {
        remoteDataSource.getKinList(title, conn)
    }

    override fun saveMovieList(arrItem: ArrayList<MovieInfo>) {
        localDataSource.saveMovieList(arrItem)
    }

    override fun saveBlogList(arrItem: ArrayList<BlogInfo>) {
        localDataSource.saveBlogList(arrItem)
    }

    override fun saveImageList(arrItem: ArrayList<ImageInfo>) {
        localDataSource.saveImageList(arrItem)
    }

    override fun saveKinList(arrItem: ArrayList<KinInfo>) {
        localDataSource.saveKinList(arrItem)
    }

    override fun deleteMovieList() {
        localDataSource.deleteMovieList()
    }

    override fun deleteBlogList() {
        localDataSource.deleteBlogList()
    }

    override fun deleteImageList() {
        localDataSource.deleteImageList()
    }

    override fun deleteKinList() {
        localDataSource.deleteKinList()
    }

    override fun getCacheMovieList(
        success: (ArrayList<MovieInfo>) -> Unit,
        failed: (Throwable) -> Unit
    ) {
        localDataSource.getCacheMovieList(success, failed)
    }

    override fun getCacheBlogList(
        success: (ArrayList<BlogInfo>) -> Unit,
        failed: (Throwable) -> Unit
    ) {
        localDataSource.getCacheBlogList(success, failed)
    }

    override fun getCacheImageList(
        success: (ArrayList<ImageInfo>) -> Unit,
        failed: (Throwable) -> Unit
    ) {
        localDataSource.getCacheImageList(success, failed)
    }

    override fun getCacheKinList(
        success: (ArrayList<KinInfo>) -> Unit,
        failed: (Throwable) -> Unit
    ) {
        localDataSource.getCacheKinList(success, failed)
    }

}