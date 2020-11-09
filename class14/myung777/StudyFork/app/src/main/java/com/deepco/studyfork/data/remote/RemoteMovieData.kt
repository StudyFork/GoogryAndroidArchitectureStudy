package com.deepco.studyfork.data.remote

import com.deepco.studyfork.data.model.Item

interface RemoteMovieData {

    fun getMovieList(
        title: String,
        success: (List<Item>) -> Unit,
        failed: (String) -> Unit
    )
}