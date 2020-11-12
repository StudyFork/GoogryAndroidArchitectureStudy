package com.jay.aas.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.jay.aas.R
import com.jay.aas.api.RetrofitHelper
import com.jay.aas.data.MovieLocalDataSourceImpl
import com.jay.aas.data.MovieRemoteDataSourceImpl
import com.jay.aas.data.MovieRepository
import com.jay.aas.data.MovieRepositoryImpl
import com.jay.aas.databinding.ActivityMovieBinding
import com.jay.aas.room.AppDatabase
import com.jay.aas.util.toast
import kotlinx.coroutines.launch

class MovieActivity : AppCompatActivity() {

    private val TAG = this::class.java.simpleName

    private lateinit var binding: ActivityMovieBinding

    private lateinit var inputMethodManager: InputMethodManager
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var movieRepository: MovieRepository

    private val onItemClick: (String) -> Unit = { link ->
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(link)))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        initRepository()
        initView()
    }

    private fun initRepository() {
        val remoteDataSource = MovieRemoteDataSourceImpl(RetrofitHelper.movieService)
        val localDataSource = MovieLocalDataSourceImpl(AppDatabase.getInstance(this).movieDao())
        movieRepository = MovieRepositoryImpl(remoteDataSource, localDataSource)
    }

    private fun initView() {
        binding = ActivityMovieBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        movieAdapter = MovieAdapter(onItemClick)
        binding.rvMovie.adapter = movieAdapter

        binding.evSearch.setOnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                searchMovies(v.text.toString())
                return@setOnEditorActionListener true
            }
            return@setOnEditorActionListener false
        }
        binding.ivSearch.setOnClickListener { searchMovies(binding.evSearch.text.toString()) }
    }

    private fun searchMovies(query: String) {
        if (query.isEmpty()) return

        lifecycleScope.launch {
            inputMethodManager.hideSoftInputFromWindow(binding.evSearch.windowToken, 0)
            try {
                val movies = movieRepository.getSearchMovies(query)

                if (movies.isEmpty()) {
                    binding.tvNoResult.isVisible = true
                    binding.rvMovie.isGone = true
                } else {
                    binding.tvNoResult.isGone = true
                    binding.rvMovie.isVisible = true

                    movieAdapter.setMovies(movies)
                }
            } catch (e: Exception) {
                e.printStackTrace()
                toast(getString(R.string.msg_search_failed))
            }
        }
    }

}