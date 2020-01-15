package com.example.archstudy.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.archstudy.Item
import com.example.archstudy.MovieData
import com.example.archstudy.R
import com.example.archstudy.data.repository.NaverQueryRepositoryImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var edtQuery: EditText
    private lateinit var btnSearch: Button
    private lateinit var rvMovieList: RecyclerView
    private lateinit var rvMovieAdapter: MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView() // 뷰 초기화
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
                showToast("요청하신 관련 영화 : $query")
                requestRemoteData(query)
            }
            clearQuery()
            activateButton()
        }

    }

    private fun initView() {

        edtQuery = findViewById(R.id.edtQuery)
        btnSearch = findViewById(R.id.btnSearch)
        rvMovieList = findViewById(R.id.rvMovieList)
        rvMovieAdapter = MovieListAdapter(object :
            MovieListAdapter.ItemClickListener {
            override fun onItemClick(url: String) {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
            }
        })
        rvMovieList.adapter = rvMovieAdapter
    }

    private fun requestRemoteData(query: String) {
        val remoteData = NaverQueryRepositoryImpl().requestRemoteData(query)
        remoteData.enqueue(object : Callback<MovieData> {

            override fun onFailure(call: Call<MovieData>, t: Throwable) {
                showToast("에러 메시지 : ${t.message.toString()}")
            }

            override fun onResponse(call: Call<MovieData>, response: Response<MovieData>) {
                Log.d("movieData", response.body().toString())
                if (response.body() != null) {
                    rvMovieAdapter.setAllData(response.body()?.items as MutableList<Item>)
                }
            }
        })
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
