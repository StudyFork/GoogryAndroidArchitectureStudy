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
import io.github.jesterz91.study.domain.entity.Movie
import io.github.jesterz91.study.domain.mapper.MovieLocalMapper
import io.github.jesterz91.study.domain.mapper.MovieRemoteMapper
import io.github.jesterz91.study.domain.repository.MovieRepositoryImpl
import io.github.jesterz91.study.domain.usecase.GetMovieUseCase
import io.github.jesterz91.study.domain.usecase.UseCase
import io.github.jesterz91.study.presentation.common.BaseActivity
import io.github.jesterz91.study.presentation.extension.hide
import io.github.jesterz91.study.presentation.extension.show
import io.github.jesterz91.study.presentation.uitl.ResourceProviderImpl
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.rxkotlin.addTo

class MovieSearchActivity : BaseActivity<MovieSearchContract.Presenter,
        ActivityMovieSearchBinding>(ActivityMovieSearchBinding::inflate), MovieSearchContract.View {

    private val movieAdapter by lazy { MovieSearchAdapter() }

    private val customTabsIntent by lazy {
        CustomTabsIntent.Builder().build()
    }

    private val movieDatabase by lazy {
        MovieDatabase.getInstance(applicationContext)
    }

    private val movieUseCase: UseCase<Flowable<List<Movie>>, String> by lazy {
        GetMovieUseCase(
            movieRepository = MovieRepositoryImpl(
                movieLocalDataSource = MovieLocalDataSourceImpl(movieDatabase.movieDao()),
                movieLocalMapper = MovieLocalMapper(),
                movieRemoteDataSource = MovieRemoteDataSourceImpl(),
                movieRemoteMapper = MovieRemoteMapper()
            )
        )
    }

    override val presenter: MovieSearchContract.Presenter by lazy {
        MovieSearchPresenter(this, ResourceProviderImpl(this), movieUseCase)
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
            adapter = movieAdapter
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        (menu?.findItem(R.id.menu_search)?.actionView as? SearchView)?.apply {
            maxWidth = Int.MAX_VALUE
            queryHint = getString(R.string.search_movie_hint)

            queryTextChanges()
                .subscribe(presenter::searchMovie)
                .addTo(disposables)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onBackPressed() = presenter.backPressed()

    override fun fetchSearchResult(items: List<Movie>) {
        movieAdapter.changeItems(items)
        movieAdapter.notifyDataSetChanged()
    }

    override fun getClickObservable(): Observable<Uri> = movieAdapter.getClickObservable()

    override fun showLink(uri: Uri) = customTabsIntent.launchUrl(this, uri)

    override fun showProgress() = binding.progressBar.show()

    override fun hideProgress() = binding.progressBar.hide()
}