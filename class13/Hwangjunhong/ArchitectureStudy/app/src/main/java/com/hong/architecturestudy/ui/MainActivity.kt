package com.hong.architecturestudy.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hong.architecturestudy.R
import com.hong.architecturestudy.data.MovieResultData
import com.hong.architecturestudy.ext.hideKeyboard
import com.hong.architecturestudy.network.RetrofitCreator
import com.hong.architecturestudy.utils.log
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val adapter = MovieAdapter()
    private val movieCallBack = object : retrofit2.Callback<MovieResultData> {
        override fun onFailure(call: Call<MovieResultData>, t: Throwable) {
            log("[MainActivity] : 통신 실패")
        }

        override fun onResponse(call: Call<MovieResultData>, response: Response<MovieResultData>) {
            with(response) {
                val body = body()
                if (isSuccessful && body != null) {
                    adapter.addItems(body.items)
                } else {
                    log("[MainActivity] : 데이터 불러오기 실패")
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setRecyclerView()
        btn_search.setOnClickListener {
            if (edit_search.text.toString().isBlank()) {
                Toast.makeText(this, "영화 제목을 입력해 주세요", Toast.LENGTH_LONG).show()
            } else {
                getMovieList(edit_search.text.toString())
                hideKeyboard(this, edit_search)
            }
        }
    }

    private fun setRecyclerView() {
        rv_movies_list.adapter = adapter
        rv_movies_list.setHasFixedSize(true)
        adapter.onClick = { movieData ->
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(movieData.link))
            startActivity(intent)
        }
    }

    private fun getMovieList(query: String) {
        RetrofitCreator.service.getMovies(query).enqueue(movieCallBack)
    }
}