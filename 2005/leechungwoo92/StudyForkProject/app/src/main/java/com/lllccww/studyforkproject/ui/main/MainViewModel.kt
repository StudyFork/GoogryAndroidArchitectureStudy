package com.lllccww.studyforkproject.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lllccww.studyforkproject.data.model.MovieItem
import com.lllccww.studyforkproject.data.repository.NaverMovieRepository

class MainViewModel(private val movieRepository: NaverMovieRepository) : ViewModel() {
    val movieItemList = MutableLiveData<ArrayList<MovieItem>>()
    val query = MutableLiveData<String>()
    val toastMessage = MutableLiveData<String>()
    val progressBar = MutableLiveData<Boolean>()
    val hideKeyboard = MutableLiveData<Boolean>()

    fun getSearchMovie() {


        val inputQuery = query.value

        if (inputQuery.isNullOrEmpty()) {
            toastMessage.value = "검색어를 입력해주세요."
            return
        }
        hideKeyboard.value = true
        progressBar.value = true


        movieRepository.getSearchMovie(
            inputQuery,
            success = { movieItem ->
                progressBar.value = false
                if (movieItem.isNullOrEmpty()) {
                    toastMessage.value = "검색결과가 없습니다."


                } else {
                    movieItemList.value = (movieItem as ArrayList<MovieItem>)
                }
            },
            failure = {
                progressBar.value = false
                toastMessage.value = it.message.toString()

            }

        )


    }

}