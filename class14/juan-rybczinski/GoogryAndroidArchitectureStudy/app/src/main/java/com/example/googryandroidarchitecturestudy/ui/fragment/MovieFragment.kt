package com.example.googryandroidarchitecturestudy.ui.fragment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.googryandroidarchitecturestudy.R
import com.example.googryandroidarchitecturestudy.data.local.MovieLocalDataSourceImpl
import com.example.googryandroidarchitecturestudy.data.remote.MovieRemoteDataSourceImpl
import com.example.googryandroidarchitecturestudy.data.repository.MovieRepository
import com.example.googryandroidarchitecturestudy.data.repository.MovieRepositoryImpl
import com.example.googryandroidarchitecturestudy.database.MovieDatabase
import com.example.googryandroidarchitecturestudy.databinding.FragmentMovieBinding
import com.example.googryandroidarchitecturestudy.domain.Movie
import com.example.googryandroidarchitecturestudy.ui.MovieContract
import com.example.googryandroidarchitecturestudy.ui.presenter.MoviePresenter
import com.example.googryandroidarchitecturestudy.ui.recycler.MovieAdapter
import kotlinx.coroutines.launch

class MovieFragment : Fragment(), MovieContract.View {

    private val TAG = this::class.java.simpleName

    private val movieAdapter = MovieAdapter {
        presenter.selectMovieItem(it)
    }

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    private val movieRepository: MovieRepository by lazy {
        val movieRemoteDataSource = MovieRemoteDataSourceImpl()
        val movieLocalDataSource =
            MovieLocalDataSourceImpl(MovieDatabase.getInstance(requireContext()))
        MovieRepositoryImpl(movieRemoteDataSource, movieLocalDataSource)
    }

    private val inputMethodManager by lazy(LazyThreadSafetyMode.NONE) {
        requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    private val presenter: MovieContract.Presenter by lazy {
        MoviePresenter(this, movieRepository)
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

        setupUi()
    }

    private fun setupUi() {
        binding.movieList.adapter = movieAdapter

        binding.searchButton.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                presenter.queryMovieList(binding.searchText.text.toString())
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    override fun showMovieList(items: List<Movie>) {
        movieAdapter.setMovies(items)
    }

    override fun showQueryEmpty() {
        toast(getString(R.string.no_keyword))
        movieAdapter.setMovies(listOf())
    }

    override fun showNoMovieSearchResult() {
        toast(getString(R.string.no_results))
        movieAdapter.setMovies(listOf())
    }

    override fun showMovieDetail(item: Movie) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(item.link)))
    }

    override fun showMovieSearchFailed(e: Exception) {
        toast(getString(R.string.error_occurred))
        Log.e(TAG, e.message.toString())
    }

    override fun hideKeyboard() {
        inputMethodManager.hideSoftInputFromWindow(
            requireActivity().currentFocus?.windowToken,
            0
        )
    }

}