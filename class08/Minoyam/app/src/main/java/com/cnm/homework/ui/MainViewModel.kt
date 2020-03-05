package com.cnm.homework.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cnm.homework.applySchedulers
import com.cnm.homework.data.model.NaverResponse
import com.cnm.homework.data.repository.NaverQueryRepository
import io.reactivex.disposables.CompositeDisposable

class MainViewModel(private val naverQueryRepository: NaverQueryRepository) : ViewModel() {
    private val disposable = CompositeDisposable()

    val movieItems = MutableLiveData<List<NaverResponse.Item>>()
    val searchString = MutableLiveData<String>()
    val toastString = MutableLiveData<String>()
    val isKeyboardBoolean = MutableLiveData<Boolean>()
    val isProgressBoolean = MutableLiveData<Boolean>()

    fun movieListSearch() {
        val query = searchString.value as String
        if (query.isNotEmpty()) {
            disposable.add(naverQueryRepository.getNaverMovie(query)
                .applySchedulers()
                .doOnSubscribe {
                    isProgressBoolean.value = true
                    isKeyboardBoolean.value = false
                }
                .doAfterTerminate {
                    isProgressBoolean.value = false
                }
                .subscribe({
                    if (it.total != 0) {
                        setItems(it.items)
                    }
                }, {
                    toastString.value = "검색 결과가 없습니다."
                    movieItems.value = null
                })
            )
        } else {
            toastString.value = "제목을 입력해주세요."
        }
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }

    fun setItems(it: List<NaverResponse.Item>) {
        movieItems.value = null
        movieItems.value = it
    }

    fun showKeyboard() {
        isKeyboardBoolean.value = true
    }

    fun loadMovieList(): List<NaverResponse.Item> = naverQueryRepository.loadLocal()
}