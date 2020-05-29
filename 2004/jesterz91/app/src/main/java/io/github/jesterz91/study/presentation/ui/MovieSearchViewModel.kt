package io.github.jesterz91.study.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.github.jesterz91.study.domain.entity.Movie
import io.github.jesterz91.study.domain.usecase.UseCase
import io.github.jesterz91.study.presentation.common.BaseViewModel
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.rxkotlin.addTo
import io.reactivex.subjects.BehaviorSubject

class MovieSearchViewModel(
    private val getMovieUseCase: UseCase<Flowable<List<Movie>>, String>
) : BaseViewModel() {

    private val _movieListLiveData = MutableLiveData<List<Movie>>(emptyList())
    val movieListLiveData: LiveData<List<Movie>> = _movieListLiveData

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
            .subscribe { _movieListLiveData.postValue(it) }
            .addTo(disposables)
    }

    fun backPressed() = backPressSubject.onNext(System.currentTimeMillis())
}