package com.example.aas.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.commit
import com.example.aas.R
import com.example.aas.base.BaseActivity
import com.example.aas.data.model.Movie
import com.example.aas.data.repository.MovieSearchRepositoryImpl
import com.example.aas.databinding.ActivityMainBinding
import com.example.aas.ui.savedquerydialog.SavedQueryDialogFragment
import com.example.aas.utils.hideKeyboard
import com.example.aas.utils.showToast
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity :
    BaseActivity<MainContract.Presenter, ActivityMainBinding>(R.layout.activity_main),
    SavedQueryDialogFragment.HistorySelectionListener, MovieAdapter.MovieSelectionListener,
    MainContract.View {

    override val presenter: MainContract.Presenter by lazy {
        MainPresenter(this, MovieSearchRepositoryImpl)
    }
    private val movieAdapter = MovieAdapter(this)
    private val fragmentFactory: FragmentFactory = FragmentFactoryImpl(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = fragmentFactory
        super.onCreate(savedInstanceState)

        binding.run {
            activity = this@MainActivity
            rcvMovie.adapter = movieAdapter
            RxTextView.textChanges(etMovieName)
                .subscribe { btnRequest.isEnabled = it.isNotBlank() }
                .addTo(compositeDisposable)
        }
    }

    override fun onHistorySelection(query: String) {
        presenter.getMovies(query)
    }

    override fun onSearchRequest() {
        et_movie_name.text.also { et_movie_name.setText("") }
        et_movie_name.clearFocus()
        hideKeyboard(this, et_movie_name)
    }

    override fun showMovieResult(movieResult: Single<List<Movie>>) {
        movieResult
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                this.showToast("Search Completed", Toast.LENGTH_SHORT)
                movieAdapter.setList(it)
            }, {
                this.showToast("Network Error", Toast.LENGTH_LONG)
                it.printStackTrace()
            }).addTo(compositeDisposable)
    }

    override fun onMovieSelect(uri: String) {
        presenter.openMovieSpecific(uri)
    }

    override fun openWebLink(uri: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        startActivity(intent)
    }

    override fun showSavedQuery(savedQuery: Single<List<String>>) {
        savedQuery
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
}