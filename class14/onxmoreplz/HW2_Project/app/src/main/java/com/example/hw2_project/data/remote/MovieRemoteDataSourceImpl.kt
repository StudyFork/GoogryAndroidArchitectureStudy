package com.example.hw2_project.data.remote

import com.example.hw2_project.data.MovieList
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException
import java.net.URL
import java.net.URLEncoder

class MovieRemoteDataSourceImpl : MovieRemoteDataSource {

    //Naver 영화 검색 API 사용을 위한 App 등록
    private val clienId = "fQFY7M9rMOVD2KDT8Aaq"
    private val clientSecret = "v8aD8p_Ri0"

    // getMovie 재정의
    override fun getMovieFromRemote(
        query: String,
        success: (MovieList) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        //OkHttp로 요청
        val text = URLEncoder.encode(query, "UTF-8")

        val url = URL("https://openapi.naver.com/v1/search/movie.json?query=${text}&display=10&start=1&genre=")

        val request = Request.Builder()
            .url(url)
            .addHeader("X-Naver-Client-Id", clienId)
            .addHeader("X-Naver-Client-Secret", clientSecret)
            .method("GET", null)
            .build()

        //요청을 위한 Client 객체 생성
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                fail(e)
            }
            override fun onResponse(call: Call, response: Response) {
                val body = response.body()?.string()

                //Gson 라이브러리 사용
                val gson = GsonBuilder().create()
                val moviefeed = gson.fromJson<MovieList>(body, MovieList::class.java)

                if(response.isSuccessful){
                    success(moviefeed)
                }
            }
        })

    }
}