package com.eunice.eunicehong.ui

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eunice.eunicehong.R
import com.eunice.eunicehong.data.remote.Movie
import com.eunice.eunicehong.data.remote.MovieAPI

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
        val searchEditText = findViewById<EditText>(R.id.query_input)
        val searchButton = findViewById<ImageButton>(R.id.search_button)

        searchEditText.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH
                && event.keyCode == KeyEvent.KEYCODE_ENTER
            ) {
                searchMovieList(v.text.toString().trim())
                true
            } else false
        }

        searchButton.setOnClickListener {
            val query = searchEditText.text.toString().trim()
            if (!query.isBlank()) {
                searchMovieList(query)
            }
        }
    }

    private fun searchMovieList(query: String) {
        MovieAPI().getMovieList(query,
            { movies ->
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
        val movieListView = findViewById<RecyclerView>(R.id.move_list)
        movieListAdapter = MovieAdapter(movieList)
        movieListView.adapter = movieListAdapter
        movieListView.layoutManager = LinearLayoutManager(this@MainActivity)
    }

    companion object {
        private const val TAG = "MAIN_ACTIVITY"
    }

}
