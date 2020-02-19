package com.example.handnew04.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.model.data.MovieData
import com.example.model.data.MovieRepository
import com.example.model.network.NetworkManager


class MainViewModel(val networkManager: NetworkManager, val movieRepository: MovieRepository) :
    ViewModel() {
    val movieData = MutableLiveData<List<MovieData>>()
    val isLoading = MutableLiveData<Boolean>()
    val isNetworkRunning = MutableLiveData<Boolean>()
    val isInputTextLengthZero = MutableLiveData<Boolean>()
    val isEmptyResult = MutableLiveData<Boolean>()
    val failMessage = MutableLiveData<String>()
    val inputText = MutableLiveData<String>()

    fun serchMovie() {
        isLoading.value = true

        if (!isConnectedNetwork()) {
            isNetworkRunning.value = false
            isLoading.value = false
            return
        }

        if (!checkInputQuery()) return

        movieRepository.getMovieData(
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