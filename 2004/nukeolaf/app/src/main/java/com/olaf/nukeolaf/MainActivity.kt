package com.olaf.nukeolaf

import android.os.Bundle
import android.text.TextUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import kotlinx.android.synthetic.main.activity_main.*

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
                    true
                } else {
                    // 검색어가 있는 경우
                    var map = HashMap<String, Any>()
                    map["query"] = query!!
                    map["display"] = 100
                    retrofitClient.searchMovie(
                        getString(R.string.naver_client_id),
                        getString(R.string.naver_client_secret),
                        map
                    )
                    true
                }
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }

    // inputString 공백 확인 메서드
    private fun isEmptyText(text: String?): Boolean {
        return TextUtils.isEmpty(text)
    }
}
