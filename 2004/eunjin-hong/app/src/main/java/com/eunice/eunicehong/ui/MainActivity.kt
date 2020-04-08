package com.eunice.eunicehong.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import com.eunice.eunicehong.R
import com.eunice.eunicehong.data.remote.Movie
import com.eunice.eunicehong.data.remote.MovieAPI
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val movieList = arrayListOf<Movie>()
    private lateinit var movieListAdapter: MovieAdapter

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
            if (!query.isBlank()) {
                searchMovieList(query)
            }
        }
    }

    private fun searchMovieList(query: String) {
        MovieAPI().getMovieList(query,
            { movies ->
                zero_item_message.visibility =
                    if (movies.items.isNullOrEmpty()) View.VISIBLE else View.GONE

                movieList.let {
                    it.clear()
                    it.addAll(movies.items)
                }.also {
                    movieListAdapter.notifyDataSetChanged()
                }

            }, { e: Throwable ->
                Log.d(TAG, e.toString())
            })
    }

    private fun setMovieList() {
        movieListAdapter = MovieAdapter(movieList)
        move_list.adapter = movieListAdapter
    }

    companion object {
        private const val TAG = "MAIN_ACTIVITY"
    }

}
