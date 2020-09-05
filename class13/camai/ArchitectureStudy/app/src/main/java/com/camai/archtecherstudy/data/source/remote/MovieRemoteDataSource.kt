package com.camai.archtecherstudy.data.source.remote

import com.camai.archtecherstudy.data.model.Items

interface MovieRemoteDataSource {

    fun getSearchMovie(
        keyword: String,
        display: Int,
        start: Int,
        success: (ArrayList<Items>) -> Unit,
        failed: (String) -> Unit)

}