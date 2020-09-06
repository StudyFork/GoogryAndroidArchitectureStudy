package com.hong.architecturestudy.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.hong.architecturestudy.data.model.MovieData
import com.hong.architecturestudy.data.source.local.entity.MovieInfo

interface RepositoryDataSource {

    fun getMovieList(
        query: String,
        onSuccess: (List<MovieData>) -> Unit,
        onFailure: (Throwable) -> Unit
    )

    fun saveData(keyword: String)
    fun loadData(context: Context): LiveData<List<MovieInfo>>
}