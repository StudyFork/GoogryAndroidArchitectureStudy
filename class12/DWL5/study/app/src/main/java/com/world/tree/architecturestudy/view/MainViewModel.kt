package com.world.tree.architecturestudy.view

import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.world.tree.architecturestudy.model.Movie
import com.world.tree.architecturestudy.model.repository.remote.NaverRepository
import java.util.*
import kotlin.collections.ArrayList

class MainViewModel(private val repository: NaverRepository) {

    private val _movieList = MutableLiveData<List<Movie.Item>>().also {
        it.value = ArrayList()
    }
    val movieList = _movieList
    private val _title = MutableLiveData<String>()
    val title = _title
    private val _toastMsg = MutableLiveData<String>()
    val toastMsg = _toastMsg




    fun searchMovie() {
        title.value?.let { it ->
            if (it.isBlank()) {
                toastMsg.value = "검색어를 입력 해 주세요"
            }

            repository.getMovies(it, success = {
                movieList.value = it
            }, error = {
                it.message?.let { message ->
                    toastMsg.value = message
                }
            })
        }

    }
}