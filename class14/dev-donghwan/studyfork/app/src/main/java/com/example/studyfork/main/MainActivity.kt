package com.example.studyfork.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.example.studyfork.MovieRecyclerAdapter
import com.example.studyfork.R
import com.example.studyfork.base.BaseActivity
import com.example.studyfork.databinding.ActivityMainBinding
import com.example.studyfork.recent.RecentSearchActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity :
    BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel: MainViewModel by viewModels()

    private val requestCode = 100

    private val recyclerAdapter = MovieRecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = viewModel
        binding.recMovie.adapter = recyclerAdapter

        viewModel.showError.observe(this) {
            showError()
        }

        viewModel.showQueryError.observe(this) {
            showQueryError()
        }

        viewModel.showResultEmpty.observe(this) {
            showResultEmpty()
        }

        viewModel.showRecentSearchActivity.observe(this) {
            showRecentSearchListActivity()
        }
    }

    private fun showQueryError() {
        showToast("검색어를 입력해주세요")
    }

    private fun showError() {
        showToast("데이터를 불러오는 중에 문가 발생했습니다.")
    }

    private fun showResultEmpty() {
        showToast("검색 결과가 없습니다.")
    }

    private fun showRecentSearchListActivity() {
        Intent(this, RecentSearchActivity::class.java).apply {
            startActivityForResult(this, requestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == this.requestCode) {
            if (resultCode != RESULT_OK) return
            data?.run {
                runOnUiThread {
                    viewModel.query.value = this.getStringExtra(SEARCH_ITEM) ?: ""
                    viewModel.searchMovie()
                }
            }
        }
    }

    companion object {
        const val SEARCH_ITEM = "SEARCH_ITEM"
    }
}