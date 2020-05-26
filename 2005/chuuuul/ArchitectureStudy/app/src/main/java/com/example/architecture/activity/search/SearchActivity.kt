package com.example.architecture.activity.search

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.StringRes
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
            searchPresenter.searchMovie(actionId, edt_search_keyword.text.toString())
        }

        btn_search_searchButton.setOnClickListener {
            searchPresenter.searchMovie(edt_search_keyword.text.toString())
        }
    }

    override fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }

    override fun showToast(@StringRes resId: Int) {
        Toast.makeText(this, getText(resId), Toast.LENGTH_SHORT).show()
    }

    override fun showMovieList(movieList: List<MovieModel>) {
        adapter.addNewItems(movieList)
    }


}
