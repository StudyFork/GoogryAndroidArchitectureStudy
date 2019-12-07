package wooooooak.com.studyapp.api

import retrofit2.http.GET
import retrofit2.http.Query
import wooooooak.com.studyapp.model.response.base.NaverQueryResponse
import wooooooak.com.studyapp.model.response.blog.Blog
import wooooooak.com.studyapp.model.response.image.Image
import wooooooak.com.studyapp.model.response.kin.Kin
import wooooooak.com.studyapp.model.response.movie.Movie

interface NaverRequestApi {

    @GET("v1/search/movie.json")
    suspend fun getMovies(
        @Query("query") query: String,
        @Query("display") display: Int?,
        @Query("start") start: Int?
    ): NaverQueryResponse<Movie>

    @GET("v1/search/blog.json")
    suspend fun getBlogs(
        @Query("query") query: String,
        @Query("display") display: Int?,
        @Query("start") start: Int?
    ): NaverQueryResponse<Blog>

    @GET("v1/search/image.json")
    suspend fun getImages(
        @Query("query") query: String,
        @Query("display") display: Int?,
        @Query("start") start: Int?
    ): NaverQueryResponse<Image>

    @GET("v1/search/kin.json")
    suspend fun getKins(
        @Query("query") query: String,
        @Query("display") display: Int?,
        @Query("start") start: Int?
    ): NaverQueryResponse<Kin>
}