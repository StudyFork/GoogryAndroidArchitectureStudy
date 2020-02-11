package com.example.studyapplication.data.repository

import com.example.studyapplication.data.datasource.local.NaverLocalDataSource
import com.example.studyapplication.data.datasource.remote.NaverRemoteDataSource
import com.example.studyapplication.data.model.*
import com.example.studyapplication.network.Conn

class NaverSearchRepositoryImpl(
    private val remoteDataSource: NaverRemoteDataSource,
    private val localDataSource: NaverLocalDataSource
) : NaverSearchRepository {

    override fun getMovieList(query: String, conn: Conn) {
        remoteDataSource.getMovieList(query, object : Conn {
            override fun <T> success(result: T) {
                val searchData: SearchResult<MovieInfo> = result as SearchResult<MovieInfo>
                searchData.let {
                    if (searchData.arrItem.size == 0) {
                        deleteMovieList()
                    } else {
                        saveMovieList(searchData.arrItem)
                        conn.success(searchData)
                    }
                }
            }

            override fun failed(e: Throwable) {
                deleteMovieList()
            }

        })
    }

    override fun getBlogList(query: String, conn: Conn) {
        remoteDataSource.getBlogList(query, object : Conn {
            override fun <T> success(result: T) {
                val searchData: SearchResult<BlogInfo> = result as SearchResult<BlogInfo>
                searchData.let {
                    if (searchData.arrItem.size == 0) {
                        deleteBlogList()
                    } else {
                        saveBlogList(searchData.arrItem)
                        conn.success(searchData)
                    }
                }
            }

            override fun failed(e: Throwable) {
                deleteBlogList()
            }
        })
    }

    override fun getImageList(query: String, conn: Conn) {
        remoteDataSource.getImageList(query, object : Conn {
            override fun <T> success(result: T) {
                val searchData: SearchResult<ImageInfo> = result as SearchResult<ImageInfo>
                searchData.let {
                    if (searchData.arrItem.size == 0) {
                        deleteImageList()
                    } else {
                        saveImageList(searchData.arrItem)
                        conn.success(searchData)
                    }
                }
            }

            override fun failed(e: Throwable) {
                deleteImageList()
            }
        })
    }

    override fun getKinList(title: String, conn: Conn) {
        remoteDataSource.getKinList(title, object : Conn {
            override fun <T> success(result: T) {
                val searchData: SearchResult<KinInfo> = result as SearchResult<KinInfo>
                searchData.let {
                    if (searchData.arrItem.size == 0) {
                        deleteImageList()
                    } else {
                        saveKinList(searchData.arrItem)
                        conn.success(searchData)
                    }
                }
            }

            override fun failed(e: Throwable) {
                deleteKinList()
            }
        })
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