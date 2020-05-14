package com.example.architecture.activity.search

import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.architecture.R
import com.example.architecture.data.model.MovieModel
import com.example.architecture.data.repository.NaverRepositoryImpl
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity() {

    private val movieList = mutableListOf<MovieModel>()
    private val adapter = MovieListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        setRecyclerview()
        setViewEvent()
    }


    private fun setViewEvent() {
        btn_search_searchButton.setOnClickListener {
            searchMovie()
        }

        // * 키보드 엔터 클릭 시 검색
        edt_search_searchName.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                btn_search_searchButton.callOnClick()
                true
            } else
                false

        }
    }

    // * 검색
    private fun searchMovie() {
        val keyWord = edt_search_searchName.text.toString()

        if (isValidKeyword(keyWord)) {
            NaverRepositoryImpl.getMovieList(
                applicationContext,
                keyWord,
                onSuccess = this::onSuccess,
                onFailure = this::onFailure
            )
        }
    }


    private fun isValidKeyword(keyword: String): Boolean {

        if (keyword.isBlank()) {
            Toast.makeText(this, getString(R.string.empty_keyword), Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    // * RecyclerView

    private fun setRecyclerview() {
        adapter.addNewItems(movieList)
        recyclerview_search_movieList.adapter = adapter
    }

    // * Retrofit Response
    private fun onSuccess(movieList: List<MovieModel>) {
        if (movieList.isNotEmpty()) {
            adapter.addNewItems(movieList)
        } else {
            Toast.makeText(this, R.string.not_found_result, Toast.LENGTH_SHORT).show()
        }
    }

    private fun onFailure(t: Throwable) {
        Log.d("chul", "OnFailure : $t")
    }

}
