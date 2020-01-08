package com.example.studyapplication.data.repository

import com.example.studyapplication.data.datasource.local.NaverLocalDataSource
import com.example.studyapplication.data.datasource.remote.NaverRemoteDataSource
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

    override fun saveBlogList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveImageList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveKinList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteMovieList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteBlogList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteImageList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteKinList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getCacheMovieList(
        success: (ArrayList<MovieInfo>) -> Unit,
        failed: (Throwable) -> Unit
    ) {
        localDataSource.getCacheMovieList(success, failed)
    }

}