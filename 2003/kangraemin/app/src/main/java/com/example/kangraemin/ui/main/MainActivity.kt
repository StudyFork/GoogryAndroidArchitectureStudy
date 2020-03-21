package com.example.kangraemin.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.kangraemin.R
import com.example.kangraemin.adapter.SearchResultAdapter
import com.example.kangraemin.base.KangBaseActivity
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
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : KangBaseActivity(), MainContract.View {

    private lateinit var presenter: MainContract.Presenter

    private val adapter = SearchResultAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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

        rv_search_result.adapter = adapter

        val whenSearchTextChanged = et_search.textChanges()
            .subscribe {
                presenter.hasEnteredSearchText(it.toString())
            }
        compositeDisposable.add(whenSearchTextChanged)

        val whenLogOutClicked = btn_logout.clicks()
            .subscribe {
                presenter.deleteAutoLoginStatus(unit = it)
            }
        compositeDisposable.add(whenLogOutClicked)

        val whenArriveSearchResult = btn_search.clicks()
            .toFlowable(BackpressureStrategy.BUFFER)
            .doOnNext {
                presenter.checkNetworkStatus()
            }
            .startWith(Unit)
            .subscribe {
                presenter.getMovies(searchText = et_search.text.toString())
            }
        compositeDisposable.add(whenArriveSearchResult)
    }

    override fun showLogOutButton() {
        btn_logout.visibility = View.VISIBLE
    }

    override fun enableSearchButton() {
        btn_search.alpha = 1f
        btn_search.isEnabled = true
    }

    override fun disableSearchButton() {
        btn_search.alpha = 0.3f
        btn_search.isEnabled = false
    }

    override fun startLoginActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    override fun showNetworkErrorText() {
        rv_search_result.visibility = View.GONE
        tv_network_error.visibility = View.VISIBLE
    }

    override fun hideNetworkErrorText() {
        rv_search_result.visibility = View.VISIBLE
        tv_network_error.visibility = View.GONE
    }

    override fun setMoviesInAdapter(movies: ArrayList<MovieDetail>) {
        adapter.setData(movies)
    }

    override fun onDestroy() {
        presenter.onViewDestroy()
        super.onDestroy()
    }
}
