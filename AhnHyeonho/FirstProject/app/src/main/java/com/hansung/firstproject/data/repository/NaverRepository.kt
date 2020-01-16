package com.hansung.firstproject.data.repository
import com.hansung.firstproject.data.MovieResponseModel
import com.hansung.firstproject.data.source.remote.NaverRemoteDataSource

class NaverRepository private constructor(
    private val dataSource: NaverRemoteDataSource
) {

    fun getMoviesData(
        title: String,
        success: (MovieResponseModel) -> Unit,
        fail: (Throwable) -> Unit,
        isEmptyList: () -> Unit
    ) {
        dataSource.getMoviesData(title, success, fail, isEmptyList)
    }

    companion object {
        @Volatile
        private var _INSTANCE: NaverRepository? = null

        @JvmStatic
        fun getInstance(dataSource: NaverRemoteDataSource): NaverRepository =
            _INSTANCE ?: synchronized(this) {
                _INSTANCE ?: NaverRepository(dataSource).also {
                    _INSTANCE = it
                }
            }
    }
}