package com.olaf.nukeolaf

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var retrofitClient: RetrofitInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        retrofitClient = RetrofitClient.client

        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return if (isEmptyText(query)) {
                    // 검색어가 공백인 경우
                    showEmptyTextDialog()
                    true
                } else {
                    // 검색어가 있는 경우
                    searchMovie(query!!)
                    true
                }
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    private fun searchMovie(query: String) {
        retrofitClient.searchMovie(
            getString(R.string.naver_client_id),
            getString(R.string.naver_client_secret),
            query, 10, 1
        ).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(
                call: Call<MovieResponse>,
                response: Response<MovieResponse>
            ) {
                Log.d("onQueryTextListener", response.toString())
                Log.d("onQueryTextListener", response.body().toString())
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.d("onQueryTextListener", t.toString())
            }
        })
    }

    // inputString 공백 확인 메서드
    private fun isEmptyText(text: String?): Boolean {
        return TextUtils.isEmpty(text)
    }

    private fun showEmptyTextDialog() {
        AlertDialog.Builder(this).apply {
            setTitle("알림")
                .setMessage("검색어를 입력해주세요")
                .show()
        }
    }
}
