package mi.song.class12android.network

import mi.song.class12android.model.data.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("movie.json")
    fun getMovieInfo(@Query("query") query: String): Call<MovieResponse>
}