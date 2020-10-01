package com.example.aas.ui.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.Observable
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
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity :
    BaseActivity<ActivityMainBinding>(R.layout.activity_main),
    SavedQueryDialogFragment.HistorySelectionListener, MovieAdapter.MovieSelectionListener {

    private val movieAdapter = MovieAdapter(this)
    private val fragmentFactory: FragmentFactory = FragmentFactoryImpl(this)
    private val mainViewModel = MainViewModel(MovieSearchRepositoryImpl)

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = fragmentFactory
        super.onCreate(savedInstanceState)

        initBinding()
        initView()
        initObserver()

        savedInstanceState?.getParcelableArrayList<Movie>(RCV_LIST)?.let {
            movieAdapter.setList(it)
        }
    }

    override fun onHistorySelection(query: String) {
        mainViewModel.getMovies(query)
    }

    override fun onMovieSelect(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelableArrayList(RCV_LIST, movieAdapter.movieList)
    }

    private fun initBinding() {
        binding.viewModel = mainViewModel
    }

    private fun initView() {
        rcv_movie.adapter = movieAdapter
        RxTextView.textChanges(et_movie_name)
            .subscribe { btn_request.isEnabled = it.isNotBlank() }
            .addTo(compositeDisposable)
    }

    private fun initObserver() {
        mainViewModel.searchRequestEvent.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                et_movie_name.text.also { et_movie_name.setText("") }
                et_movie_name.clearFocus()
                hideKeyboard(this@MainActivity, et_movie_name)
            }
        })

        mainViewModel.failureEvent.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                showToast("Request Failed", Toast.LENGTH_LONG)
            }
        })

        mainViewModel.savedQueryResult.addOnPropertyChangedCallback(object :
            Observable.OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                mainViewModel.savedQueryResult.get()?.let {
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
        })
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

    companion object {
        const val RCV_LIST = "RCV_LIST"
    }
}