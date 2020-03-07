package com.example.kangraemin

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.kangraemin.adapter.SearchResultAdapter
import com.example.kangraemin.base.KangBaseActivity
import com.example.kangraemin.model.MovieSearchInterface
import com.example.kangraemin.util.NetworkUtil
import com.example.kangraemin.util.RetrofitClient
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.textChanges
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : KangBaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreferences = getSharedPreferences(LoginActivity.TAG_USER_INFO, 0)
        val editor = sharedPreferences.edit()

        if (sharedPreferences.getBoolean(LoginActivity.TAG_AUTO_LOGIN, false)) {
            btn_logout.visibility = View.VISIBLE
        }

        val adapter = SearchResultAdapter()

        rv_search_result.adapter = adapter

        val service = RetrofitClient()
            .getClient("https://openapi.naver.com")
            .create(MovieSearchInterface::class.java)

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
                    service.getSearchItems(
                        display = "10",
                        start = "1",
                        query = et_search.text.toString()
                    )
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe({ responseMovieSearch ->
                            adapter.data = responseMovieSearch.items
                            adapter.notifyDataSetChanged()
                        }, { it.printStackTrace() })
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
}
