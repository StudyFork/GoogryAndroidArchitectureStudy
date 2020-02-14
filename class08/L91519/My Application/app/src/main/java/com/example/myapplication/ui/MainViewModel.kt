package com.example.myapplication.ui

import android.util.Log
import androidx.databinding.ObservableField
import com.example.myapplication.data.model.MovieResult
import com.example.myapplication.data.repository.NaverRepositoryImpl

class MainViewModel {

    val query = ObservableField<String>()
    val searchResultList = ObservableField<List<MovieResult.Item>>()
    val errorQueryBlank = ObservableField<Throwable>()
    val errorFailSearch = ObservableField<Throwable>()
    val resultEmpty = ObservableField<Boolean>()

    fun findMovie() {
        if (query.get()?.isBlank()?:true) {
            errorQueryBlank.set(Throwable())
        } else {
            NaverRepositoryImpl.getResultData(query.get()?:"",
                success = {
                    if (it.items.isEmpty()) {
                        searchResultList.set(it.items)
                        resultEmpty.set(true)
                    } else {
                        searchResultList.set(it.items)
                        resultEmpty.set(false)
                        //TODO: Save Searched Result

                    }
                },
                fail = {
                    errorFailSearch.set(it)
                })
        }
    }

//    private fun getQuery() = query.get() as String
}