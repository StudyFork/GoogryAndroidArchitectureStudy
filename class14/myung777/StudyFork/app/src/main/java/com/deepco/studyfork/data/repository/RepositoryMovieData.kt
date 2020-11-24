package com.deepco.studyfork.data.repository

import com.deepco.studyfork.data.model.Item

interface RepositoryMovieData {
    fun getMovieList(
        title: String,
        success: (List<Item>) -> Unit,
        failed: (String) -> Unit
    )

    fun saveQuery(query: String)

    fun getQueryList(): List<String>
}