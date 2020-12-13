package com.deepco.studyfork

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.observe
import com.deepco.studyfork.databinding.ActivityMainBinding
import com.deepco.studyfork.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = mainViewModel
        setObserver()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQ_CODE_RECENT_SEARCH && resultCode == RESULT_OK) {
            data?.getStringExtra(EXTRA_MOVIE_TITLE)?.let { query ->
                binding.movieEditText.setText(query)
                mainViewModel.queryMovie()
            }
        }
    }

    fun recentSearch() {
        val intent = Intent(this, RecentSearchActivity::class.java)
        startActivityForResult(intent, REQ_CODE_RECENT_SEARCH)
    }

    private fun setObserver() {
        mainViewModel.message.observe(this) {
            showEmptyMessage(it)
        }

        mainViewModel.startRecentSearchActivity.observe(this) {
            recentSearch()
        }
    }

    companion object {
        const val REQ_CODE_RECENT_SEARCH = 1001
        const val EXTRA_MOVIE_TITLE = "movie_title"
    }
}