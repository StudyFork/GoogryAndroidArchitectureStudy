package com.deepco.data.data.remote

import com.deepco.data.data.api.RetrofitService
import com.deepco.data.data.model.Item
import com.deepco.data.data.model.MovieData
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

class RemoteMovieDataImpl @Inject constructor(
    private var retrofitService: RetrofitService
) : RemoteMovieData {
    override fun getMovieList(
        title: String,
        success: (List<Item>) -> Unit,
        failed: (String) -> Unit
    ) {
        retrofitService.getSearchMovie(title).enqueue(object : Callback<MovieData> {
            override fun onFailure(call: Call<MovieData>, t: Throwable) {
                failed(t.message.toString())
            }

            override fun onResponse(call: Call<MovieData>, response: Response<MovieData>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    body?.let {
                        if (it.items.isNotEmpty()) {
                            success(it.items)
                        } else {
                            failed("$title 를 찾을 수 없습니다")
                        }
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
abstract class RemoteMovieDataModule {

    @Binds
    @Singleton
    abstract fun bindRemoteMovieData(remoteMovieDataImpl: RemoteMovieDataImpl): RemoteMovieData
}