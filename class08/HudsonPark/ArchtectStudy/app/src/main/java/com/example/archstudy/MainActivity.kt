package com.example.archstudy

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var edtQuery: EditText
    private lateinit var btnSearch: Button
    private lateinit var rvMovieList: RecyclerView
    private lateinit var rvMovieAdapter : MovieListAdapter
    private var movieData : List<Item> = emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView() // 뷰 초기화
        refreshRecycler(movieData) // 리사이클러 뷰 초기화 및 설정
        initEvent() // 이벤트 처리
    }

    private fun initEvent() {
        btnSearch.setOnClickListener {
            hideKeyboard()
            disableButton()
            // 검색 버튼 클릭 시
            var query = edtQuery.text.toString()
            if (query.isEmpty()) {
                showToast("검색어를 다시 입력해주세요.")
            } else {
                // 네이버 영화 정보 API 요청
                showToast("요청하신 관련 영화 : $query")
                requestMovieList(query)
                rvMovieList.adapter?.notifyDataSetChanged()
            }
            clearQuery()
            activateButton()
        }

    }

    private fun initView() {
        edtQuery = findViewById(R.id.edtQuery)
        btnSearch = findViewById(R.id.btnSearch)
        rvMovieList = findViewById(R.id.rvMovieList)
    }

    private fun refreshRecycler(data : List<Item>) {
        rvMovieAdapter = MovieListAdapter(data,object : MovieListAdapter.ItemClickListener {
            override fun onItemClick(url: String) {
                startActivity(Intent(Intent.ACTION_VIEW,Uri.parse(url)))
            }
        })
        rvMovieList.adapter = rvMovieAdapter
    }

    private fun requestMovieList(query: String) {

        val retrofit = Retrofit.Builder()
            .baseUrl(getString(R.string.api_address))
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(RetrofitService::class.java).apply {
            this.getMovieList(BuildConfig.API_CLIENT_ID, BuildConfig.API_CLIENT_SECRET, query)
                .enqueue(object : Callback<Movie> {

                    override fun onFailure(call: Call<Movie>, t: Throwable) {
                        showToast("통신 에러가 발생하였습니다 error : ${t.message}")
                    }

                    override fun onResponse(
                        call: Call<Movie>,
                        response: Response<Movie>
                    ) {

                        with(response) {

                            if (isSuccessful && body() != null) {
                                movieData = body()!!.items
                                refreshRecycler(movieData)

                            }
                        }

                    }
                })
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun activateButton() {
        btnSearch.isClickable = true
    }

    private fun disableButton() {
        btnSearch.isClickable = false
    }

    private fun clearQuery() {
        edtQuery.text.clear()
    }

    private fun hideKeyboard() {
        val inputManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(edtQuery.windowToken, 0)
    }
}
