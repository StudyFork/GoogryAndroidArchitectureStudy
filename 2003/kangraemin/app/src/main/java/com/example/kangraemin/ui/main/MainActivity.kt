package com.example.kangraemin.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kangraemin.R
import com.example.kangraemin.adapter.SearchResultAdapter
import com.example.kangraemin.base.KangBaseActivity
import com.example.kangraemin.databinding.ActivityMainBinding
import com.example.kangraemin.model.AppDatabase
import com.example.kangraemin.model.AuthRepository
import com.example.kangraemin.model.MovieSearchRepository
import com.example.kangraemin.model.local.datadao.AuthLocalDataSourceImpl
import com.example.kangraemin.model.local.datadao.LocalMovieDataSourceImpl
import com.example.kangraemin.model.remote.datadao.MovieRemoteDataSourceImpl
import com.example.kangraemin.ui.login.LoginActivity
import com.example.kangraemin.util.NetworkUtil
import com.example.kangraemin.util.RetrofitClient

class MainActivity : KangBaseActivity() {

    private val adapter = SearchResultAdapter()

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel: MainViewModel by viewModels {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainViewModel(
                    movieSearchRepository = MovieSearchRepository(
                        localMovieDataSource = LocalMovieDataSourceImpl(
                            movieDao = AppDatabase.getInstance(
                                context = this@MainActivity
                            ).movieDao()
                        ),
                        remoteMovieDataSource = MovieRemoteDataSourceImpl(RetrofitClient.getMovieApi())
                    ),
                    authRepository = AuthRepository(
                        authLocalDataSource = AuthLocalDataSourceImpl(
                            AppDatabase.getInstance(context = this@MainActivity).authDao()
                        )
                    ),
                    networkUtil = NetworkUtil(context = this@MainActivity)
                ) as T
            }
        }
    }

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
