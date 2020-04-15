package com.byiryu.study.ui

import android.app.Application
import com.byiryu.study.model.Repository
import com.byiryu.study.model.local.LocalDataBase
import com.byiryu.study.model.local.LocalDataSource
import com.byiryu.study.model.local.pref.AppPreference
import com.byiryu.study.model.remote.RemoteDataSource
import com.byiryu.study.ui.mvvm.base.BRViewModelFactory

class BRApplication : Application() {

    lateinit var viewModelFactory: BRViewModelFactory
    lateinit var repository: Repository

    override fun onCreate() {
        super.onCreate()
        init()
    }

    private fun init() {

        val pref = AppPreference(this)
        val db = LocalDataBase.getInstance(this)

        val remoteDataSource = RemoteDataSource()
        val localDataSource = LocalDataSource(db.movieDao(), pref)


        repository = Repository(remoteDataSource, localDataSource)
        viewModelFactory = BRViewModelFactory(repository)

    }

}