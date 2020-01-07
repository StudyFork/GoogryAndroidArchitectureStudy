package app.ch.study.data.remote.api

import app.ch.study.data.db.entitiy.MovieModel
import io.reactivex.Single
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

interface WebApi {

    @GET("/v1/search/movie.json")
    fun searchMovie(@Path("query") query: String?): Single<List<MovieModel>>

}