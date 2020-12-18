package com.wybh.androidarchitecturestudy.main

import android.view.View
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wybh.androidarchitecturestudy.base.BaseViewModel
import com.wybh.androidarchitecturestudy.data.CinemaItem
import com.wybh.androidarchitecturestudy.model.repository.RepositoryImpl
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel @ViewModelInject constructor(
    private val repository: RepositoryImpl
) : BaseViewModel() {
    private val tempList = mutableListOf<CinemaItem>()

    private val _cinemaItemList = MutableLiveData<List<CinemaItem>>()
    val cinemaItemList: LiveData<List<CinemaItem>> = _cinemaItemList

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _recentSearch = MutableLiveData<Unit>()
    val recentSearch: LiveData<Unit> = _recentSearch

    val query = MutableLiveData<String>("")

    fun recentSearch() {
        _recentSearch.value = Unit
    }

    fun searchCinema() {
        if (query.value.isNullOrEmpty()) {
            return
        }
        progressVisible.value = View.VISIBLE

        addDisposable(repository.searchCinema(query.value!!)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.newThread())
            .map { response ->
                tempList.clear()
                response.items.map {
                    val item = CinemaItem(
                        it.image,
                        it.title,
                        it.actor,
                        it.userRating,
                        it.pubDate,
                        it.link
                    )
                    tempList.add(item)
                }
            }.subscribe({
                _cinemaItemList.value = tempList
                progressVisible.value = View.GONE
            }, {
                _error.value = it.message
            }))
    }
}