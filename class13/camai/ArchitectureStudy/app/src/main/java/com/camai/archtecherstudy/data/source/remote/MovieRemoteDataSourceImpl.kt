package com.camai.archtecherstudy.data.source.remote

import android.util.Log
import com.camai.archtecherstudy.data.model.Items
import com.camai.archtecherstudy.data.model.MovieResponseModel
import com.camai.archtecherstudy.data.network.MovieApiServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

class MovieRemoteDataSourceImpl @Inject constructor(
    private val movieApi: MovieApiServiceImpl
) :
    MovieRemoteDataSource {

    private val TAG = "MovieRemoteDataSource"

    override fun getSearchMovie(
        keyword: String,
        display: Int,
        start: Int,
        success: (ArrayList<Items>) -> Unit,
        failed: (String) -> Unit
    ) {
        val request = movieApi.service
        val call = request.getMovieSearch(keyword, display, start)
        call.enqueue(object :
            retrofit2.Callback<MovieResponseModel> {

            override fun onResponse(
                call: Call<MovieResponseModel>,
                response: Response<MovieResponseModel>
            ) {
                // Success
                if (response.isSuccessful) {

                    val body = response.body()
                    body?.let {
                        success(it.items)
                    }

                } else {
                    Log.e(TAG, response.message())
                    failed(response.message())
                }

            }

            override fun onFailure(call: Call<MovieResponseModel>, t: Throwable) {
                // Failed
                Log.e(TAG, t.message.toString())
                failed(t.message.toString())

            }
        })
    }
}

@Module
@InstallIn(ApplicationComponent::class)
abstract class RemoteDataSourceModule {
    @Binds
    @Singleton
    abstract fun bindRemoteRepository(movieRemoteDataSourceImpl: MovieRemoteDataSourceImpl): MovieRemoteDataSource
}