package com.example.androidarchitecturestudy

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.androidarchitecturestudy.Retrofit.RetrofitClient
import com.example.androidarchitecturestudy.Retrofit.ServerIp
import com.example.androidarchitecturestudy.data.GetMovieInfo
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var retrofitClient: RetrofitClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        searchMovie()
    }


    private fun searchMovie() {
        btn_main_search_movie.setOnClickListener {

            //영화 검색 실행
            getMovieData(edit_main_search_movie.text.toString())
        }
    }//searchMovie


    // TODO: 2020/11/01 추후  코루틴 또는 Rx로 변경할
    private fun getMovieData(searchQuery: String) {
        retrofitClient = RetrofitClient(ServerIp.naverMovieApiUrl)
        retrofitClient.apiService.getMovieSearchResult(searchQuery)
            .enqueue(object : Callback<GetMovieInfo.MovieList> {

                override fun onResponse(
                    call: Call<GetMovieInfo.MovieList>,
                    response: Response<GetMovieInfo.MovieList>
                ) {

                    //응답 성공
                    if (response.isSuccessful) {
                        val movieList = response.body()?.movieList

                        Log.v("check_log", movieList.toString())

                    } else {//응답 실패
                        Log.v("check_log", response.code().toString())
                        Log.v("check_log", response.errorBody()?.string().toString())
                    }
                }


                override fun onFailure(call: Call<GetMovieInfo.MovieList>, t: Throwable) {
                    Log.v("check_log", t.message.toString())
                }

            })
    }//getMovieData 끝

}