package com.example.kyudong3

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.kyudong3.adapter.SearchMovieRvAdapter
import com.example.kyudong3.data.repository.MovieRepository
import com.example.kyudong3.data.repository.MovieRepositoryImpl
import com.example.kyudong3.extension.toast
import com.example.kyudong3.util.RecyclerViewItemDivider
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val movieRepository: MovieRepository by lazy {
        MovieRepositoryImpl()
    }

    private val movieRvAdapter: SearchMovieRvAdapter by lazy {
        SearchMovieRvAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setMovieRecyclerView()

        searchETxt.setOnEditorActionListener { _, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_UNSPECIFIED, EditorInfo.IME_ACTION_GO -> {
                    searchMovie()
                    true
                }
                else -> {
                    false
                }
            }
        }

        searchBtn.setOnClickListener {
            searchMovie()
        }
    }

    private fun setMovieRecyclerView() {
        searchRv.apply {
            adapter = movieRvAdapter
            addItemDecoration(RecyclerViewItemDivider(this@MainActivity))
        }
    }

    private fun searchMovie() {
        if (searchETxt.text.trim().isEmpty()) {
            Toast.makeText(applicationContext, "검색어를 1자 이상 입력해주세요!", Toast.LENGTH_SHORT)
                .show()
        } else {
            fetchSearchMovieApi(searchETxt.text.trim().toString())
        }
    }

    private fun fetchSearchMovieApi(searchQuery: String) {
        movieRepository.getSearchMovie(searchQuery,
            success = { movieList ->
                if (movieList.isEmpty()) {
                    this.toast("검색결과가 없습니다")
                } else {
                    movieRvAdapter.setMovieList(movieList)
                    movieRvAdapter.notifyDataSetChanged()
                }
            },
            failure = { error ->
                error.printStackTrace()
            })
    }
}
