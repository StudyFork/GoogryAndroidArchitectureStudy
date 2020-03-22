package io.github.sooakim.ui.movie

import android.net.Uri
import io.github.sooakim.domain.repository.SAMovieRepository
import io.github.sooakim.ui.movie.mapper.SAMoviePresentationMapper
import io.github.sooakim.ui.movie.model.SAMoviePresentation
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class SAMovieSearchPresenter(
    private val movieRepository: SAMovieRepository,
    private val view: SAMovieSearchContractor.View
) : SAMovieSearchContractor.Presenter {
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()
    private val searchAction: PublishSubject<String> = PublishSubject.create()
    private val searchChanges: PublishSubject<String> = PublishSubject.create()

    init {
        val textChanges = searchChanges
            .toFlowable(BackpressureStrategy.DROP)
        val buttonClick = searchAction
            .throttleFirst(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
            .toFlowable(BackpressureStrategy.DROP)

        Flowable.merge(textChanges, buttonClick)
            .debounce(700, TimeUnit.MILLISECONDS)
            .map(CharSequence::trim)
            .map(CharSequence::toString)
            .distinctUntilChanged()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { view.showLoading() }
            .switchMap(movieRepository::getMovies)
            .map { it.map(SAMoviePresentationMapper::mapToPresentation) }
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { view.hideLoading() }
            .doOnError { view.hideLoading() }
            .subscribe { view.showSearchResults(it) }
            .addTo(compositeDisposable)

        view.setSearchText(text = movieRepository.latestMovieQuery)
    }

    override fun onSearchResultClick(item: SAMoviePresentation) {
        val linkString = item.link.takeIf(String::isNotBlank) ?: return
        val uri = kotlin.runCatching { Uri.parse(linkString) }.getOrNull() ?: return
        view.showLink(uri)
    }

    override fun search(text: String) {
        searchAction.onNext(text)
    }

    override fun onSearchChanges(text: String) {
        searchChanges.onNext(text)
    }
}