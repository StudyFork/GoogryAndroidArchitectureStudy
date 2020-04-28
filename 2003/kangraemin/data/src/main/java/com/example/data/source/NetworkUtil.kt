package com.example.data.source

import com.example.data.model.NetworkStatus

interface NetworkUtil {
    fun getConnectivityStatus(): NetworkStatus
}