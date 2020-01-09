package com.example.handnew04.ui.main

import android.util.Log
import com.example.handnew04.data.MovieRepository
import com.example.handnew04.network.NetworkManager

class MainPresenter(val view: MainContract.View, val networkManager: NetworkManager) :
    MainContract.Presenter {
    lateinit var inputText: String

    override fun serchMovie(inputText: String) {
        this.inputText = inputText

        view.showLoading()

        if (!isConnectedNetwork()) {
            view.showNotConnectedNetwork()
            view.hideLoading()
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

        view.hideLoading()
    }

    override fun checkInputQuery(): Boolean {
        return if (inputText.isEmpty()) {
            view.showInputLengthZero()
            view.hideLoading()
            false
        } else true
    }

    override fun isConnectedNetwork(): Boolean = networkManager.checkNetworkConnect()
}