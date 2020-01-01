package com.hansung.firstproject

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hansung.firstproject.adapter.RecyclerViewAdapter
import com.hansung.firstproject.data.MovieModel
import com.hansung.firstproject.data.repository.NaverRepository
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var clientId: String // naver 검색API 사용을 위한 Client ID
    private lateinit var clientSecret: String //naver 검색API 사용을 위한 Client Secret
    private val adapter: RecyclerViewAdapter<MovieModel> = RecyclerViewAdapter()
    private val naverRepository: NaverRepository =
        NaverRepository().getInstance() // Repository 생성

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("ahn", "MainActivity onCreate...")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recycler_view_movies.adapter = adapter

        clientId = getString(R.string.client_id)
        clientSecret = getString(R.string.client_secret)

        // recyclerView 초기화
        initRecyclerView()

        btn_search.setOnClickListener {
            // 입력값이 없을 때
            if (et_search.text.isEmpty()) {
                Log.d("ahn", "검색어 누락")
                Toast.makeText(this, "검색어를 입력해주세요", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else {
                // 입력값이 있을 때
                doSearch(et_search.text.toString())

                //키보드 제거
                removeKeyboard()
            }
        }
    }

    // recyclerView 초기화 메소드
    private fun initRecyclerView() {
        recycler_view_movies.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recycler_view_movies.setHasFixedSize(true)
        // movie 항목별 구분선 추가
        recycler_view_movies.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )
    }

    // 검색 메소드
    private fun doSearch(keyword: String) {
        naverRepository.getMoviesData(keyword, clientId, clientSecret,
            success = {
                adapter.addItems(it.items)
                adapter.notifyDataSetChanged()
            },
            fail = {
                Toast.makeText(applicationContext, "인터넷 연결을 확인하세요", Toast.LENGTH_SHORT)
                    .show()
                Log.e("ahn", "인터넷 연결 오류", it)
            })
    }

    //키보드 제거 메소드
    private fun removeKeyboard() =
        (this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
            et_search.windowToken,
            0
        )
}