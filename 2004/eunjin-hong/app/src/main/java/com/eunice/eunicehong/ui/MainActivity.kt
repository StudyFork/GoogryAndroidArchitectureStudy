package com.eunice.eunicehong.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import com.eunice.eunicehong.R
import com.eunice.eunicehong.data.remote.MovieAPI
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val movieListAdapter = MovieAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSearchBar()

        setMovieList()
    }

    private fun setSearchBar() {

        query_input.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchMovieList(v.text.toString().trim())
                true
            } else {
                false
            }
        }

        search_button.setOnClickListener {
            val query = query_input.text.toString().trim()
            if (query.isNotBlank()) {
                searchMovieList(query)
            }
        }
    }

    private fun searchMovieList(query: String) {
        MovieAPI.getMovieList(
            query = query,
            onSuccess = { movies ->
                zero_item_message.visibility =
                    if (movies.isNullOrEmpty()) View.VISIBLE else View.GONE
                movieListAdapter.setMovieList(movies)
            },
            onFailure = { e: Throwable ->
                Log.d(TAG, e.toString())
            })
    }

    private fun setMovieList() {
        move_list.adapter = movieListAdapter
    }

    companion object {
        private const val TAG = "MAIN_ACTIVITY"
    }

}
