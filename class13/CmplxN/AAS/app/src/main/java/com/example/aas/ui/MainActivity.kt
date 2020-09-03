package com.example.aas.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aas.R
import com.example.aas.data.model.ApiResult
import com.example.aas.data.repository.MovieSearchRepository
import com.example.aas.data.repository.MovieSearchRepositoryImpl
import com.example.aas.utils.hideKeyboard
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity(R.layout.activity_main), HistorySelectionListener {
    private val compositeDisposable = CompositeDisposable()
    private val movieSearchRepository: MovieSearchRepository = MovieSearchRepositoryImpl
    private val movieAdapter = MovieAdapter()
    private val savedQueryDialogFragment by lazy { SavedQueryDialogFragment.create(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        rcv_movie.adapter = movieAdapter

        RxTextView.textChanges(et_movie_name)
            .subscribe { btn_request.isEnabled = it.isNotBlank() }
            .addTo(compositeDisposable)

        RxView.clicks(btn_request)
            .flatMapSingle {
                val keyword = et_movie_name.text.also { et_movie_name.setText("") }
                et_movie_name.clearFocus()
                hideKeyboard(this, et_movie_name)
                movieSearchRepository.getMovies(keyword.toString(), applicationContext)
            }
            .observeGetMovies()

        btn_history.isEnabled = movieSearchRepository.getSavedQueries(this).isNotEmpty()

        RxView.clicks(btn_history)
            .throttleFirst(1000L, TimeUnit.MILLISECONDS)
            .subscribe { savedQueryDialogFragment.show(supportFragmentManager, "history") }
            .addTo(compositeDisposable)
    }

    override fun onDestroy() {
        compositeDisposable.clear()
        super.onDestroy()
    }

    override fun onHistorySelection(query: String) {
        movieSearchRepository.getMovies(query, this)
            .toObservable()
            .observeGetMovies()
    }

    private fun Observable<ApiResult>.observeGetMovies() {
        this.observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Toast.makeText(this@MainActivity, "Search Completed", Toast.LENGTH_SHORT).show()
                movieAdapter.setList(it.movies)
            }, {
                Toast.makeText(this@MainActivity, "Network Error", Toast.LENGTH_LONG).show()
                it.printStackTrace()
            }).addTo(compositeDisposable)
    }
}