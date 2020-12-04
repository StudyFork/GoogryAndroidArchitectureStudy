package com.example.studyfork.main

import android.content.Intent
import android.os.Bundle
import androidx.databinding.Observable
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
        MainViewModel(
            RepositoryImpl(
                remoteDataSource = RemoteDataSourceImpl(),
                localDataSource = LocalDataSourceImpl(getSharedPreferences("local", MODE_PRIVATE))
            )
        )
    }

    private val requestCode = 100

    private val recyclerAdapter = MovieRecyclerAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.activity = this

        binding.recMovie.adapter = recyclerAdapter

        viewModel.showError.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                showError()
            }
        })

        viewModel.showQueryError.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                showQueryError()
            }
        })

        viewModel.showResultEmpty.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                showResultEmpty()
            }
        })
    }

    override fun onDestroy() {
        viewModel.clear()
        super.onDestroy()
    }

    fun searchMovie() {
        binding.edtQuery.text.toString().run {
            viewModel.searchMovie(this)
        }
    }

    fun showQueryError() {
        showToast("검색어를 입력해주세요")
    }

    fun showError() {
        showToast("데이터를 불러오는 중에 문가 발생했습니다.")
    }

    fun showResultEmpty() {
        showToast("검색 결과가 없습니다.")
    }

    fun showRecentSearchListActivity() {
        Intent(this, RecentSearchActivity::class.java).apply {
            startActivityForResult(this, requestCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == this.requestCode) {
            if (resultCode != RESULT_OK) return
            data?.run {
                viewModel.searchMovie(this.getStringExtra(SEARCH_ITEM) ?: "")
            }
        }
    }

    companion object {
        const val SEARCH_ITEM = "SEARCH_ITEM"
    }
}