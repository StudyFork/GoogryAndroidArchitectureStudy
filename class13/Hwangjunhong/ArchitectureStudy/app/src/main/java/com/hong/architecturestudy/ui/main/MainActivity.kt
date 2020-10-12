package com.hong.architecturestudy.ui.main

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hong.architecturestudy.R
import com.hong.architecturestudy.data.repository.RepositoryDataSourceImpl
import com.hong.architecturestudy.data.source.local.LocalDataSourceImpl
import com.hong.architecturestudy.data.source.remote.RemoteDataSourceImpl
import com.hong.architecturestudy.databinding.ActivityMainBinding
import com.hong.architecturestudy.ext.toast
import com.hong.architecturestudy.ui.base.BaseActivity
import com.hong.architecturestudy.ui.main.adapter.MovieAdapter
import com.hong.architecturestudy.ui.moviedialog.MovieListDialogFragment

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val vm: MainViewModel by viewModels {
        object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainViewModel(RepositoryDataSourceImpl(LocalDataSourceImpl(), RemoteDataSourceImpl())) as T
            }
        }
    }
    private val fragment = MovieListDialogFragment.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setBinding()
        showToastMessage()
        setRecyclerView()
    }

    private fun setBinding() {
        binding.apply {
            vm = this@MainActivity.vm
            activity = this@MainActivity
        }
    }

    private fun setRecyclerView() {
        val movieAdapter = MovieAdapter()
        binding.rvMoviesList.adapter = movieAdapter
        binding.rvMoviesList.setHasFixedSize(true)

        vm.movieList observe {
            movieAdapter.setData(it)
        }
    }

    fun showRecentSearchList() {
        fragment.show(supportFragmentManager, "fragmentDialog")

        vm.isVisible observe {
            if (it) {
                fragment.dismiss()
                vm.isVisible.value = false
            }
        }
    }

    private fun showToastMessage() = vm.msg observe { message ->
        when (message) {
            Message.NETWORK_ERROR -> toast(R.string.message_network_error)
            Message.SUCCESS -> toast(R.string.message_success)
        }
    }


}