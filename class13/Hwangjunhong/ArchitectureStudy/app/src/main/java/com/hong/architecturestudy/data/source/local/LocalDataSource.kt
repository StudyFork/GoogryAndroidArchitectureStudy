package com.hong.architecturestudy.data.source.local

import android.content.Context
import androidx.lifecycle.LiveData
import com.hong.architecturestudy.data.source.local.entity.MovieInfo

interface LocalDataSource {
    fun saveData(keyword: String, context: Context)
    fun loadData(context: Context): LiveData<List<MovieInfo>>
}