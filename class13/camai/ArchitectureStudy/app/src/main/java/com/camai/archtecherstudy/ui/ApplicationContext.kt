package com.camai.archtecherstudy.ui

import android.app.Application
import android.content.Context
import com.camai.archtecherstudy.data.repository.MovieRepositoryImpl

class ApplicationContext : Application() {
    init {
        instance = this
    }

    companion object {
        private var instance: ApplicationContext? = null
        fun movieapplicationContext(): Context {
            return instance!!.applicationContext
        }
    }

}