package com.hong.data.source.remote

import com.hong.architecturestudy.data.source.remote.RemoteDataSource
import com.hong.data.model.MovieData
import com.hong.data.model.MovieResultData
import com.hong.data.network.RetrofitCreator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

class RemoteDataSourceImpl @Inject constructor(
    private val retrofitCreator: RetrofitCreator
) : RemoteDataSource {

    override fun getMovieList(
        query: String,
        onSuccess: (List<MovieData>) -> Unit,
        onFailure: (Throwable) -> Unit
    ) {
        val naverMovieApi = retrofitCreator.service
        naverMovieApi.getMovies(query).enqueue(object : Callback<MovieResultData> {
            override fun onFailure(call: Call<MovieResultData>, t: Throwable) {
                onFailure.invoke(t)
            }

            override fun onResponse(
                call: Call<MovieResultData>,
                response: Response<MovieResultData>
            ) {
                with(response) {
                    val body = body()
                    if (isSuccessful && body != null) {
                        onSuccess.invoke(body.items)
                    } else {
                        onFailure.invoke(Throwable())
                    }
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
    abstract fun bindRemoteDataSourceImpl(remoteDataSourceImpl: RemoteDataSourceImpl): RemoteDataSource
}
