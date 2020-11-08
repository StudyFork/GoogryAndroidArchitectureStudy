package com.example.googryandroidarchitecturestudy.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.googryandroidarchitecturestudy.R
import com.example.googryandroidarchitecturestudy.network.MovieApi
import com.example.googryandroidarchitecturestudy.ui.recycler.MovieAdapter
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.coroutines.launch

class MovieFragment : Fragment() {

    private val TAG = this::class.java.simpleName

    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieAdapter = MovieAdapter(listOf())
        movie_list.adapter = movieAdapter

        search_button.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                try {
                    val movieResponse =
                        MovieApi.movieService.searchMovies(search_text.text.toString())
                    movieAdapter.setMovies(movieResponse.items)
                } catch (e: Exception) {
                    Log.e(TAG, e.message)
                }
            }
        }
    }

}