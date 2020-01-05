package com.hansung.firstproject.ui.main

import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hansung.firstproject.R
import com.hansung.firstproject.adapter.RecyclerViewAdapter
import com.hansung.firstproject.data.MovieModel
import com.hansung.firstproject.data.MovieResponseModel
import com.hansung.firstproject.data.repository.NaverRepository
import com.hansung.firstproject.data.source.remote.NaverRemoteDataSourceImpl
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MainContract.View {

    private lateinit var presenter: MainContract.Presenter

    private lateinit var clientId: String // naver 검색API 사용을 위한 Client ID
    private lateinit var clientSecret: String //naver 검색API 사용을 위한 Client Secret
    private val adapter: RecyclerViewAdapter<MovieModel> = RecyclerViewAdapter()

    private val naverRepository by lazy {
        NaverRepository.getInstance(
            NaverRemoteDataSourceImpl.getInstance(
                getString(R.string.client_id),
                getString(R.string.client_secret)
            )
        )

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainPresenter(
            this,
            naverRepository
        )

        recycler_view_movies.adapter = adapter

        clientId = getString(R.string.client_id)
        clientSecret = getString(R.string.client_secret)

        // recyclerView 초기화
        initRecyclerView()

        btn_search.setOnClickListener {
            // 입력값이 없을 때
            presenter.doSearch(et_search.text.toString())

            /*
            if (et_search.text.isEmpty()) {

                return@setOnClickListener
            } else {
                // 입력값이 있을 때
                doSearch(et_search.text.toString())
                //키보드 제거
                removeKeyboard()
            }*/
        }
    }

    // recyclerView 초기화 메소드
    override fun initRecyclerView() {
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


    //키보드 제거 메소드
    override fun removeKeyboard() =
        (this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(
            et_search.windowToken,
            0
        )

    override fun showErrorKeywordEmpty() {
        Toast.makeText(this, getString(R.string.empty_keword_message), Toast.LENGTH_SHORT).show()
    }

    override fun showErrorInternetDisconnect() {
        Toast.makeText(this, getString(R.string.internet_error_message), Toast.LENGTH_SHORT).show()
    }

    override fun addItemToAdapter(response: MovieResponseModel) {
        adapter.run {
            addItems(response.items)
            notifyDataSetChanged()
        }
    }
}