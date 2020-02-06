package com.example.architecture_project.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.architecture_project.data.model.Movie
import com.example.architecture_project.data.model.NaverApi
import com.example.architecture_project.data.repository.NaverRepository

class MainViewModel : ViewModel() {

    val naverRepository: NaverRepository = NaverRepository()
    var hasWrongChar = MutableLiveData<Boolean>()
    var isEmptyKeyword = MutableLiveData<Boolean>()
    var isEmptyMovieData = MutableLiveData<Boolean>()
    var movieData = MutableLiveData<List<Movie>>()
    var errorToast = MutableLiveData<Throwable>()
    var movieDataNum = MutableLiveData<Int>()

    fun getMovieData(keyword: String) {
        if (keyword.contains("@")) {
            hasWrongChar.value = true
            return
        }
        if (keyword.equals("")) {
            isEmptyKeyword.value = true
            return
        }
        naverRepository.getMovieData(keyword, success = {
            if (it.item.isEmpty()) {
                isEmptyMovieData.value = true
            } else {
                setMovieDataNum(it)
                movieData.value = it.item
            }
        }, fail = {
            Log.e("error is :", it.toString())
            errorToast.value = it
        })
    }

    fun setMovieDataNum(data: NaverApi) {
        movieDataNum.value = data.total
    }
}