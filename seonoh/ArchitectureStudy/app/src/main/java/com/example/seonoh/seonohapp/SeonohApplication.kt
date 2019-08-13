package com.example.seonoh.seonohapp

import android.app.Application
import android.content.Context
import com.example.seonoh.seonohapp.network.Api
import com.example.seonoh.seonohapp.network.RetrofitCreator

class SeonohApplication : Application(){

    companion object{
        var mContext : Context? = null
        var mApiService : Api? = null
    }

    override fun onCreate() {
        super.onCreate()

        mContext = this
        mApiService = RetrofitCreator.createNet()
    }
}