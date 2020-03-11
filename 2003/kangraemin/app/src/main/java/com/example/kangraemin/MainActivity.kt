package com.example.kangraemin

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.kangraemin.adapter.SearchResultAdapter
import com.example.kangraemin.base.KangBaseActivity
import com.example.kangraemin.model.MovieSearchRepository
import com.example.kangraemin.util.NetworkUtil
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.textChanges
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : KangBaseActivity() {

    val adapter = SearchResultAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreferences = getSharedPreferences(LoginActivity.TAG_USER_INFO, 0)
        val editor = sharedPreferences.edit()

        if (sharedPreferences.getBoolean(LoginActivity.TAG_AUTO_LOGIN, false)) {
            btn_logout.visibility = View.VISIBLE
        }

        rv_search_result.adapter = adapter

        search("")

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

        val whenSearchButtonClicked = btn_search.clicks()
            .map {
                when (NetworkUtil().getConnectivityStatus(context = this)) {
                    NetworkUtil.NetworkStatus.NOT_CONNECTED -> false
                    else -> true
                }
            }
            .subscribe { connectedToInternet ->
                if (connectedToInternet) {
                    rv_search_result.visibility = View.VISIBLE
                    tv_network_error.visibility = View.GONE
                    search(et_search.text.toString())
                } else {
                    rv_search_result.visibility = View.GONE
                    tv_network_error.visibility = View.VISIBLE
                }
            }
        compositeDisposable.add(whenSearchButtonClicked)

        val whenLogOutClicked = btn_logout.clicks()
            .subscribe {
                editor.remove(LoginActivity.TAG_AUTO_LOGIN)
                editor.apply()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        compositeDisposable.add(whenLogOutClicked)
    }

    private fun search(query: String) {
        val whenSearchFinished = MovieSearchRepository()
            .getMovieData(query = query, context = this)
            .subscribe({ responseMovieSearch ->
                adapter.setData(responseMovieSearch.items)
            }, { it.printStackTrace() })
        compositeDisposable.add(whenSearchFinished)
    }
}
