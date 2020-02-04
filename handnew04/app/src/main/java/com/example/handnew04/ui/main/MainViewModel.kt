package com.example.handnew04.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.handnew04.data.MovieData
import com.example.handnew04.data.MovieRepository
import com.example.handnew04.network.NetworkManager

class MainViewModel(val networkManager: NetworkManager) : ViewModel() {
    var movieData = MutableLiveData<List<MovieData>>()
    var isLoading = MutableLiveData<Boolean>()
    var isNetworkRunning = MutableLiveData<Boolean>()
    var isInputTextLengthZero = MutableLiveData<Boolean>()
    var isEmptyResult = MutableLiveData<Boolean>()
    var failMessage = MutableLiveData<String>()
    var inputText = MutableLiveData<String>()

    fun serchMovie() {
        isLoading.value = true

        if (!isConnectedNetwork()) {
            isNetworkRunning.value = false
            isLoading.value = false
            return
        }

        if (!checkInputQuery()) return

        MovieRepository.getMovieData(
            inputText.value.toString()
            , success = {
                if (it.items.isEmpty()) isEmptyResult.value = true
                else movieData.value = it.items
            },
            fail = {
                Log.e("NaverMovieApi Fail ", it.message)
                failMessage.value = it.message
            })

        isLoading.value = false
    }

    fun isConnectedNetwork(): Boolean = networkManager.checkNetworkConnect()

    fun checkInputQuery(): Boolean {
        return if (inputText.value.toString().isEmpty()) {
            isInputTextLengthZero.value = true
            isLoading.value = false
            false
        } else true
    }

    fun searchButtonClick() {
        serchMovie()
    }
}