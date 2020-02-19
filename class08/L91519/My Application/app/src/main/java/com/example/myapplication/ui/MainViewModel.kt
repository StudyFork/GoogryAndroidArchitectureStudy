package com.example.myapplication.ui

import androidx.databinding.ObservableField
import com.example.myapplication.data.model.Movie
import com.example.myapplication.data.model.MovieResult
import com.example.myapplication.data.repository.NaverRepository

class MainViewModel(private val naverRepository: NaverRepository) {

    val query = ObservableField<String>()
    val searchResultList = ObservableField<List<MovieResult.Item>>()
    val errorQueryBlank = ObservableField<Throwable>()
    val errorFailSearch = ObservableField<Throwable>()
    val resultEmpty = ObservableField<Boolean>()

    fun findMovie() {
        if (query.get()?.isBlank() != false) {
            errorQueryBlank.set(Throwable())
        } else {
            naverRepository.getResultData(
                query.get() ?: "",
                success = {
                    if (it.items.isEmpty()) {
                        searchResultList.set(it.items)
                        resultEmpty.set(true)
                    } else {
                        searchResultList.set(it.items)
                        resultEmpty.set(false)

                        naverRepository.saveCache(
                            Movie(
                                it.items,
                                query.get() ?: ""
                            )
                        )
                    }
                },
                fail = {
                    errorFailSearch.set(it)
                })
        }
    }

    fun getRecentData() {
        naverRepository.getRecentData().let {
            searchResultList.set(it.results)
            query.set(it.query)
        }
    }

}