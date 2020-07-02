package io.github.jesterz91.study.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.jesterz91.domain.model.Movie
import io.github.jesterz91.domain.usecase.UseCase
import io.github.jesterz91.study.common.BaseViewModel
import io.github.jesterz91.study.mapper.MoviePresentationMapper
import io.github.jesterz91.study.model.MoviePresentation
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.BehaviorSubject

class MovieSearchViewModel(
    private val getMovieUseCase: UseCase<Flowable<List<Movie>>, String>
) : BaseViewModel() {

    private val _movieListLiveData = MutableLiveData<List<MoviePresentation>>(emptyList())
    val movieListLiveData: LiveData<List<MoviePresentation>> = _movieListLiveData

    private val _loadingLiveData = MutableLiveData(false)
    val loadingLiveData: LiveData<Boolean> = _loadingLiveData

    private val _errorMessageLiveData = MutableLiveData<String>()
    val errorMessageLiveData: LiveData<String> = _errorMessageLiveData

    private val backPressSubject = BehaviorSubject.createDefault(0L)
    val backPressObservable: Observable<Long> = backPressSubject

    fun searchMovie(query: String) {
        getMovieUseCase(query)
            .onBackpressureDrop()
            .doOnSubscribe { _loadingLiveData.postValue(true) }
            .doAfterTerminate { _loadingLiveData.postValue(false) }
            .doOnError { _errorMessageLiveData.postValue(it.message) }
            .onErrorReturn { emptyList() }
            .map { it.map(MoviePresentationMapper::mapToPresentation) }
            .subscribe { _movieListLiveData.postValue(it) }
            .addTo(disposables)
    }

    fun backPressed() = backPressSubject.onNext(System.currentTimeMillis())
}