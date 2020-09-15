package com.example.aas.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.commit
import com.example.aas.R
import com.example.aas.base.BaseActivity
import com.example.aas.data.model.ApiResult
import com.example.aas.data.repository.MovieSearchRepositoryImpl
import com.example.aas.ui.savedquerydialog.SavedQueryDialogFragment
import com.example.aas.utils.hideKeyboard
import com.example.aas.utils.showToast
import com.jakewharton.rxbinding2.view.RxView
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : BaseActivity<MainContract.Presenter>(R.layout.activity_main),
    SavedQueryDialogFragment.HistorySelectionListener, MainContract.View {

    override val presenter: MainContract.Presenter by lazy {
        MainPresenter(
            this,
            MovieSearchRepositoryImpl
        )
    }
    private val movieAdapter = MovieAdapter()
    private val fragmentFactory: FragmentFactory = FragmentFactoryImpl(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = fragmentFactory
        super.onCreate(savedInstanceState)

        rcv_movie.adapter = movieAdapter

        RxTextView.textChanges(et_movie_name)
            .subscribe { btn_request.isEnabled = it.isNotBlank() }
            .addTo(compositeDisposable)

        RxView.clicks(btn_request)
            .flatMapSingle {
                presenter.getMovies(et_movie_name.text.toString())
            }
            .observeGetMovies()

        RxView.clicks(btn_history)
            .throttleFirst(1000L, TimeUnit.MILLISECONDS)
            .flatMapSingle { presenter.getSavedQueries() }
            .subscribe({
                supportFragmentManager.commit {
                    val bundle = Bundle().apply {
                        putStringArray(SavedQueryDialogFragment.HISTORY_LIST, it.toTypedArray())
                    }
                    add(SavedQueryDialogFragment::class.java, bundle, SavedQueryDialogFragment.TAG)
                }
            }, {
                this.showToast("Local Data Loading Error", Toast.LENGTH_LONG)
                it.printStackTrace()
            }).addTo(compositeDisposable)
    }

    override fun onHistorySelection(query: String) {
        presenter.getMovies(query) // Presenter
            .toObservable()
            .observeGetMovies()
    }

    private fun Observable<ApiResult>.observeGetMovies() {
        this.observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                this@MainActivity.showToast("Search Completed", Toast.LENGTH_SHORT)
                movieAdapter.setList(it.movies)
            }, {
                this@MainActivity.showToast("Network Error", Toast.LENGTH_LONG)
                it.printStackTrace()
            }).addTo(compositeDisposable)
    }

    private class FragmentFactoryImpl(private val historySelectionListener: SavedQueryDialogFragment.HistorySelectionListener) :
        FragmentFactory() {

        override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
            return when (className) {
                SavedQueryDialogFragment::class.java.name -> SavedQueryDialogFragment.getInstance(
                    historySelectionListener
                )
                else -> super.instantiate(classLoader, className)
            }
        }
    }

    override fun onSearchRequest() {
        et_movie_name.text.also { et_movie_name.setText("") }
        et_movie_name.clearFocus()
        hideKeyboard(this, et_movie_name)
    }
}