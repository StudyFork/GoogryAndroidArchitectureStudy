package com.example.architecture_project.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.architecture_project.data.model.Movie
import com.example.architecture_project.data.model.NaverApi
import com.example.architecture_project.data.repository.NaverRepository

class MainViewModel (private val naverRepository: NaverRepository) : ViewModel(){

    val hasWrongChar = MutableLiveData<Unit>()
    val isEmptyKeyword = MutableLiveData<Unit>()
    val isEmptyMovieData = MutableLiveData<Unit>()
    val movieData = MutableLiveData<List<Movie>>()
    private val errorToast = MutableLiveData<Throwable>()
    val movieDataNum = MutableLiveData<Int>()

    fun getMovieData(keyword: String) {
        if (keyword.contains("@")) {
            hasWrongChar.value = Unit
            return
        }
        if (keyword.equals("")) {
            isEmptyKeyword.value = Unit
            return
        }
        naverRepository.getMovieData(keyword, success = {
            if (it.item.isEmpty()) {
                isEmptyMovieData.value = Unit
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