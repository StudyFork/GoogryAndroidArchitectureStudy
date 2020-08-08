package m.woong.architecturestudy.data.source.remote

import m.woong.architecturestudy.data.source.remote.model.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRemoteDataSourceImpl(val movieApi: MovieApi) : MovieRemoteDataSource {

    override fun getMovie(
        query: String,
        success: (MovieResponse) -> Unit,
        failure: (Throwable) -> Unit
    ) {
        movieApi.movieSearch(query)
            .enqueue(object : Callback<MovieResponse> {
                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    response.body()?.let {
                        success(it)
                    }
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    failure(t)
                }
            })
    }
}