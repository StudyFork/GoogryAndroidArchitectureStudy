package com.example.googryandroidarchitecturestudy.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.googryandroidarchitecturestudy.data.local.MovieLocalDataSourceImpl
import com.example.googryandroidarchitecturestudy.data.remote.MovieRemoteDataSourceImpl
import com.example.googryandroidarchitecturestudy.data.repository.MovieRepository
import com.example.googryandroidarchitecturestudy.data.repository.MovieRepositoryImpl
import com.example.googryandroidarchitecturestudy.database.MovieDatabase
import com.example.googryandroidarchitecturestudy.databinding.FragmentMovieBinding
import com.example.googryandroidarchitecturestudy.ui.recycler.MovieAdapter
import kotlinx.coroutines.launch

class MovieFragment : Fragment() {

    private val TAG = this::class.java.simpleName

    private lateinit var movieAdapter: MovieAdapter

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    private val movieRepository: MovieRepository by lazy {
        val movieRemoteDataSource = MovieRemoteDataSourceImpl()
        val movieLocalDataSource =
            MovieLocalDataSourceImpl(MovieDatabase.getInstance(requireContext()))
        MovieRepositoryImpl(movieRemoteDataSource, movieLocalDataSource)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieAdapter = MovieAdapter()
        binding.movieList.adapter = movieAdapter

        binding.searchButton.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                try {
                    movieAdapter.setMovies(
                        movieRepository.searchMovies(
                            binding.searchText.text.toString()
                        )
                    )
                } catch (e: Exception) {
                    Log.e(TAG, e.message.toString())
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}