package com.studyfork.architecturestudy.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.studyfork.architecturestudy.R
import com.studyfork.architecturestudy.base.BaseActivity
import com.studyfork.architecturestudy.common.ResourceProvider
import com.studyfork.architecturestudy.databinding.ActivityMainBinding
import com.studyfork.architecturestudy.extension.hideKeyboard
import com.studyfork.architecturestudy.ui.adapter.MovieResultRVAdapter

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {

    private val movieResultRVAdapter: MovieResultRVAdapter by lazy {
        MovieResultRVAdapter {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it)))
        }
    }

    override fun getViewModelInstance(): MainViewModel =
        MainViewModel(ResourceProvider(baseContext))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.activity = this@MainActivity
        setMovieRecyclerView()
    }

    private fun setMovieRecyclerView() {
        binding.rvMovieList.run {
            layoutManager = LinearLayoutManager(baseContext)
            adapter = movieResultRVAdapter
        }
    }

    fun onClick(view: View) {
        when (view.id) {
            R.id.btn_search -> {
                currentFocus?.hideKeyboard()
                viewModel.getMovieList()
            }
        }
    }
}
