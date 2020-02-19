package com.example.model.network

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager

class NetworkManager(val context: Application) {
    fun checkNetworkConnect(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo != null && networkInfo.isConnected
    }
}