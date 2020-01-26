package com.song2.myapplication.ui

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.song2.myapplication.source.MovieData
import com.song2.myapplication.source.MovieRepository

class MainViewModel(private val repo: MovieRepository) : ViewModel() {

    val MAINVIEWMODEL = "mainViewModel :"

    var movieData: ObservableField<List<MovieData>> = ObservableField()
    var visibleResult : ObservableField<Boolean> = ObservableField()

    fun getMovie(keyword : String){
        repo.getMovieData(keyword,
            GETMOVIECNT,
            paging++ * GETMOVIECNT,
            onSuccess = {
                movieData.set(it)
                preKeyword = keyword

                if (it.count() > 0) visibleResult.set(true)
                else visibleResult.set(false)

            },
            onFailure = {
                Log.e(MAINVIEWMODEL,it.toString())
            }
        )
    }

    companion object{
        var preKeyword = ""
        var paging = 1
        val GETMOVIECNT = 20
    }
}