package com.example.hw2_project.recentSearch

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.hw2_project.R
import com.example.hw2_project.data.repository.MovieRepositoryImpl
import com.example.hw2_project.databinding.ActivityRecentSearchBinding

class RecentSearchActivity : AppCompatActivity(),
    RecentSearchRecyclerViewAdapter.ClickSavedMovieListener {
    private lateinit var recentSearchViewModel: RecentSearchViewModel
    private lateinit var binding: ActivityRecentSearchBinding
    private val recentSearchAdapter = RecentSearchRecyclerViewAdapter(this)
    private val movieRepository = MovieRepositoryImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        recentSearchViewModel = ViewModelProvider(
            this,
            RecentSearchViewModel.RecentSearchViewModelFactory(movieRepository)
        )
            .get(RecentSearchViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recent_search)
        binding.lifecycleOwner = this
        binding.vm = recentSearchViewModel

        binding.rvRecentList.setHasFixedSize(true)
        binding.rvRecentList.adapter = recentSearchAdapter

        recentSearchViewModel.getRecentMovie()
        observeRecentSearchViewModelEvent()
    }

    private fun observeRecentSearchViewModelEvent() {
        recentSearchViewModel.movieListFromSharedPref.observe(this) {
            recentSearchViewModel.movieListFromSharedPref.value.let {
                recentSearchAdapter.updateMovieList(it)
            }
        }
        recentSearchViewModel.backToMainActivity.observe(this) {
            finish()
        }
    }

    override fun onClickSavedMovie(movie: String) {
        val intent = Intent()
        intent.putExtra("recentMovie", movie)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

}