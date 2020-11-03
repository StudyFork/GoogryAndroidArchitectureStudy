package com.example.androidarchitecturestudy

import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.androidarchitecturestudy.Retrofit.RetrofitClient
import com.example.androidarchitecturestudy.Retrofit.ServerIp
import com.example.androidarchitecturestudy.adapter.MovieListRecyclerViewAdapter
import com.example.androidarchitecturestudy.data.GetMovieInfo
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {


    private lateinit var retrofitClient: RetrofitClient

    // 리사이클러뷰 어뎁터
    private lateinit var recyclerViewAdapter: MovieListRecyclerViewAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initSet()
        setRecyclerView()
        searchMovie()

    }


    // 초기 설정들 세팅
    private fun initSet(){
        retrofitClient = RetrofitClient(ServerIp.naverMovieApiUrl)
    }


    // 리사이클러뷰 세팅
    private fun setRecyclerView() {

        recyclerViewAdapter = MovieListRecyclerViewAdapter(this)


        recycler_view_main_movie_list.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)//그리드 형태로
            adapter = recyclerViewAdapter
        }

    }// setRecyclerView

    // 리사이클러뷰 데이터 업데이트
    private fun updateRecyclerView(movieList: ArrayList<GetMovieInfo.MovieData>) {
        recyclerViewAdapter.getMovieData(movieList)
    }


    // 영화 검색 실행
    private fun searchMovie() {


        // 검색 버튼 눌릴때
        btn_main_search_movie.setOnClickListener {

            // 영화 검색 실행
            getMovieData(edit_main_search_movie.text.toString())
        }


        // edittext search action 눌릴때
        edit_main_search_movie.setOnEditorActionListener { _, actionId, _ ->

            if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                //영화 검색 실행
                getMovieData(edit_main_search_movie.text.toString())
            }
            true
        }

    }// searchMovie


    // 서버에  영화 데이터 받아옴
    // TODO: 2020/11/01 추후  코루틴 또는 Rx로 변경하기
    private fun getMovieData(searchQuery: String) {
        retrofitClient.apiService.getMovieSearchResult(searchQuery)
            .enqueue(object : Callback<GetMovieInfo.MovieList> {

                override fun onResponse(
                    call: Call<GetMovieInfo.MovieList>,
                    response: Response<GetMovieInfo.MovieList>
                ) {

                    // 응답 성공
                    if (response.isSuccessful) {
                        val movieList = response.body()?.movieList

                        if (movieList != null) {
                            updateRecyclerView(movieList = movieList)
                        }

                    } else {// 응답 실패
                        Log.v("check_log", response.code().toString())
                        Log.v("check_log", response.errorBody()?.string().toString())
                    }

                }


                override fun onFailure(call: Call<GetMovieInfo.MovieList>, t: Throwable) {
                    Log.e("check_log", t.message.toString())

                }

            })
    }// getMovieData 끝

}