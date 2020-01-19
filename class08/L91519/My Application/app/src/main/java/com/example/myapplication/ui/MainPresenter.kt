package com.example.myapplication.ui

import android.widget.Toast
import com.example.myapplication.data.repository.NaverRepositoryImpl

class MainPresenter(private val view: MainConstract.View) : MainConstract.Presenter{
    override fun findMovie(query: String) {
        NaverRepositoryImpl.getResultData(query,
            success = { view.updateMovieRecycler(it.items) },
            fail = { view.failMovieGet(it.message.toString()) })


    }
}