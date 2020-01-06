package com.example.handnew04.ui.main

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.example.handnew04.data.MovieRepository

class MainPresenter(val view: MainContract.View) : MainContract.Presenter {
    lateinit var mInputText: String

    override fun serchMovie(inputText: String) {
        mInputText = inputText

        if (!checkConnectedNetwork()) {
            view.showNotConnectedNetwork()
            return
        }

        if (!checkInputQuery()) return

        MovieRepository.getMovieData(inputText
            , success = {
                if (it.items.isEmpty()) view.showEmptyResult()
                else view.showSuccessSearchMovie(it)
            },
            fail = {
                Log.e("NaverMovieApi Fail ", it.message)
                view.showFailSearchMovie(it.message)
            })
    }

    override fun checkInputQuery(): Boolean {
        return if (mInputText.isEmpty()) {
            view.showInputLengthZero()
            false
        } else true
    }

    override fun checkConnectedNetwork(): Boolean {
        val connectivityManager =
            view.getContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }

}