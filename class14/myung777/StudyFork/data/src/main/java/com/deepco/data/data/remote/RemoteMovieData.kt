package com.deepco.data.data.remote

import com.deepco.data.data.model.Item

interface RemoteMovieData {

    fun getMovieList(
        title: String,
        success: (List<Item>) -> Unit,
        failed: (String) -> Unit
    )
}