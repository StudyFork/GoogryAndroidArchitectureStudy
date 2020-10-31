package com.example.myproject.data.source.remote

import com.example.myproject.data.model.Items
import com.example.myproject.data.model.Movie
import com.example.myproject.data.service.RetrofitClient
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

class NaverRemoteDataSourceImpl @Inject constructor(private val retrofitClient: RetrofitClient) : NaverRemoteDataSource {
    override fun getMovieList(
        title: String,
        success: (ArrayList<Items>) -> Unit,
        failed: (String) -> Unit
    ) {
        retrofitClient.getService().getMovies(title).enqueue(object : Callback<Movie> {
            override fun onFailure(call: Call<Movie>, t: Throwable) {
                failed(t.message.toString())
            }

            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let {
                        success(it.items)
                    }
                } else {
                    failed(response.message())
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
    abstract fun bindRemoteDataSource(remoteDataSourceImpl: NaverRemoteDataSourceImpl): NaverRemoteDataSource
}
