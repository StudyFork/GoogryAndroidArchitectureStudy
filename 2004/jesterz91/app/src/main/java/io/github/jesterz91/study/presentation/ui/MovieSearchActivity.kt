package io.github.jesterz91.study.presentation.ui

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.DividerItemDecoration
import com.jakewharton.rxbinding3.appcompat.queryTextChanges
import io.github.jesterz91.study.R
import io.github.jesterz91.study.databinding.ActivityMovieSearchBinding
import io.github.jesterz91.study.presentation.common.BaseActivity
import io.github.jesterz91.study.presentation.constant.Constant
import io.github.jesterz91.study.presentation.extension.observe
import io.github.jesterz91.study.presentation.extension.toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

class MovieSearchActivity :
    BaseActivity<MovieSearchViewModel, ActivityMovieSearchBinding>(ActivityMovieSearchBinding::inflate) {

    private val movieAdapter by lazy { MovieSearchAdapter() }

    override val viewModel: MovieSearchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(binding) {
            vm = viewModel
            lifecycleOwner = this@MovieSearchActivity
            adapter = movieAdapter
            itemDecoration =
                DividerItemDecoration(this@MovieSearchActivity, DividerItemDecoration.VERTICAL)
        }

        observe()
    }

    private fun observe(): Unit = with(viewModel) {
        observe(errorMessageLiveData, ::toast)

        observe(movieListLiveData) { hideSoftKeyboard() }

        backPressObservable
            .buffer(2, 1)
            .map { it.last() - it.first() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { duration ->
                if (duration < Constant.FINISH_DURATION) {
                    finish()
                } else {
                    toast(R.string.back_press_message)
                }
            }.addTo(disposables)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        (menu?.findItem(R.id.menu_search)?.actionView as? SearchView)?.apply {
            maxWidth = Int.MAX_VALUE
            queryHint = getString(R.string.search_movie_hint)
            queryTextChanges()
                .debounce(Constant.SEARCH_DEBOUNCE_TIME, TimeUnit.MILLISECONDS)
                .filter(CharSequence::isNotBlank)
                .map(CharSequence::toString)
                .distinctUntilChanged()
                .subscribe(viewModel::searchMovie)
                .addTo(disposables)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onBackPressed() = viewModel.backPressed()
}