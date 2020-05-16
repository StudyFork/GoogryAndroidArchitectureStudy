package io.github.jesterz91.study.presentation.ui

import io.github.jesterz91.study.domain.entity.Movie
import io.github.jesterz91.study.domain.entity.Result
import io.github.jesterz91.study.domain.usecase.UseCase
import io.github.jesterz91.study.presentation.common.BaseViewModel
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

class MovieSearchViewModel(
    private val useCase: UseCase<Flowable<List<Movie>>, String>
) : BaseViewModel() {

    private val _movieSearchResult = PublishSubject.create<Result<List<Movie>>>()
    val movieSearchResult: Observable<Result<List<Movie>>>
        get() = _movieSearchResult

    private val backPressSubject = BehaviorSubject.createDefault(0L)
    val backPressObservable: Observable<Long>
        get() = backPressSubject

    fun searchMovie(query: String) {
        useCase.invoke(query)
            .onBackpressureDrop()
            .doOnSubscribe { _movieSearchResult.onNext(Result.Loading) }
            .doOnError { _movieSearchResult.onNext(Result.Error(it)) }
            .onErrorReturn { emptyList() }
            .subscribe { _movieSearchResult.onNext(Result.Success(it)) }
            .addTo(disposables)
    }

    fun backPressed() = backPressSubject.onNext(System.currentTimeMillis())
}