package io.github.jesterz91.study.presentation.ui

import android.net.Uri
import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.DividerItemDecoration
import com.jakewharton.rxbinding3.appcompat.queryTextChanges
import io.github.jesterz91.study.R
import io.github.jesterz91.study.data.model.MovieResponse
import io.github.jesterz91.study.data.remote.MovieService
import io.github.jesterz91.study.databinding.ActivityMovieSearchBinding
import io.github.jesterz91.study.presentation.common.BaseActivity
import io.github.jesterz91.study.presentation.constant.Constant
import io.github.jesterz91.study.presentation.extension.toGone
import io.github.jesterz91.study.presentation.extension.toVisible
import io.github.jesterz91.study.presentation.extension.toast
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit

class MovieSearchActivity :
    BaseActivity<ActivityMovieSearchBinding>(ActivityMovieSearchBinding::inflate) {

    private val backPressSubject = BehaviorSubject.createDefault(0L)

    private val movieAdapter by lazy {
        MovieSearchAdapter(
            mutableListOf()
        )
    }

    private val customTabsIntent: CustomTabsIntent by lazy {
        CustomTabsIntent.Builder().build()
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
            .debounce(700, TimeUnit.MILLISECONDS)
            .filter(CharSequence::isNotBlank)
            .map(CharSequence::toString)
            .distinctUntilChanged()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { binding.progressBar.toVisible() }
            .flatMapSingle(MovieService.movieApi::searchMovie)
            .observeOn(AndroidSchedulers.mainThread())
            .map(MovieResponse::items)
            .onErrorReturn { mutableListOf() }
            .doOnNext { binding.progressBar.toGone() }
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        (menu?.findItem(R.id.menu_search)?.actionView as? SearchView)?.apply {
            maxWidth = Int.MAX_VALUE
            queryHint = getString(R.string.search_movie_hint)
        }?.also(::subscribeMovieSearchEvent)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onBackPressed() {
        backPressSubject.onNext(System.currentTimeMillis())
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
}