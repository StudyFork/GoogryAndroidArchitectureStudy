package com.example.studyfork.main

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.studyfork.MovieRecyclerAdapter
import com.example.studyfork.R
import com.example.studyfork.base.BaseActivity
import com.example.studyfork.data.local.LocalDataSourceImpl
import com.example.studyfork.data.remote.RemoteDataSourceImpl
import com.example.studyfork.data.repository.RepositoryImpl
import com.example.studyfork.databinding.ActivityMainBinding
import com.example.studyfork.recent.RecentSearchActivity

class MainActivity :
    BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val viewModel by lazy {
        ViewModelProvider(
            this,
            MainViewModelFactory(
                RepositoryImpl(
                    remoteDataSource = RemoteDataSourceImpl(),
                    localDataSource = LocalDataSourceImpl(
                        getSharedPreferences(
                            "local",
                            MODE_PRIVATE
                        )
                    )
                )
            )
        ).get(MainViewModel::class.java)
    }

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