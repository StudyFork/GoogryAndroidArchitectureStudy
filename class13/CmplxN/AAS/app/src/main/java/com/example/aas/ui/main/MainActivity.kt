package com.example.aas.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.aas.R
import com.example.aas.base.BaseActivity
import com.example.aas.data.repository.MovieSearchRepositoryImpl
import com.example.aas.databinding.ActivityMainBinding
import com.example.aas.ui.savedquerydialog.SavedQueryDialogFragment
import com.example.aas.utils.hideKeyboard
import com.example.aas.utils.showToast
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity :
    BaseActivity<ActivityMainBinding>(R.layout.activity_main),
    SavedQueryDialogFragment.HistorySelectionListener, MovieAdapter.MovieSelectionListener {

    private val movieAdapter = MovieAdapter(this)
    private val fragmentFactory: FragmentFactory = FragmentFactoryImpl(this)
    private val mainViewModel: MainViewModel by viewModels {
        object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MainViewModel(MovieSearchRepositoryImpl) as T
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = fragmentFactory
        super.onCreate(savedInstanceState)

        initBinding()
        initView()
        initObserver()
    }

    override fun onHistorySelection(query: String) {
        mainViewModel.query.value = query
        mainViewModel.getMovies()
    }

    override fun onMovieSelect(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    private fun initBinding() {
        with(binding) {
            lifecycleOwner = this@MainActivity
            viewModel = mainViewModel
        }
    }

    private fun initView() {
        rcv_movie.adapter = movieAdapter
        movieAdapter.setList(mainViewModel.movieSearchResult.value ?: emptyList())
        RxTextView.textChanges(et_movie_name)
            .subscribe { btn_request.isEnabled = it.isNotBlank() }
            .addTo(compositeDisposable)
    }

    private fun initObserver() {
        mainViewModel.searchRequestEvent.observe(this) {
            with(et_movie_name) {
                setText("")
                clearFocus()
                hideKeyboard(this@MainActivity, this)
            }
        }

        mainViewModel.failureEvent.observe(this) {
            showToast("Request Failed", Toast.LENGTH_LONG)
        }

        mainViewModel.savedQueryResult.observe(this) {
            supportFragmentManager.commit {
                val bundle = Bundle().apply {
                    putStringArray(SavedQueryDialogFragment.HISTORY_LIST, it)
                }
                add(
                    SavedQueryDialogFragment::class.java,
                    bundle,
                    SavedQueryDialogFragment.TAG
                )
            }
        }
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