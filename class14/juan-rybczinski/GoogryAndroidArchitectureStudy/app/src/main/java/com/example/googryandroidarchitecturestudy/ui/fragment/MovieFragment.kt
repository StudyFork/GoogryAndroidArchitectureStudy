package com.example.googryandroidarchitecturestudy.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.googryandroidarchitecturestudy.R
import com.example.googryandroidarchitecturestudy.network.MovieApi
import com.example.googryandroidarchitecturestudy.ui.recycler.MovieAdapter
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.coroutines.launch

class MovieFragment : Fragment() {

    private val TAG = this::class.java.simpleName

    private lateinit var movieList: RecyclerView
    private lateinit var movieAdapter: MovieAdapter

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_movie, container, false)

//        search_button.setOnClickListener {
//            Toast.makeText(requireContext(), "검색", Toast.LENGTH_SHORT).show()
//        }

        movieList = view.findViewById(R.id.movie_list)
        movieAdapter = MovieAdapter(listOf())
        movieList.adapter = movieAdapter

        view.findViewById<Button>(R.id.search_button).setOnClickListener {
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

        return view
    }

}