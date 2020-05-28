package com.example.architecture.activity.search

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.architecture.R
import com.example.architecture.activity.search.adapter.MovieAdapter
import com.example.architecture.data.model.MovieModel
import com.example.architecture.data.repository.NaverRepositoryImpl
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity(R.layout.activity_search), SearchContract.View {

    private val searchPresenter by lazy {
        val naverRepository = NaverRepositoryImpl(applicationContext)
        SearchPresenter(this, naverRepository)
    }

    private val adapter = MovieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setRecyclerview()
        setViewEvent()
    }

    private fun setRecyclerview() {
        recyclerview_search_movieList.adapter = adapter
    }

    private fun setViewEvent() {
        // * Keyboard의 엔터로 검색 작업
        edt_search_keyword.setOnEditorActionListener { _, actionId, _ ->
            val keyword = edt_search_keyword.text.toString()

            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchPresenter.searchMovie(keyword)
                true
            } else {
                false
            }
        }

        btn_search_searchButton.setOnClickListener {
            val keyword = edt_search_keyword.text.toString()
            searchPresenter.searchMovie(keyword)
        }
    }

    override fun showMessageEmptyResult() {
        Toast.makeText(this, getString(R.string.not_found_result), Toast.LENGTH_SHORT).show()
    }

    override fun showMessageEmptyKeyword() {
        Toast.makeText(this, getString(R.string.empty_keyword), Toast.LENGTH_SHORT).show()
    }

    override fun showMovieList(movieList: List<MovieModel>) {
        adapter.movieAdapterPresenter.setMovieList(movieList)
    }


}
