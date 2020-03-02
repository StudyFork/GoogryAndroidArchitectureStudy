package com.studyfork.architecturestudy.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.studyfork.architecturestudy.R
import com.studyfork.architecturestudy.base.BaseActivity
import com.studyfork.architecturestudy.databinding.ActivityMainBinding
import com.studyfork.architecturestudy.extension.hideKeyboard
import com.studyfork.architecturestudy.ui.adapter.MovieResultRVAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {
    private val movieResultRVAdapter: MovieResultRVAdapter by lazy {
        MovieResultRVAdapter {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it)))
        }
    }

    override val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setMovieRecyclerView()
        setKeyboardObserve()
    }

    private fun setMovieRecyclerView() {
        binding.rvMovieList.run {
            layoutManager = LinearLayoutManager(baseContext)
            adapter = movieResultRVAdapter
        }
    }

    private fun setKeyboardObserve() {
        with(viewModel.hidesKeyboard) {
            observe(this@MainActivity, Observer {
                if (it) binding.editMovieSearch.hideKeyboard()
            })
        }
    }
}
