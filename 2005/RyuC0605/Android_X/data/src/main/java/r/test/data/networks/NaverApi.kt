package r.test.data.networks

import r.test.data.BuildConfig
import r.test.data.model.MovieVo
import retrofit2.Call
import retrofit2.http.*

interface NaverApi {
    /**
     * 네이버 영화 검색 API
     */
    @GET("/v1/search/movie.json?display=100")
    @Headers(
        "X-Naver-Client-Id: ${BuildConfig.X_NAVER_CLIENT_ID}",
        "X-Naver-Client-Secret: ${BuildConfig.X_NAVER_CLIENT_SECRET}"
    )
    fun searchMovie(@Query("query") query: String): Call<MovieVo>
}