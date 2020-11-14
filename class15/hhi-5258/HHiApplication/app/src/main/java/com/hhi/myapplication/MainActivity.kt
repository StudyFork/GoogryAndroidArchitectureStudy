package com.hhi.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hhi.myapplication.data.model.MovieData
import com.hhi.myapplication.data.repository.NaverRepositoryDataSourceImpl
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainContract.View {
    private val recyclerAdapter = RecyclerAdapter()
    private val mainPresenter = MainPresenter(this, NaverRepositoryDataSourceImpl())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpUI()
        setUpListener()
    }

    private fun setUpUI() {
        main_recyclerview.setHasFixedSize(false)
        main_recyclerview.adapter = recyclerAdapter
    }

    private fun setUpListener() {
        main_btn_search.setOnClickListener {
            val searchText = main_edit_search.text.toString()
            mainPresenter.searchMovie(searchText)
        }
    }

    override fun showMovies(items: ArrayList<MovieData.MovieItem>) {
        recyclerAdapter.setMovieList(items)
    }

    override fun showEmptyQuery() {
        Toast.makeText(this, "내용을 입력해 주세요.", Toast.LENGTH_SHORT).show()
    }
}