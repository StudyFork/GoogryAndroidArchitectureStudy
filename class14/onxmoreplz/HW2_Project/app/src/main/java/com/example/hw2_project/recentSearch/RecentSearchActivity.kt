package com.example.hw2_project.recentSearch

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.hw2_project.R
import com.example.hw2_project.data.repository.MovieRepositoryImpl
import com.example.hw2_project.databinding.ActivityRecentSearchBinding

class RecentSearchActivity : AppCompatActivity(), RecentSearchContract.View,
    RecentSearchRecyclerViewAdapter.SavedMovieClickListener {
    private lateinit var binding: ActivityRecentSearchBinding

    private val recentSearchAdapter = RecentSearchRecyclerViewAdapter(this)

    private val recentSearchPresenter = RecentSearchPresenter(
        this,
        MovieRepositoryImpl()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recent_search)
        binding.rvRecentList.setHasFixedSize(true)
        binding.rvRecentList.adapter = recentSearchAdapter

        recentSearchPresenter.searchRecentQuery()
    }

    override fun showRecentSearchMovieList(list: List<String>) {
        recentSearchAdapter.updateMovieList(list)
    }

    fun clickBackBtn() {
        this.finish()
    }

    override fun clickSavedMovie(movie: String) {
        val intent = Intent()
        intent.putExtra("recentMovie", movie)
        setResult(Activity.RESULT_OK, intent)
        finish()
    }
}