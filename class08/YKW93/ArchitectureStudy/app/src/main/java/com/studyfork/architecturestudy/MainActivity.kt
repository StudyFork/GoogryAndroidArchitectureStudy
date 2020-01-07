package com.studyfork.architecturestudy

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()

    private val movieResultRVAdapter: MovieResultRVAdapter by lazy {
        MovieResultRVAdapter {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(it)))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setMovieRecyclerView()

        btn_search.setOnClickListener {
            CommonUtils.hideKeyboard(baseContext, it)
            getMovieList(edit_movie_search.text.toString())
        }
    }

    private fun setMovieRecyclerView() {
        rv_movie_list.run {
            layoutManager = LinearLayoutManager(baseContext)
            adapter = movieResultRVAdapter
        }
    }

    private fun getMovieList(query: String) {
        compositeDisposable.add(
            ApiClient.apiService.getMovieList(query)
                .compose(RxUtils.applySchedulers())
                .doOnSubscribe {
                    pb_loading_view.visibility = View.VISIBLE
                }
                .doAfterTerminate {
                    pb_loading_view.visibility = View.GONE
                }
                .subscribe({
                    if (it.total != 0) {
                        movieResultRVAdapter.setItems(it.items)
                    } else {
                        Toast.makeText(baseContext, getString(R.string.empty_data_notice), Toast.LENGTH_SHORT).show()
                    }
                }, {
                    it.printStackTrace()
                })
        )
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }
}
