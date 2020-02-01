package com.hansung.firstproject.ui.main

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import com.hansung.firstproject.data.MovieModel
import com.hansung.firstproject.data.repository.NaverRepository

class MainViewModel(private val repository: NaverRepository) {

    val movieData: ObservableArrayList<MovieModel> = ObservableArrayList()
    var keyword = ObservableField<String>("")
    var showError = ObservableField<String>()
    var isEmptyResult = ObservableField<Boolean>()
    var showKeywordEmptyError = ObservableField<Boolean>()
    var hideKeyboard = ObservableField<Boolean>()


    fun doSearch() {
        if (keyword.get()!! == "") {
            showKeywordEmptyError.set(true)
        } else {
            // 검색 메소드
            repository.getMoviesData(
                keyword.get()!!,
                success = {
                    movieData.clear()
                    movieData.addAll(it.items)
                },
                fail = {
                    showError.set(it.message!!)
                },
                isEmptyList = {
                    isEmptyResult.set(true)
                }
            )
            hideKeyboard.set(true)
        }
    }
}