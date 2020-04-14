package com.example.kangraemin.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.kangraemin.R
import com.example.kangraemin.adapter.SearchResultAdapter
import com.example.kangraemin.base.KangBaseActivity
import com.example.kangraemin.databinding.ActivityMainBinding
import com.example.kangraemin.ui.login.LoginActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : KangBaseActivity() {

    private val adapter = SearchResultAdapter()

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.vm = mainViewModel

        mainViewModel.checkAutoLoginStatus()

        binding.rvSearchResult.adapter = adapter

        binding.lifecycleOwner = this

        mainViewModel.logOutSuccess.observe(this, Observer {
            startActivity(Intent(this@MainActivity, LoginActivity::class.java))
            finish()
        })

        mainViewModel.logOutFail.observe(this, Observer {
            toast(getString(R.string.main_error_delete_auth_toast_message))
        })

        mainViewModel.getAuthError.observe(this, Observer {
            toast(getString(R.string.error_get_auth_toast_message))
        })

        mainViewModel.getMovieError.observe(this, Observer {
            toast(getString(R.string.main_error_get_movie_data_toast_message))
        })

        mainViewModel.movies.observe(this, Observer {
            adapter.setData(it)
        })

        mainViewModel.isNetworkConnected.observe(this, Observer {
            if (!it) {
                toast(resources.getString(R.string.main_network_error_message))
            }
        })
    }
}
