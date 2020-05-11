package com.project.architecturestudy.data.repository

import android.content.Context
import com.project.architecturestudy.adapters.SearchAdapter

interface NaverMovieRepository {

    fun getMovieList(
        context: Context,
        keyWord: String,
        adapter: SearchAdapter?,
        SuccessMsg: () -> Unit,
        FailureMsg: () -> Unit
    )
}