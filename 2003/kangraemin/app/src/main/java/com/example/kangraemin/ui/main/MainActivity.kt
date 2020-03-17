package com.example.kangraemin.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.kangraemin.R
import com.example.kangraemin.adapter.SearchResultAdapter
import com.example.kangraemin.base.KangBaseActivity
import com.example.kangraemin.model.AppDatabase
import com.example.kangraemin.model.AuthRepository
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

    private val remoteMovieDataSource by lazy {
        MovieRemoteDataSourceImpl(RetrofitClient.getMovieApi())
    }

    private val localMovieDataSource by lazy {
        val db = AppDatabase.getInstance(context = this)
        LocalMovieDataSourceImpl(movieDao = db.movieDao())
    }

    private val authRepository by lazy {
        val db = AppDatabase.getInstance(context = this)
        AuthRepository(authLocalDataSource = AuthLocalDataSourceImpl(db.authDao()))
    }

    private val adapter = SearchResultAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter = MainPresenter(
            mainView = this,
            remoteMovieDataSource = remoteMovieDataSource,
            localMovieDataSource = localMovieDataSource,
            authRepository = authRepository
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
                presenter.deleteAutoLoginStatus()
            }
        compositeDisposable.add(whenLogOutClicked)

        val whenArriveSearchResult = btn_search.clicks()
            .toFlowable(BackpressureStrategy.BUFFER)
            .doOnNext {
                presenter.checkNetworkStatus(NetworkUtil().getConnectivityStatus(context = this))
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
