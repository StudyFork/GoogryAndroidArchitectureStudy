package com.project.architecturestudy.data.repository

import android.content.Context
import com.project.architecturestudy.adapters.SearchAdapter

interface NaverMovieRepository {

    fun getMovieList(
        keyWord: String,
        SuccessMsg: () -> Unit,
        FailureMsg: () -> Unit
    )

    fun getCashedMovieList(context: Context, adapter: SearchAdapter?)
}