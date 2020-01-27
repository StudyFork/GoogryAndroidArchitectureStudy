package com.hansung.firstproject.ui.main

import android.util.Log
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.databinding.ObservableList
import com.hansung.firstproject.data.MovieModel
import com.hansung.firstproject.data.repository.NaverRepository

class MainViewModel(private val repository: NaverRepository) {

    //var movieData = ObservableField<ArrayList<MovieModel>>()
    val movieData: ObservableArrayList<MovieModel> = ObservableArrayList()
    var showError = ObservableField<String>()
    var isEmptyResult = ObservableField<Boolean>()
    var showKeywordEmptyError = ObservableField<Boolean>()
    var hideKeyboard = ObservableField<Boolean>()

    fun doSearch(keyword: String) {
        if (keyword.isEmpty()) {
            showKeywordEmptyError.set(true)
        } else {
            // 검색 메소드
            repository.getMoviesData(
                keyword,
                success = {
                    //view.addItemToAdapter(it)
                    movieData.clear()
                    movieData.addAll(it.items)
                    movieData.forEach{ a ->
                        Log.d("ahn", a.title)
                    }


                    //movieData.set(it.items)
                    //Log.d("ahn", movieData.get()?.size.toString())
                },
                fail = {
                    //view.showErrorByErrorMessage(it.message!!)
                    showError.set(it.message!!)
                },
                isEmptyList = {
                    //view.showErrorEmptyList()
                    isEmptyResult.set(true)
                }
            )
            //view.removeKeyboard()
            hideKeyboard.set(true)
        }
    }
}