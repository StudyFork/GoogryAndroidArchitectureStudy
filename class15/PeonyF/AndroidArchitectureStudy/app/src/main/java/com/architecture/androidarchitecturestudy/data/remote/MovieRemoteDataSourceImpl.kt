package com.architecture.androidarchitecturestudy.data.remote

import com.architecture.androidarchitecturestudy.data.model.MovieResponse
import com.architecture.androidarchitecturestudy.webservice.ApiClient
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

class MovieRemoteDataSourceImpl(
    private val network: NetworkService = ApiClient.NETWORK_SERVICE
) : MovieRemoteDataSource {
    override fun getMovieData(
        keyword: String,
        display: Int,
        onSuccess: (MovieResponse) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        network.getMovieSearch(keyword, display)
            .enqueue(object : Callback<MovieResponse> {
                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    onFailure(t)
                }

                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    val body = response.body()
                    body?.let {
                        onSuccess(it)
                    }
                }
            })
    }
}

@Module
@InstallIn(ApplicationComponent::class)
abstract class RemoteDataSourceModule {
    @Binds
    @Singleton
    abstract fun bindRemoteDataSource(remoteDataSourceImpl: MovieRemoteDataSourceImpl): MovieRemoteDataSource
}