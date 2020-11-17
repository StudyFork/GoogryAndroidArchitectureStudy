package com.example.hw2_project.data.remote

import com.example.hw2_project.data.api.NaverMovieApi
import com.example.hw2_project.data.api.NaverMovieData
import okhttp3.*
import java.io.IOException
import java.net.URL
import java.net.URLEncoder

class MovieRemoteDataSourceImpl : MovieRemoteDataSource {
    companion object{
        //Naver 영화 검색 API 사용을 위한 App 등록
        private const val CLIENT_ID = "fQFY7M9rMOVD2KDT8Aaq"
        private const val CLIENT_SECRET = "v8aD8p_Ri0"
    }

    override fun getMovieFromRemote(
        query: String,
        success: (NaverMovieData.NaverMovieResponse) -> Unit,
        fail: (Throwable) -> Unit
    ) {
        //OkHttp로 요청
        val text = URLEncoder.encode(query, "UTF-8")

            override fun onFailure(call: Call<NaverMovieData.NaverMovieResponse>, t: Throwable) {
                fail(t)
        val request = Request.Builder()
            .url(url)
            .addHeader("X-Naver-Client-Id", CLIENT_ID)
            .addHeader("X-Naver-Client-Secret", CLIENT_SECRET)
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