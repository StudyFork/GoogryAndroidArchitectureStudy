package com.hjhan.hyejeong.data.source.remote

import com.hjhan.hyejeong.data.model.MovieData
import com.hjhan.hyejeong.data.network.NaverApi
import com.hjhan.hyejeong.data.network.ServiceClient
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

class NaverRemoteDataSourceImpl @Inject constructor(
    private val serviceClient: ServiceClient
) : NaverRemoteDataSource {

    override fun getSearchMovies(
        query: String,
        success: (MovieData) -> Unit,
        failed: (String) -> Unit
    ) {
        val api = serviceClient.createService(NaverApi::class.java)
        api.getMovies(query, 10, 100).enqueue(object : Callback<MovieData> {
            override fun onResponse(call: Call<MovieData>, response: Response<MovieData>) {

                if (response.isSuccessful) {
                    response.body()?.let {
                        success(it)
                    }
                } else {
                    failed("네트워크 통신 실패")
                }

            }

            override fun onFailure(call: Call<MovieData>, t: Throwable) {
                failed(t.message ?: "알 수 없는 에러")
            }
        })
    }
}


@Module
@InstallIn(ApplicationComponent::class)
abstract class RemoteDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindRemoteDataSource(remoteDataSourceImpl: NaverRemoteDataSourceImpl): NaverRemoteDataSource
}