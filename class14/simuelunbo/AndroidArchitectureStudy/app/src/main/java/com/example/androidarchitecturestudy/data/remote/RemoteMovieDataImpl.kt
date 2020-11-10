package com.example.androidarchitecturestudy.data.remote

import com.example.androidarchitecturestudy.data.api.NaverMovieInterface
import com.example.androidarchitecturestudy.data.local.LocalMovieDataImpl
import com.example.androidarchitecturestudy.data.model.MovieData
import com.example.androidarchitecturestudy.data.repository.RepositoryMovieImpl
import retrofit2.Call
import retrofit2.Response

class RemoteMovieDataImpl : RemoteMovieData {
    private val localMovieDataImpl = LocalMovieDataImpl()
    private val repositoryMovieImpl = RepositoryMovieImpl(this,localMovieDataImpl)

    override fun getSearchMovieList(
        query: String,
        success: (MovieData) -> Unit,
        failed: (String) -> Unit
    ) {
        NaverMovieInterface.create().searchMovies(query).enqueue(object :
            retrofit2.Callback<MovieData> {
            override fun onResponse(call: Call<MovieData>, response: Response<MovieData>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        success(it)
                        it.items?.let { movie -> repositoryMovieImpl.saveMovieData(movie) }
                    }
                } else {
                    failed(response.message())
                }
            }

            override fun onFailure(call: Call<MovieData>, t: Throwable) {
                failed("다시 검색해 주세요")
            }

        })
    }
}