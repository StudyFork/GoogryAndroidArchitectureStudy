package com.example.kangraemin

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.kangraemin.adapter.SearchResultAdapter
import com.example.kangraemin.base.KangBaseActivity
import com.example.kangraemin.model.AuthRepository
import com.example.kangraemin.model.MovieSearchRepository
import com.example.kangraemin.model.remote.datadao.MovieImpl
import com.example.kangraemin.model.remote.datamodel.Movies
import com.example.kangraemin.util.NetworkUtil
import com.example.kangraemin.util.RetrofitClient
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : KangBaseActivity() {

    val remoteMovieDatasource by lazy {
        MovieImpl(RetrofitClient.getMovieApi())
    }

    val adapter = SearchResultAdapter()

    val authRepo: AuthRepository by lazy {
        AuthRepository
            .getAuthRepo()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val getAuth = authRepo
            .getAuth(context = this)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.autoLogin) {
                    btn_logout.visibility = View.VISIBLE
                }
            }, { it.printStackTrace() })
        compositeDisposable.add(getAuth)

        rv_search_result.adapter = adapter

        val whenSearchTextChanged = et_search.textChanges()
            .map { enteredText ->
                enteredText.isNotEmpty()
            }
            .subscribe { isText ->
                if (isText) {
                    btn_search.alpha = 1f
                    btn_search.isEnabled = true
                } else {
                    btn_search.alpha = 0.3f
                    btn_search.isEnabled = false
                }
            }
        compositeDisposable.add(whenSearchTextChanged)

        val whenLogOutClicked = btn_logout.clicks()
            .subscribe {
                val deleteAuth = authRepo
                    .deleteAuth(context = this)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        startActivity(Intent(this, LoginActivity::class.java))
                        finish()
                    }, { it.printStackTrace() })
                compositeDisposable.add(deleteAuth)
            }
        compositeDisposable.add(whenLogOutClicked)

        val whenArriveSearchResult = btn_search.clicks()
            .toFlowable(BackpressureStrategy.BUFFER)
            .doOnNext {
                when (NetworkUtil().getConnectivityStatus(context = this)) {
                    NetworkUtil.NetworkStatus.NOT_CONNECTED -> {
                        rv_search_result.visibility = View.GONE
                        tv_network_error.visibility = View.VISIBLE
                    }
                    else -> {
                        rv_search_result.visibility = View.VISIBLE
                        tv_network_error.visibility = View.GONE
                    }
                }
            }
            .map { et_search.text.toString() }
            .switchMap { search(it) }
            .startWith(search(""))
            .subscribe({ movies ->
                adapter.setData(movies.items)
            }, { it.printStackTrace() })
        compositeDisposable.add(whenArriveSearchResult)
    }

    private fun search(query: String): Flowable<Movies> {
        return MovieSearchRepository(remoteMovieDatasource = remoteMovieDatasource)
            .getMovieData(query = query, context = this)
            .cache()
            .observeOn(AndroidSchedulers.mainThread())
    }
}
