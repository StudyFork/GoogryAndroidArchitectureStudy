package io.github.sooakim.ui.movie

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import io.github.sooakim.domain.repository.SAMovieRepository
import io.github.sooakim.ui.base.SAViewModel
import io.github.sooakim.ui.movie.mapper.SAMoviePresentationMapper
import io.github.sooakim.ui.movie.model.SAMoviePresentation
import io.github.sooakim.util.NotNullObservableField
import io.github.sooakim.util.ext.rx
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject
import java.util.concurrent.TimeUnit

class SAMovieSearchViewModel(
    movieRepository: SAMovieRepository,
    navigator: SAMovieSearchNavigator
) : SAViewModel<SAMovieSearchNavigator>(navigator) {
    private val _isLoading: ObservableBoolean = ObservableBoolean(false)
    private val _searchText: NotNullObservableField<String> =
        NotNullObservableField(movieRepository.latestMovieQuery)
    private val _searchClick: PublishSubject<Unit> = PublishSubject.create()
    private val _movies: NotNullObservableField<List<SAMoviePresentation>> = NotNullObservableField(
        emptyList()
    )
    private val _movieClick: PublishSubject<SAMoviePresentation> = PublishSubject.create()

    val isLoading: ObservableBoolean = _isLoading
    val searchText: ObservableField<String> = _searchText
    val searchClick: Subject<Unit> = _searchClick
    val movies: ObservableField<List<SAMoviePresentation>> = _movies

    init {
        val textChanges = _searchText.rx
            .toFlowable(BackpressureStrategy.DROP)
        val buttonClick = _searchClick
            .throttleFirst(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
            .map { _searchText.get() }
            .toFlowable(BackpressureStrategy.DROP)

        Flowable.merge(textChanges, buttonClick)
            .debounce(700, TimeUnit.MILLISECONDS)
            .map(String::trim)
            .distinctUntilChanged()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { showLoading() }
            .switchMap(movieRepository::getMovies)
            .map {
                it.map(SAMoviePresentationMapper::mapToPresentation).map {
                    it.apply {
                        onClick = _movieClick
                    }
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { hideLoading() }
            .doOnError { hideLoading() }
            .subscribe { _movies.set(it) }
            .addTo(compositeDisposable)

        _movieClick
            .map(SAMoviePresentation::link)
            .subscribe(navigator::showMovieLink)
            .addTo(compositeDisposable)
    }

    private fun showLoading() {
        _isLoading.set(true)
    }

    private fun hideLoading() {
        _isLoading.set(false)
    }
}