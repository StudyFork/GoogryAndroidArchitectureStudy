package com.world.tree.architecturestudy.view

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dwl.study_library.model.Movie
import com.dwl.study_library.repository.NaverRepository
import java.util.*
import kotlin.collections.ArrayList

class MainViewModel @ViewModelInject constructor(private val repository: NaverRepository)
    : ViewModel() {

    private val _movieList = MutableLiveData<List<Movie.Item>>(mutableListOf())
    val movieList : LiveData<List<Movie.Item>> = _movieList
    private val _toastMsg = MutableLiveData<String>()
    val toastMsg : LiveData<String> = _toastMsg
    val title = MutableLiveData<String>()


    fun searchMovie() {
        title.value?.let { it ->
            if (it.isBlank()) {
                _toastMsg.value = "검색어를 입력 해 주세요"
            }

            repository.getMovies(it, success = {
                _movieList.value = it
            }, error = {
                it.message?.let { message ->
                    _toastMsg.value = message
                }
            })
        }

    }
}