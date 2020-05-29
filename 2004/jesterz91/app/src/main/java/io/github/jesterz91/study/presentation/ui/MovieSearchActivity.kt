package io.github.jesterz91.study.presentation.ui

import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.jakewharton.rxbinding3.appcompat.queryTextChanges
import io.github.jesterz91.study.R
import io.github.jesterz91.study.data.local.MovieDatabase
import io.github.jesterz91.study.data.local.source.MovieLocalDataSourceImpl
import io.github.jesterz91.study.data.remote.source.MovieRemoteDataSourceImpl
import io.github.jesterz91.study.databinding.ActivityMovieSearchBinding
import io.github.jesterz91.study.domain.entity.Movie
import io.github.jesterz91.study.domain.mapper.MovieLocalMapper
import io.github.jesterz91.study.domain.mapper.MovieRemoteMapper
import io.github.jesterz91.study.domain.repository.MovieRepositoryImpl
import io.github.jesterz91.study.domain.usecase.GetMovieUseCase
import io.github.jesterz91.study.domain.usecase.UseCase
import io.github.jesterz91.study.presentation.common.BaseActivity
import io.github.jesterz91.study.presentation.constant.Constant
import io.github.jesterz91.study.presentation.extension.observe
import io.github.jesterz91.study.presentation.extension.toast
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import java.util.concurrent.TimeUnit

class MovieSearchActivity :
    BaseActivity<MovieSearchViewModel, ActivityMovieSearchBinding>(ActivityMovieSearchBinding::inflate) {

    private val movieAdapter by lazy { MovieSearchAdapter() }

    private val movieDatabase by lazy { MovieDatabase.getInstance(applicationContext) }

    private val getMovieUseCase: UseCase<Flowable<List<Movie>>, String> by lazy {
        GetMovieUseCase(
            movieRepository = MovieRepositoryImpl(
                movieLocalDataSource = MovieLocalDataSourceImpl(movieDatabase.movieDao()),
                movieLocalMapper = MovieLocalMapper(),
                movieRemoteDataSource = MovieRemoteDataSourceImpl(),
                movieRemoteMapper = MovieRemoteMapper()
            )
        )
    }

    override val viewModel: MovieSearchViewModel by viewModels {
        object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return MovieSearchViewModel(getMovieUseCase) as T
            }
        }
    }

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

        backPressObservable
            .buffer(2, 1)
            .map { it.last() - it.first() }
            .observeOn(AndroidSchedulers.mainThread())
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