package com.example.aas.data.source.local

import io.reactivex.Completable
import io.reactivex.Single

interface LocalDataSource {
    fun saveQuery(query: String): Completable

    fun getSavedQuery(): Single<List<String>>
}