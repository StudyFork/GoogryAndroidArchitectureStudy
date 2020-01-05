package com.siwon.prj.datasource

import com.siwon.prj.datasource.service.MovieSearchService
import com.siwon.prj.model.ApiInfo
import com.siwon.prj.model.Movie
import com.siwon.prj.model.Movies
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO MovieSearchServiceImpl.service를 object 형태로 직접 접근이 아닌 주입 형태로 생성될때 인자로 받아보도록 수정해보시면 추후 아키텍처를 구성하실때 도움이 될꺼 같습니다.
class RemoteMovieSearchDataSourceImpl(private val service: MovieSearchService) : RemoteMovieSearchDataSource {

    override fun searchMovies(
        query: String,
        success: (ArrayList<Movie>) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        service.search(ApiInfo.CLIENT_ID, ApiInfo.CLIENT_SECRET, query)
            .enqueue(object : Callback<Movies> {
                override fun onFailure(call: Call<Movies>, t: Throwable) {
                    fail(t)
                }

                override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                    success(response.body()!!.movies)
                }
            })
    }
}
