package com.example.archstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var edtQuery: EditText
    private lateinit var btnSearch: Button
    private lateinit var rvMovieList: RecyclerView
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView() // 뷰 초기화
        initEvent() // 이벤트 처리
    }

    private fun initEvent() {
        btnSearch.setOnClickListener {
            // 검색 버튼 클릭 시
            var query = edtQuery.text.toString()
            if (query.isEmpty()) {
                Toast.makeText(this, "검색어를 다시 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                // 네이버 영화 정보 API 요청
                Toast.makeText(this, "검색하신 내용 : $query", Toast.LENGTH_SHORT).show()
                requestMovieList(query).let {
                    rvMovieList.adapter = MovieListAdapter(it) // 결과값 어댑터에 전달
                }
            }
        }
    }

    private fun initView() {
        edtQuery = findViewById(R.id.edtQuery)
        btnSearch = findViewById(R.id.btnSearch)
        rvMovieList = findViewById(R.id.rvMovieList)
    }

    private fun requestMovieList(query: String): List<Items> {

        val retrofit = Retrofit.Builder()
            .baseUrl(getString(R.string.api_address))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(RetrofitService::class.java)
        service.getMovieList(BuildConfig.API_CLIENT_ID, BuildConfig.API_CLIENT_SECRET, query)
            .enqueue(
                object : retrofit2.Callback<Movie> {

                    override fun onFailure(call: Call<Movie>, t: Throwable) {
                        Log.d(TAG, "onFailure() message : ${t.message}")
                    }

                    override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                        Toast.makeText(this@MainActivity, "영화 데이터 전달 성공", Toast.LENGTH_LONG).show()
                    }

                })

        return mutableListOf()
    }
}
