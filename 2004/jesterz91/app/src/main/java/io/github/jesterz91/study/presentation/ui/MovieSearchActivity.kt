package io.github.jesterz91.study.presentation.ui

import android.net.Uri
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.DividerItemDecoration
import com.jakewharton.rxbinding3.appcompat.queryTextChanges
import io.github.jesterz91.study.R
import io.github.jesterz91.study.data.local.MovieDatabase
import io.github.jesterz91.study.data.local.source.MovieLocalDataSourceImpl
import io.github.jesterz91.study.data.remote.source.MovieRemoteDataSourceImpl
import io.github.jesterz91.study.databinding.ActivityMovieSearchBinding
import io.github.jesterz91.study.domain.mapper.MovieLocalMapper
import io.github.jesterz91.study.domain.mapper.MovieRemoteMapper
import io.github.jesterz91.study.domain.repository.MovieRepositoryImpl
import io.github.jesterz91.study.domain.usecase.GetMovieUseCase
import io.github.jesterz91.study.presentation.common.BaseActivity
import io.github.jesterz91.study.presentation.constant.Constant
import io.github.jesterz91.study.presentation.extension.hide
import io.github.jesterz91.study.presentation.extension.show
import io.github.jesterz91.study.presentation.extension.toast
import io.reactivex.BackpressureStrategy
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit

class MovieSearchActivity :
    BaseActivity<ActivityMovieSearchBinding>(ActivityMovieSearchBinding::inflate) {

    private val backPressSubject = BehaviorSubject.createDefault(0L)

    private val movieAdapter by lazy { MovieSearchAdapter() }

    private val customTabsIntent: CustomTabsIntent by lazy {
        CustomTabsIntent.Builder().build()
    }

    private val movieDatabase by lazy {
        MovieDatabase.getInstance(applicationContext)
    }

    private val movieUseCase by lazy {
        GetMovieUseCase(
            movieRepository = MovieRepositoryImpl(
                movieLocalDataSource = MovieLocalDataSourceImpl(movieDatabase.movieDao()),
                movieLocalMapper = MovieLocalMapper(),
                movieRemoteDataSource = MovieRemoteDataSourceImpl(),
                movieRemoteMapper = MovieRemoteMapper()
            )
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.recyclerView.apply {
            setHasFixedSize(true)
            addItemDecoration(
                DividerItemDecoration(
                    this@MovieSearchActivity,
                    DividerItemDecoration.VERTICAL
                )
            )
            adapter = movieAdapter.also {
                subscribeBrowseEvent(it.getClickObservable())
            }
        }
        subscribeBackPressEvent()
    }

    private fun subscribeMovieSearchEvent(searchView: SearchView) {
        searchView.queryTextChanges()
            .toFlowable(BackpressureStrategy.DROP)
            .debounce(Constant.SEARCH_DEBOUNCE_TIME, TimeUnit.MILLISECONDS)
            .filter(CharSequence::isNotBlank)
            .map(CharSequence::toString)
            .distinctUntilChanged()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { binding.progressBar.show() }
            .flatMap(movieUseCase::invoke)
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorReturn { mutableListOf() }
            .doOnNext { binding.progressBar.hide() }
            .subscribe {
                movieAdapter.changeItems(it)
                movieAdapter.notifyDataSetChanged()
                hideSoftKeyboard(searchView.windowToken)
            }.addTo(disposables)
    }

    private fun subscribeBrowseEvent(uriObservable: Observable<Uri>) {
        uriObservable.subscribe { uri ->
            customTabsIntent.launchUrl(this, uri)
        }.addTo(disposables)
    }

    private fun subscribeBackPressEvent() {
        backPressSubject.buffer(2, 1)
            .map { it.last() - it.first() }
            .subscribe { duration ->
                if (duration < Constant.FINISH_DURATION) {
                    finish()
                } else {
                    toast(getString(R.string.back_press_message))
                }
            }.addTo(disposables)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        (menu?.findItem(R.id.menu_search)?.actionView as? SearchView)?.apply {
            maxWidth = Int.MAX_VALUE
            queryHint = getString(R.string.search_movie_hint)
        }?.also(::subscribeMovieSearchEvent)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onBackPressed() = backPressSubject.onNext(System.currentTimeMillis())
}