package com.example.architecture_project.ui.main

import android.util.Log
import androidx.databinding.ObservableField
import com.example.architecture_project.data.model.Movie
import com.example.architecture_project.data.model.NaverApi
import com.example.architecture_project.data.repository.NaverRepository

class MainViewModel() {

    val naverRepository: NaverRepository = NaverRepository()
    var hasWrongChar: ObservableField<Boolean> = ObservableField()
    var isEmptyKeyword: ObservableField<Boolean> = ObservableField()
    var isEmptyMovieData: ObservableField<Boolean> = ObservableField()
    var movieData: ObservableField<List<Movie>> = ObservableField()
    var errorToast: ObservableField<Throwable> = ObservableField()
    var movieDataNum: ObservableField<Int> = ObservableField()

    fun getMovieData(keyword: String) {
        if (keyword.contains("@")) {
            hasWrongChar.set(true)
            return
        }
        if (keyword.equals("")) {
            isEmptyKeyword.set(true)
            return
        }
        naverRepository.getMovieData(keyword, success = {
            if (it.item.isEmpty()) {
                isEmptyMovieData.set(true)
            } else {
                getMovieDataNum(it)
                movieData.set(it.item)
            }
        }, fail = {
            Log.e("error is :",it.toString())
            errorToast.set(it)
        })
    }

    fun getMovieDataNum(data: NaverApi) {
        movieDataNum.set(data.total)
    }
}