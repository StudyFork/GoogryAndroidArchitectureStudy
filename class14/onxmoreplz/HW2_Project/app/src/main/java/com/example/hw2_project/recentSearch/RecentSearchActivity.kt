package com.example.hw2_project.recentSearch

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import com.example.hw2_project.R
import com.example.hw2_project.data.repository.MovieRepositoryImpl
import com.example.hw2_project.databinding.ActivityRecentSearchBinding

class RecentSearchActivity : AppCompatActivity(),
    RecentSearchRecyclerViewAdapter.ClickSavedMovieListener {
    private lateinit var binding: ActivityRecentSearchBinding

    private val recentSearchAdapter = RecentSearchRecyclerViewAdapter(this)

    private val movieRepository = MovieRepositoryImpl()

    private val recentSearchViewModel = RecentSearchViewModel(movieRepository)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recent_search)
        binding.vm = recentSearchViewModel

        binding.rvRecentList.setHasFixedSize(true)
        binding.rvRecentList.adapter = recentSearchAdapter

        recentSearchViewModel.getRecentMovie()
        observeRecentSearchViewModelEvent()
    }

    private fun observeRecentSearchViewModelEvent() {
        recentSearchViewModel.movieListFromSharedPref.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                recentSearchViewModel.movieListFromSharedPref.get()?.let {
                    recentSearchAdapter.updateMovieList(it)
                }
            }
        })
/*        recentSearchViewModel.clickedMovie.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                val intent = Intent()
                intent.putExtra("recentMovie", recentSearchViewModel.clickedMovie)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        })*/
        recentSearchViewModel.backToMainActivity.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                finish()
            }
        })
    }

    override fun onClickSavedMovie(movie: String) {
        val intent = Intent()
        intent.putExtra("recentMovie", movie)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

}