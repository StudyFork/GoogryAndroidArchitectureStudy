package com.onit.googlearchitecturestudy.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.onit.googlearchitecturestudy.Movie
import com.onit.googlearchitecturestudy.data.repository.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    val isLoading = MutableLiveData<Boolean>()
    val toastMessage = MutableLiveData<String>()
    val hideKeyBoard = MutableLiveData<Boolean>()
    val movieList = MutableLiveData<List<Movie>>()
    val searchString = MutableLiveData<String>()

    fun searchMovies() {
        hideKeyBoard.value = true

        val keyword = searchString.value

        if (keyword.isNullOrBlank()) {
            toastMessage.value = "검색어를 입력해주세요."
            return
        }

        CoroutineScope(Dispatchers.Main).launch {
            isLoading.value = true

            val response =
                withContext(Dispatchers.IO) { movieRepository.getMovieList(keyword) }

            if (response.isSuccessful) {
                val responseMovieList = response.body()?.movies
                if (responseMovieList == null) {
                    toastMessage.value = "검색결과가 없습니다."
                } else {
                    movieList.value = responseMovieList
                }
            } else {
                Log.e("MainActivity", "네이버 API요청에 실패했습니다. responseCode = ${response.code()}")
                toastMessage.value = "네트워크가 불안정합니다."
            }

            isLoading.value = false
        }
    }
}