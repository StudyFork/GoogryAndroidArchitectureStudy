package com.example.kangraemin.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
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

    private lateinit var mainViewModel: MainViewModel

    private val adapter = SearchResultAdapter()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mainViewModel = MainViewModel(
            movieSearchRepository = MovieSearchRepository(
                localMovieDataSource = LocalMovieDataSourceImpl(
                    movieDao = AppDatabase.getInstance(
                        context = this
                    ).movieDao()
                ),
                remoteMovieDataSource = MovieRemoteDataSourceImpl(RetrofitClient.getMovieApi())
            ),
            authRepository = AuthRepository(
                authLocalDataSource = AuthLocalDataSourceImpl(
                    AppDatabase.getInstance(context = this).authDao()
                )
            ),
            networkUtil = NetworkUtil(context = this)
        )

        binding.vm = mainViewModel

        mainViewModel.checkAutoLoginStatus()

        binding.rvSearchResult.adapter = adapter

        mainViewModel.logOutSuccess.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                startActivity(Intent(this@MainActivity, LoginActivity::class.java))
                finish()
            }
        })

        mainViewModel.logOutFail.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                toast(getString(R.string.main_error_delete_auth_toast_message))
            }
        })

        mainViewModel.getAuthError.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                toast(getString(R.string.error_get_auth_toast_message))
            }
        })

        mainViewModel.getMovieError.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                toast(getString(R.string.main_error_get_movie_data_toast_message))
            }
        })

        mainViewModel.movies.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                adapter.setData(mainViewModel.movies.get())
            }
        })

        mainViewModel.isNetworkConnected.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                if (!mainViewModel.isNetworkConnected.get()) {
                    toast(resources.getString(R.string.main_network_error_message))
                }
            }
        })
    }
}
