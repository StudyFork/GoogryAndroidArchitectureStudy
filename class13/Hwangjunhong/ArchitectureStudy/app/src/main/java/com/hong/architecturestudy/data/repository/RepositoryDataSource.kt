package com.hong.architecturestudy.data.repository

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.hong.architecturestudy.data.model.MovieData
import com.hong.architecturestudy.data.source.local.entity.MovieInfo

interface RepositoryDataSource {

    fun getMovieList(
        query: String,
        onSuccess: (List<MovieData>) -> Unit,
        onFailure: (Throwable) -> Unit
    )

    fun saveData(keyword: String, context: Context)

    fun loadData(
        lifecycleOwner: LifecycleOwner,
        observer: Observer<List<MovieInfo>>,
        context: Context
    )
}