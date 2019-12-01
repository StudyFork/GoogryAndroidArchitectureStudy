package wooooooak.com.studyapp.api

import retrofit2.http.GET
import retrofit2.http.Query
import wooooooak.com.studyapp.model.response.blog.BlogList
import wooooooak.com.studyapp.model.response.image.ImageList
import wooooooak.com.studyapp.model.response.kin.KinList
import wooooooak.com.studyapp.model.response.movie.MovieList

interface NaverRequestApi {

    @GET("movie.json")
    suspend fun getMovies(
        @Query("query") query: String,
        @Query("display") display: Int?,
        @Query("start") start: Int?
    ): MovieList

    @GET("blog.json")
    suspend fun getBlogs(
        @Query("query") query: String,
        @Query("display") display: Int?,
        @Query("start") start: Int?
    ): BlogList

    @GET("image.json")
    suspend fun getImages(
        @Query("query") query: String,
        @Query("display") display: Int?,
        @Query("start") start: Int?
    ): ImageList

    @GET("kin.json")
    suspend fun getKins(
        @Query("query") query: String,
        @Query("display") display: Int?,
        @Query("start") start: Int?
    ): KinList
}