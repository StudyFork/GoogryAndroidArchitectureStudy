package m.woong.architecturestudy.data.source.remote

import m.woong.architecturestudy.data.source.remote.model.MovieResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MovieApi {

    @Headers(
        "X-Naver-Client-Id: $CLIENT_ID",
        "X-Naver-Client-Secret: $CLIENT_SECRET"
    )

    @GET("v1/search/movie.json")
    fun movieSearch(
        @Query("query") query: String
    ): Call<MovieResponse>

    object MovieRetrofit {
        private val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://openapi.naver.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service: MovieApi = retrofit.create(MovieApi::class.java)
    }

    companion object {
        const val CLIENT_ID = "lHrslPhzC2bFPf8LZ4kB"
        const val CLIENT_SECRET = "0vJ3mpzX6_"
    }
}