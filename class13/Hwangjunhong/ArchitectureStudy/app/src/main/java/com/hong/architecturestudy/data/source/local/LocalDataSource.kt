package com.hong.architecturestudy.data.source.local

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.hong.architecturestudy.data.source.local.entity.MovieInfo

interface LocalDataSource {
    fun saveData(keyword: String, context: Context)

    fun loadData(
        lifecycleOwner: LifecycleOwner,
        observer: Observer<List<MovieInfo>>,
        context: Context
    )

}