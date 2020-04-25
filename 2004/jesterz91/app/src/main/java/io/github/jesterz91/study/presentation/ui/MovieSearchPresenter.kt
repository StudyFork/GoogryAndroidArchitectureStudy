package io.github.jesterz91.study.presentation.ui

import android.net.Uri
import io.github.jesterz91.study.R
import io.github.jesterz91.study.domain.entity.Movie
import io.github.jesterz91.study.domain.usecase.UseCase
import io.github.jesterz91.study.presentation.common.BasePresenter
import io.github.jesterz91.study.presentation.constant.Constant
import io.github.jesterz91.study.presentation.util.ResourceProvider
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class MovieSearchPresenter(
    override val view: MovieSearchContract.View,
    private val resourceProvider: ResourceProvider,
    private val getMovieUseCase: UseCase<Flowable<List<Movie>>, String>
) : BasePresenter<MovieSearchContract.View>(), MovieSearchContract.Presenter {

    private val queryChangeSubject = PublishSubject.create<CharSequence>()

    private val browseSubject = PublishSubject.create<Uri>()

    private val backPressSubject = BehaviorSubject.createDefault(0L)

    init {
        subscribeMovieSearchEvent()

        subscribeMovieBrowseEvent()

        subscribeBackPressEvent()
    }

    private fun subscribeMovieSearchEvent() {
        queryChangeSubject.toFlowable(BackpressureStrategy.DROP)
            .debounce(Constant.SEARCH_DEBOUNCE_TIME, TimeUnit.MILLISECONDS)
            .filter(CharSequence::isNotBlank)
            .map(CharSequence::toString)
            .distinctUntilChanged()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { view.showProgress() }
            .flatMap(getMovieUseCase::invoke)
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorReturn { emptyList() }
            .doOnNext {
                view.hideProgress()
                view.hideSoftKeyboard()
            }
            .subscribe(view::showSearchResult)
            .addTo(disposables)
    }

    private fun subscribeMovieBrowseEvent() {
        browseSubject
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(view::showLink)
            .addTo(disposables)
    }

    private fun subscribeBackPressEvent() {
        backPressSubject.buffer(2, 1)
            .map { it.last() - it.first() }
            .subscribe { duration ->
                if (duration < Constant.FINISH_DURATION) {
                    view.finish()
                } else {
                    view.showToast(resourceProvider.getString(R.string.back_press_message))
                }
            }.addTo(disposables)
    }

    override fun searchMovie(query: CharSequence) = queryChangeSubject.onNext(query)

    override fun browseMovie(uri: Uri) = browseSubject.onNext(uri)

    override fun backPressed() = backPressSubject.onNext(System.currentTimeMillis())
}