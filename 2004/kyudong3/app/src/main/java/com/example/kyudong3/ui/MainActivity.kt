package com.example.kyudong3.ui

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.kyudong3.R
import com.example.kyudong3.adapter.SearchMovieRvAdapter
import com.example.kyudong3.data.local.MovieDatabase
import com.example.kyudong3.data.model.Movie
import com.example.kyudong3.data.repository.MovieRepository
import com.example.kyudong3.data.repository.MovieRepositoryImpl
import com.example.kyudong3.databinding.ActivityMainBinding
import com.example.kyudong3.extension.toast
import com.example.kyudong3.mapper.MovieLocalMapper
import com.example.kyudong3.mapper.MovieRemoteMapper
import com.example.kyudong3.ui.movie.presenter.MovieContract
import com.example.kyudong3.ui.movie.presenter.MoviePresenter
import com.example.kyudong3.util.RecyclerViewItemDivider

class MainActivity : AppCompatActivity(), MovieContract.View {
    private val movieRvAdapter: SearchMovieRvAdapter by lazy {
        SearchMovieRvAdapter()
    }

    private val movieDatabase: MovieDatabase by lazy {
        MovieDatabase.getInstance(applicationContext)
    }

    private val movieRepository: MovieRepository by lazy {
        MovieRepositoryImpl(movieDatabase.movieDao(), MovieRemoteMapper(), MovieLocalMapper())
    }

    private val moviePresenter: MovieContract.Presenter by lazy {
        MoviePresenter(this@MainActivity, movieRepository)
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        setMovieRecyclerView()

        binding.searchQuery.setOnEditorActionListener { _, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_UNSPECIFIED, EditorInfo.IME_ACTION_GO -> {
                    moviePresenter.searchMovie(binding.searchQuery.text.trim().toString())
                    true
                }
                else -> {
                    false
                }
            }
        }
    }

    fun searchBtnClick(query: String) {
        moviePresenter.searchMovie(query)
    }

    private fun setMovieRecyclerView() {
        binding.searchRv.apply {
            adapter = movieRvAdapter
            addItemDecoration(RecyclerViewItemDivider(this@MainActivity))
        }
    }

    override fun showInvalidQuerySearch() {
        toast("검색어를 1자 이상 입력해주세요!")
    }

    override fun showEmptySearchResult() {
        toast("검색결과가 없습니다")
    }

    override fun setMovieData(movieList: List<Movie>) {
        movieRvAdapter.setMovieList(movieList)
        movieRvAdapter.notifyDataSetChanged()
    }

    override fun showNetworkError(error: Throwable) {
        toast("네트워크 오류가 발생했습니다")
    }

    override fun showCachedMovieData(movieList: List<Movie>) {
        movieRvAdapter.setMovieList(movieList)
        movieRvAdapter.notifyDataSetChanged()
    }
}
