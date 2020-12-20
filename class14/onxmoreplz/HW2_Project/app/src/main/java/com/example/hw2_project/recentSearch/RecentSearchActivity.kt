package com.example.hw2_project.recentSearch

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.hw2_project.R
import com.example.hw2_project.databinding.ActivityRecentSearchBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecentSearchActivity : AppCompatActivity(),
    RecentSearchRecyclerViewAdapter.ClickSavedMovieListener {

    private lateinit var binding: ActivityRecentSearchBinding
    private val recentSearchViewModel: RecentSearchViewModel by viewModels()
    private val recentSearchAdapter = RecentSearchRecyclerViewAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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