package com.studyfork.architecturestudy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()

    private val movieResultRVAdapter: MovieResultRVAdapter by lazy {
        MovieResultRVAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setMovieRecyclerView()

        btn_search.setOnClickListener {
            getMovieList(edit_movie_search.text.toString())
        }
    }

    private fun setMovieRecyclerView() {
        rv_movie_list.apply {
            layoutManager = LinearLayoutManager(baseContext)
            adapter = movieResultRVAdapter
        }
    }

    private fun getMovieList(query: String) {
        compositeDisposable.add(
            ApiClient.getService().getMovieList(query)
                .compose(RxUtils.applySchedulers())
                .subscribe({
                    movieResultRVAdapter.setItems(it.items)
                }, {
                    it.printStackTrace()
                })
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}
