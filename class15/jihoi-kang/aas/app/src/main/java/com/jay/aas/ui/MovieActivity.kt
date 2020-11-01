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
import com.jay.aas.R
import com.jay.aas.api.MovieResponse
import com.jay.aas.api.MovieService
import com.jay.aas.api.RetrofitHelper
import com.jay.aas.databinding.ActivityMovieBinding
import com.jay.aas.model.Movie
import com.jay.aas.util.toast
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.resume

class MovieActivity : AppCompatActivity(), CoroutineScope {

    private val TAG = this::class.java.simpleName

    private val job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    private lateinit var binding: ActivityMovieBinding

    private lateinit var inputMethodManager: InputMethodManager
    private lateinit var movieService: MovieService
    private lateinit var movieAdapter: MovieAdapter

    private val onItemClick: (String) -> Unit = { link ->
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(link)))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        movieService = RetrofitHelper.getRetrofit().create(MovieService::class.java)
        initView()
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
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

        launch {
            inputMethodManager.hideSoftInputFromWindow(binding.evSearch.windowToken, 0)
            val movies = search(query)
            when {
                movies == null -> toast(getString(R.string.msg_search_failed))
                movies.isEmpty() -> {
                    binding.tvNoResult.isVisible = true
                    binding.rvMovie.isGone = true
                }
                else -> {
                    binding.tvNoResult.isGone = true
                    binding.rvMovie.isVisible = true

                    movieAdapter.setMovies(movies)
                }
            }
        }
    }

    private suspend fun search(query: String) =
        suspendCancellableCoroutine<List<Movie>?> { continuation ->
            movieService.searchMovies(query).enqueue(object : Callback<MovieResponse> {
                override fun onResponse(
                    call: Call<MovieResponse>,
                    response: Response<MovieResponse>
                ) {
                    if (response.isSuccessful) {
                        continuation.resume(response.body()?.items ?: emptyList())
                    } else {
                        continuation.resume(null)
                    }
                }

                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                    t.printStackTrace()
                    continuation.resume(null)
                }
            })
        }

}