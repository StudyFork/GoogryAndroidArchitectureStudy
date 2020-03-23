package com.example.kangraemin.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
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
import com.example.kangraemin.model.remote.datamodel.MovieDetail
import com.example.kangraemin.ui.login.LoginActivity
import com.example.kangraemin.util.NetworkUtil
import com.example.kangraemin.util.RetrofitClient
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.BackpressureStrategy

class MainActivity : KangBaseActivity(), MainContract.View {

    private lateinit var presenter: MainContract.Presenter

    private val adapter = SearchResultAdapter()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        presenter = MainPresenter(
            mainView = this,
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

        presenter.checkAutoLoginStatus()

        binding.rvSearchResult.adapter = adapter

        val whenSearchTextChanged = binding.etSearch.textChanges()
            .subscribe {
                presenter.hasEnteredSearchText(it.toString())
            }
        compositeDisposable.add(whenSearchTextChanged)

        val whenLogOutClicked = binding.btnLogout.clicks()
            .subscribe {
                presenter.deleteAutoLoginStatus(unit = it)
            }
        compositeDisposable.add(whenLogOutClicked)

        val whenArriveSearchResult = binding.btnSearch.clicks()
            .toFlowable(BackpressureStrategy.BUFFER)
            .doOnNext {
                presenter.checkNetworkStatus()
            }
            .startWith(Unit)
            .subscribe {
                presenter.getMovies(searchText = binding.etSearch.text.toString())
            }
        compositeDisposable.add(whenArriveSearchResult)
    }

    override fun showLogOutButton() {
        binding.btnLogout.visibility = View.VISIBLE
    }

    override fun enableSearchButton() {
        binding.btnSearch.alpha = 1f
        binding.btnSearch.isEnabled = true
    }

    override fun disableSearchButton() {
        binding.btnSearch.alpha = 0.3f
        binding.btnSearch.isEnabled = false
    }

    override fun startLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    override fun showNetworkErrorText() {
        binding.rvSearchResult.visibility = View.GONE
        binding.tvNetworkError.visibility = View.VISIBLE
    }

    override fun hideNetworkErrorText() {
        binding.rvSearchResult.visibility = View.VISIBLE
        binding.tvNetworkError.visibility = View.GONE
    }

    override fun showGetMovieError() {
        toast(getString(R.string.main_error_get_movie_data_toast_message))
    }

    override fun showLogOutError() {
        toast(getString(R.string.main_error_delete_auth_toast_message))
    }

    override fun showGetAuthError() {
        toast(getString(R.string.error_get_auth_toast_message))
    }

    override fun setMoviesInAdapter(movies: ArrayList<MovieDetail>) {
        adapter.setData(movies)
    }

    override fun onDestroy() {
        presenter.onViewDestroy()
        super.onDestroy()
    }
}
