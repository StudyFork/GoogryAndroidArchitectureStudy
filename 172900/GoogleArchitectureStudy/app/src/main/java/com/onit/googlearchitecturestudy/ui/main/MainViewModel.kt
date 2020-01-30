package com.onit.googlearchitecturestudy.ui.main

import android.util.Log
import androidx.databinding.ObservableField
import com.onit.googlearchitecturestudy.Movie
import com.onit.googlearchitecturestudy.data.repository.MovieRepository
import com.onit.googlearchitecturestudy.data.repository.MovieRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel {
    private val movieRepositroy: MovieRepository = MovieRepositoryImpl()
    val isLoading: ObservableField<Boolean> = ObservableField(false)
    val toastMessage: ObservableField<String> = ObservableField("")
    val hideKeyBoard: ObservableField<Boolean> = ObservableField(false)
    val movieList: ObservableField<List<Movie>> = ObservableField()
    val searchString: ObservableField<String> = ObservableField("")

    fun searchMovies(keyword: String) {
        with(hideKeyBoard) {
            set(true)
            notifyChange()
        }

        if (keyword.isBlank()) {
            with(toastMessage) {
                set("검색어를 입력해주세요.")
                notifyChange()
            }
            return
        }

        CoroutineScope(Dispatchers.Main).launch {
            isLoading.set(true)

            val response =
                withContext(Dispatchers.IO) { movieRepositroy.getMovieList(keyword) }

            if (response.isSuccessful) {
                val responseMovieList = response.body()?.movies
                if (responseMovieList == null) {
                    with(toastMessage) {
                        set("검색결과가 없습니다.")
                        notifyChange()
                    }
                } else {
                    movieList.set(responseMovieList)
                }
            } else {
                Log.e("MainActivity", "네이버 API요청에 실패했습니다. responseCode = ${response.code()}")
                with(toastMessage) {
                    set("네트워크가 불안정합니다.")
                    notifyChange()
                }
            }

            isLoading.set(false)
        }
    }
}