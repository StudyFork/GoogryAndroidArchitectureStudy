package com.hong.architecturestudy.ui.main

import android.os.Bundle
import androidx.databinding.Observable
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

    private val vm by lazy {
        MainViewModel(RepositoryDataSourceImpl(LocalDataSourceImpl(), RemoteDataSourceImpl()))
    }

    private val fragment = MovieListDialogFragment.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setBinding()
        showToastMessage()
        setRecyclerView()
    }

    private fun setBinding() {
        fragment.mainViewModel = vm

        binding.apply {
            vm = this@MainActivity.vm
            activity = this@MainActivity
        }
    }

    private fun setRecyclerView() {
        val movieAdapter = MovieAdapter()
        binding.rvMoviesList.adapter = movieAdapter
        binding.rvMoviesList.setHasFixedSize(true)

        vm.movieList.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                vm.movieList.get()?.let {
                    movieAdapter.setData(it)
                }
            }
        })
    }

    fun showRecentSearchList() {
        fragment.show(supportFragmentManager, "fragmentDialog")

        vm.isVisible.addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                fragment.dismiss()
            }
        })
    }

    private fun showToastMessage() {
        vm.msg.apply {
            addOnPropertyChangedCallback(object : Observable.OnPropertyChangedCallback() {
                override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                    when (get()) {
                        Message.NETWORK_ERROR -> toast(R.string.message_network_error)
                        Message.SUCCESS -> toast(R.string.message_success)
                    }
                }
            })
        }
    }
}