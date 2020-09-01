package com.camai.archtecherstudy.data.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RecentMovieNameViewModel : ViewModel() {
    val name = MutableLiveData<String>()

    fun sendMovieName(searchname: String) {
        name.value = searchname
    }
}