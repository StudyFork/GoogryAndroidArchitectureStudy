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
import com.hong.architecturestudy.utils.log
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {

    private val movieAdapter = MovieAdapter()

    override val vm: MainViewModel by viewModels {
        object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainViewModel(RepositoryDataSourceImpl(LocalDataSourceImpl(), RemoteDataSourceImpl())) as T
            }
        }
    }

    private val fragment = MovieListDialogFragment.getInstance()
    lateinit var onQueryCallBack: (String) -> Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        showToastMessage()
        setRecyclerView()
        showSelectedQueryResult()
        showRecentSearchList()

    }

    private fun showSelectedQueryResult() {
        onQueryCallBack = { query ->
            log("MainActivity showSelectedQueryResult$query")
            binding.query = query
            btn_search.callOnClick()
        }
    }

    private fun setRecyclerView() {
        binding.rvMoviesList.adapter = movieAdapter
        binding.rvMoviesList.setHasFixedSize(true)

        vm.movieList observe {
            movieAdapter.setData(it)
        }
    }

    private fun showRecentSearchList() {
        bind {
            RxView.clicks(btnSearchList)
                .throttleFirst(500L, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    fragment.show(supportFragmentManager, "fragmentDialog")
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